package com.example.todotechproject.servicios.OrdenVentaServicios;

import com.example.todotechproject.dto.OrdenVenta.CrearOrdenDTO;
import com.example.todotechproject.dto.OrdenVenta.OrdenVentaDTO;
import com.example.todotechproject.dto.OrdenVenta.OrdenVentaDescuentoRequest;
import com.example.todotechproject.modelo.entidades.OrdenVenta;
import com.example.todotechproject.modelo.entidades.Usuario;
import com.example.todotechproject.modelo.entidades.Vendedor;
import com.example.todotechproject.modelo.enums.EstadoOrden;
import com.example.todotechproject.repositorios.DetalleOrdenRepo;
import com.example.todotechproject.repositorios.OrdenVentaRepo;
import com.example.todotechproject.servicios.UsuarioServicios.UsuarioServicio;
import com.example.todotechproject.servicios.VendedorServicios.VendedorServicio;
import com.example.todotechproject.utils.Mappers.OrdenVentaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.todotechproject.modelo.entidades.DetalleOrden;

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
            throw new com.example.todotechproject.excepciones.UsuarioNoEncontradoException("El usuario no existe");
        }

        Vendedor vendedor = vendedorServicio.buscarVendedorPorUsuario(usuario);

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
        ultimaOrden.getVendedor().getNombre();
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
                    orden.getVendedor().getNombre();
                    orden.getProductos().forEach(p -> p.getProducto().getId());

                    return OrdenVentaMapper.toDTO(orden);
                });
    }

    @Override
    public OrdenVentaDescuentoRequest aplicarDescuento(Long ordenVentaId, Double porcentajeDescuento) {
        if (porcentajeDescuento <= 0 || porcentajeDescuento > 100) {
            throw new IllegalArgumentException("El porcentaje de descuento debe estar entre 0 y 100");
        }

        OrdenVenta orden = ordenVentaRepo.findById(ordenVentaId)
                .orElseThrow(() -> new RuntimeException("Orden de venta no encontrada"));

        // Verificar que la orden no tenga productos ya con descuento
        if (orden.getProductos().stream().anyMatch(d -> d.getSubtotal() <
                (d.getProducto().getPrecio() * d.getCantidad()))) {
            throw new IllegalStateException("Ya se ha aplicado un descuento a esta orden");
        }

        // Calcular el factor de descuento
        double factorDescuento = 1 - (porcentajeDescuento / 100);

        // Aplicar descuento a cada detalle de la orden
        for (DetalleOrden detalle : orden.getProductos()) {
            double precioOriginal = detalle.getProducto().getPrecio() * detalle.getCantidad();
            double nuevoSubtotal = precioOriginal * factorDescuento;
            detalle.setSubtotal(nuevoSubtotal);
            detalleOrdenRepo.save(detalle);
        }

        // Actualizar el total de la orden
        actualizarTotalOrden(orden);

        // Convertir a DTO y devolver
        OrdenVentaDTO dto = OrdenVentaMapper.toDTO(orden);

        // Incluimos el porcentaje de descuento en la respuesta aunque no lo guardemos en BD
        return new OrdenVentaDescuentoRequest(
                dto.id(),
                dto.fecha(),
                dto.cliente(),
                dto.vendedor(),
                dto.productos(),
                dto.estado(),
                dto.total(),
                porcentajeDescuento
                // Lo pasamos en el DTO aunque no se persista
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
                .orElseThrow(() -> new RuntimeException("Orden de venta no encontrada"));

        // Restaurar precios originales
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

