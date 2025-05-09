package com.example.todotechproject.servicios.OrdenVentaServicios;

import com.example.todotechproject.dto.OrdenVenta.CrearOrdenDTO;
import com.example.todotechproject.dto.OrdenVenta.OrdenVentaDTO;
import com.example.todotechproject.modelo.entidades.OrdenVenta;
import com.example.todotechproject.modelo.entidades.Usuario;
import com.example.todotechproject.modelo.entidades.Vendedor;
import com.example.todotechproject.modelo.enums.EstadoOrden;
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
            p.getProducto().getId(); // Carga producto asociado
            p.getOrdenVenta().getId(); // Puede que no lo necesites
        });

        return OrdenVentaMapper.toDTO(ultimaOrden);
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




}

