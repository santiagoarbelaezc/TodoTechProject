package com.example.todotechproject.utils.Mappers;

import com.example.todotechproject.dto.ClienteDTO;
import com.example.todotechproject.dto.DetalleOrden.DetalleOrdenDTO;
import com.example.todotechproject.dto.OrdenVenta.OrdenVentaDTO;
import com.example.todotechproject.dto.UsuarioDTO.UsuarioDTO;
import com.example.todotechproject.dto.VendedorDTO;
import com.example.todotechproject.modelo.entidades.OrdenVenta;

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
                        ordenVenta.getVendedor().getId(),
                        ordenVenta.getVendedor().getNombre(),
                        ordenVenta.getVendedor().getCorreo(),
                        ordenVenta.getVendedor().getTelefono(),
                        new UsuarioDTO(
                                ordenVenta.getVendedor().getUsuario().getId(),
                                ordenVenta.getVendedor().getUsuario().getUsuario(),
                                ordenVenta.getVendedor().getUsuario().getPassword(),
                                ordenVenta.getVendedor().getUsuario().getTipoUsuario()
                        )
                ),
                ordenVenta.getProductos().stream()
                        .map(DetalleOrdenMapper::toDTO)
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

        // Cliente y vendedor deben ser asignados desde el repositorio en el servicio
        return ordenVenta;
    }
}
