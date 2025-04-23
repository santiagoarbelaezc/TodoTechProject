package com.example.todotechproject.utils.Mappers;

import com.example.todotechproject.dto.OrdenVenta.OrdenVentaDTO;
import com.example.todotechproject.dto.ClienteDTO;
import com.example.todotechproject.dto.DetalleOrden.DetalleOrdenDTO;
import com.example.todotechproject.dto.VendedorDTO;
import com.example.todotechproject.modelo.entidades.OrdenVenta;
import com.example.todotechproject.modelo.enums.EstadoOrden;

import java.util.List;
import java.util.stream.Collectors;

public class OrdenVentaMapper {

    public static OrdenVentaDTO toDTO(OrdenVenta ordenVenta) {
        if (ordenVenta == null) {
            return null;
        }

        return new OrdenVentaDTO(
                ordenVenta.getId(),
                ordenVenta.getFecha(),
                new ClienteDTO(
                        ordenVenta.getCliente().getId(),
                        ordenVenta.getCliente().getNombre(),
                        ordenVenta.getCliente().getCorreo(),
                        ordenVenta.getCliente().getTelefono()
                ),
                new VendedorDTO(
                        ordenVenta.getVendedor().getNombre(),
                        ordenVenta.getVendedor().getCorreo(),
                        ordenVenta.getVendedor().getTelefono(),
                        ordenVenta.getVendedor().getUsuario()
                ),
                ordenVenta.getProductos().stream()
                        .map(detalle -> new DetalleOrdenDTO(detalle.getId(), detalle.getProducto().getId(),
                                detalle.getCantidad(), detalle.getSubtotal(),
                                detalle.getOrdenVenta().getId()))
                        .collect(Collectors.toList()),
                ordenVenta.getEstado(),
                ordenVenta.getTotal()
        );
    }

    public static OrdenVenta toEntity(OrdenVentaDTO dto) {
        if (dto == null) {
            return null;
        }

        OrdenVenta ordenVenta = new OrdenVenta();
        ordenVenta.setId(dto.id());
        ordenVenta.setFecha(dto.fecha());
        ordenVenta.setEstado(dto.estado());
        ordenVenta.setTotal(dto.total());

        // Aquí deberías asignar cliente y vendedor desde su entidad correspondiente
        // Esto depende de cómo obtengas los datos en tu aplicación
        // ordenVenta.setCliente(clienteRepository.findById(dto.cliente().getId()).orElse(null));
        // ordenVenta.setVendedor(vendedorRepository.findById(dto.vendedor().getId()).orElse(null));

        return ordenVenta;
    }
}