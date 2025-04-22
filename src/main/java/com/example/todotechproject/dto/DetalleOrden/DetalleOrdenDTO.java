package com.example.todotechproject.dto.DetalleOrden;

public record DetalleOrdenDTO(
        Long id,
        Long productoId,
        Integer cantidad,
        Double subtotal,
        Long OrdenVenta
) {}

