package com.example.todotechproject.utils.Mappers;

import com.example.todotechproject.dto.ClienteDTO;
import com.example.todotechproject.dto.DetalleOrden.DetalleOrdenDTO;
import com.example.todotechproject.dto.OrdenVenta.OrdenVentaDescuentoRequest;
import com.example.todotechproject.dto.TrabajadorDTO;
import com.example.todotechproject.modelo.entidades.DetalleOrden;
import com.example.todotechproject.modelo.entidades.OrdenVenta;
import com.example.todotechproject.utils.Mappers.Usuarios.UsuarioMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrdenVentaDescuentoMapper {

    private final UsuarioMapper usuarioMapper;

    public OrdenVentaDescuentoMapper(UsuarioMapper usuarioMapper) {
        this.usuarioMapper = usuarioMapper;
    }

    public OrdenVentaDescuentoRequest toDTO(OrdenVenta ordenVenta, Double descuentoAplicado) {
        return new OrdenVentaDescuentoRequest(
                ordenVenta.getId(),
                ordenVenta.getFecha(),
                mapCliente(ordenVenta),
                mapTrabajador(ordenVenta),
                mapDetallesOrden(ordenVenta),
                ordenVenta.getEstado(),
                ordenVenta.getTotal(),
                descuentoAplicado
        );
    }

    private ClienteDTO mapCliente(OrdenVenta ordenVenta) {
        if (ordenVenta.getCliente() == null) return null;

        return new ClienteDTO(
                ordenVenta.getCliente().getId(),
                ordenVenta.getCliente().getNombre(),
                ordenVenta.getCliente().getCorreo(),
                ordenVenta.getCliente().getTelefono()
        );
    }

    private TrabajadorDTO mapTrabajador(OrdenVenta ordenVenta) {
        if (ordenVenta.getTrabajador() == null) return null;

        return new TrabajadorDTO(
                ordenVenta.getTrabajador().getId(),
                ordenVenta.getTrabajador().getNombre(),
                ordenVenta.getTrabajador().getCorreo(),
                ordenVenta.getTrabajador().getTelefono(),
                usuarioMapper.toDTO(ordenVenta.getTrabajador().getUsuario())
        );
    }

    private List<DetalleOrdenDTO> mapDetallesOrden(OrdenVenta ordenVenta) {
        if (ordenVenta.getProductos() == null) return List.of();

        return ordenVenta.getProductos().stream()
                .map(this::mapDetalleOrden)
                .collect(Collectors.toList());
    }

    private DetalleOrdenDTO mapDetalleOrden(DetalleOrden detalle) {
        return new DetalleOrdenDTO(
                detalle.getId(),
                ProductoMapper.toDTO(detalle.getProducto()),
                detalle.getCantidad(),
                detalle.getSubtotal(),
                detalle.getOrdenVenta().getId()
        );
    }
}
