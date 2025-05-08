package com.example.todotechproject.utils.Mappers;

import com.example.todotechproject.dto.DetalleOrden.DetalleOrdenDTO;
import com.example.todotechproject.modelo.entidades.DetalleOrden;

public class DetalleOrdenMapper {

    public static DetalleOrdenDTO toDTO(DetalleOrden detalle) {
        if (detalle == null) return null;

        return new DetalleOrdenDTO(
                detalle.getId(),
                ProductoMapper.toDTO(detalle.getProducto()), // <-- Aquí mapeamos correctamente el producto
                detalle.getCantidad(),
                detalle.getSubtotal(),
                detalle.getOrdenVenta() != null ? detalle.getOrdenVenta().getId() : null
        );
    }
}
