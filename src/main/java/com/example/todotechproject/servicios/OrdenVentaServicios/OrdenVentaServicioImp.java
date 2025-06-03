package com.example.todotechproject.servicios.OrdenVentaServicios;

import com.example.todotechproject.dto.OrdenVenta.CrearOrdenDTO;
import com.example.todotechproject.dto.OrdenVenta.OrdenVentaDTO;
import com.example.todotechproject.dto.OrdenVenta.OrdenVentaDescuentoRequest;
import com.example.todotechproject.excepciones.CajeroExcepciones.OrdenNoEncontradaException;
import com.example.todotechproject.excepciones.OrdenVentaExcepciones.DescuentoInvalidoException;
import com.example.todotechproject.excepciones.OrdenVentaExcepciones.DescuentoYaAplicadoException;
import com.example.todotechproject.modelo.entidades.*;
import com.example.todotechproject.modelo.enums.EstadoOrden;
import com.example.todotechproject.repositorios.DetalleOrdenRepo;
import com.example.todotechproject.repositorios.OrdenVentaRepo;
import com.example.todotechproject.servicios.UsuarioServicios.UsuarioServicio;
import com.example.todotechproject.servicios.VendedorServicios.VendedorServicio;
import com.example.todotechproject.utils.Mappers.OrdenVentaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrdenVentaServicioImp implements OrdenVentaServicio {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private VendedorServicio vendedorServicio;

    @Autowired
    private OrdenVentaRepo ordenVentaRepo;

    @Autowired
    private DetalleOrdenRepo detalleOrdenRepo;


    @Override
    public boolean crearOrdenVentaDesdeDTO(CrearOrdenDTO request) {
        return false;
    }

    @Override
    public OrdenVenta crearOrdenVenta(CrearOrdenDTO request) throws Exception {
        Usuario usuario = usuarioServicio.buscarPorUsuario(request.vendedor());
        if (usuario == null) {
            throw new com.example.todotechproject.excepciones.UsuarioExcepciones.UsuarioNoEncontradoException("El usuario no existe");
        }

        Trabajador vendedor = vendedorServicio.buscarVendedorPorUsuario(usuario);

        return vendedorServicio.crearOrdenVenta(
                LocalDateTime.now(),
                request.cliente(),
                vendedor
        );
    }


    @Override
    public OrdenVentaDTO obtenerUltimaOrden() {
        OrdenVenta ultimaOrden = ordenVentaRepo.findTopByOrderByIdDesc();

        // ⚠️ Forzar carga de relaciones perezosas
        ultimaOrden.getCliente().getNombre();
        ultimaOrden.getTrabajador().getNombre();
        ultimaOrden.getProductos().forEach(p -> {
            p.getProducto().getId();
        });

        // ⚠️ Calcular total manualmente
        Double total = ultimaOrden.getProductos().stream()
                .mapToDouble(p -> p.getSubtotal() != null ? p.getSubtotal() : 0.0)
                .sum();

        OrdenVentaDTO dto = OrdenVentaMapper.toDTO(ultimaOrden);
        return new OrdenVentaDTO(
                dto.id(),
                dto.fecha(),
                dto.cliente(),
                dto.vendedor(),
                dto.productos(),
                dto.estado(),
                total // ← total recalculado
        );
    }


    @Override
    public OrdenVenta crearOrdenTemporal() {
        OrdenVenta ordenTemporal = OrdenVenta.builder()
                .fecha(new Date())
                .estado(EstadoOrden.PENDIENTE)  // Asegúrate que este valor exista en tu enum
                .total(0.0)
                .productos(List.of())  // lista vacía de productos
                .build();

        return ordenVentaRepo.save(ordenTemporal);
    }

    @Override
    public Optional<OrdenVentaDTO> obtenerOrdenPorId(Long id) {
        return ordenVentaRepo.findById(id)
                .map(orden -> {
                    // Forzar carga de relaciones perezosas
                    orden.getCliente().getNombre();
                    orden.getTrabajador().getNombre();
                    orden.getProductos().forEach(p -> p.getProducto().getId());

                    return OrdenVentaMapper.toDTO(orden);
                });
    }

    @Override
    public OrdenVentaDescuentoRequest aplicarDescuento(Long ordenVentaId, Double porcentajeDescuento) {
        if (porcentajeDescuento <= 0 || porcentajeDescuento > 100) {
            throw new DescuentoInvalidoException("El porcentaje de descuento debe estar entre 0 y 100");
        }

        OrdenVenta orden = ordenVentaRepo.findById(ordenVentaId)
                .orElseThrow(() -> new OrdenNoEncontradaException("Orden de venta no encontrada con ID: " + ordenVentaId));

        // Verificar si ya se aplicó un descuento (subtotal < precio * cantidad)
        boolean descuentoYaAplicado = orden.getProductos().stream().anyMatch(d ->
                d.getSubtotal() < d.getProducto().getPrecio() * d.getCantidad());

        if (descuentoYaAplicado) {
            throw new DescuentoYaAplicadoException("Ya se ha aplicado un descuento a esta orden");
        }

        double factorDescuento = 1 - (porcentajeDescuento / 100);

        for (DetalleOrden detalle : orden.getProductos()) {
            double precioOriginal = detalle.getProducto().getPrecio() * detalle.getCantidad();
            double nuevoSubtotal = precioOriginal * factorDescuento;
            detalle.setSubtotal(nuevoSubtotal);
            detalleOrdenRepo.save(detalle);
        }

        actualizarTotalOrden(orden);

        OrdenVentaDTO dto = OrdenVentaMapper.toDTO(orden);

        return new OrdenVentaDescuentoRequest(
                dto.id(),
                dto.fecha(),
                dto.cliente(),
                dto.vendedor(),
                dto.productos(),
                dto.estado(),
                dto.total(),
                porcentajeDescuento
        );
    }


    private void actualizarTotalOrden(OrdenVenta orden) {
        // Calculamos el nuevo total sumando los subtotales de todos los detalles de la orden
        Double nuevoTotal = orden.getProductos().stream()
                .mapToDouble(DetalleOrden::getSubtotal)
                .sum();

        // Actualizamos el total de la orden
        orden.setTotal(nuevoTotal);

        // Guardamos la orden con el nuevo total
        ordenVentaRepo.save(orden);
    }

    @Override
    public List<OrdenVentaDTO> obtenerTodasLasOrdenes() {
        List<OrdenVenta> ordenes = ordenVentaRepo.findAll();
        return ordenes.stream()
                .map(ordenVenta -> {
                    // Calcular el total de la orden
                    Double total = ordenVenta.getProductos().stream()
                            .mapToDouble(DetalleOrden::getSubtotal) // Obtener el subtotal de cada producto
                            .sum(); // Sumar todos los subtotales

                    // Convertir la orden de venta a DTO y setear el total
                    OrdenVentaDTO dto = OrdenVentaMapper.toDTO(ordenVenta);
                    return new OrdenVentaDTO(
                            dto.id(),
                            dto.fecha(),
                            dto.cliente(),
                            dto.vendedor(),
                            dto.productos(),
                            dto.estado(),
                            total // Establecer el total calculado
                    );
                })
                .collect(Collectors.toList());
    }

    @Override
    public OrdenVentaDTO removerDescuento(Long ordenVentaId) {
        OrdenVenta orden = ordenVentaRepo.findById(ordenVentaId)
                .orElseThrow(() -> new OrdenNoEncontradaException("Orden de venta no encontrada con ID: " + ordenVentaId));

        for (DetalleOrden detalle : orden.getProductos()) {
            double precioOriginal = detalle.getProducto().getPrecio() * detalle.getCantidad();
            detalle.setSubtotal(precioOriginal);
            detalleOrdenRepo.save(detalle);
        }

        actualizarTotalOrden(orden);

        return OrdenVentaMapper.toDTO(orden);
    }



    @Override
    public List<OrdenVentaDTO> listarOrdenesPorFecha() {
        return ordenVentaRepo.findAllByOrderByFechaAsc().stream()
                .map(OrdenVentaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrdenVentaDTO> listarOrdenesPorValor() {
        return ordenVentaRepo.findAllByOrderByTotalDesc().stream()
                .map(OrdenVentaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrdenVentaDTO> listarOrdenesPorEstado(EstadoOrden estado) {
        return ordenVentaRepo.findByEstado(estado).stream()
                .map(OrdenVentaMapper::toDTO)
                .collect(Collectors.toList());
    }





}

