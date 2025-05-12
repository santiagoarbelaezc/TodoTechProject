package com.example.todotechproject.dto.DetalleOrden;

public record AplicarDescuentoRequest(
        Long ordenVentaId,
        Double porcentajeDescuento
) {}