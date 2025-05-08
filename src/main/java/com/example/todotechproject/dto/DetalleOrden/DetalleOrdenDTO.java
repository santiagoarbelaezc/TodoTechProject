package com.example.todotechproject.dto.DetalleOrden;

import com.example.todotechproject.dto.ProductoDTO;

public record DetalleOrdenDTO(
        Long id,
        ProductoDTO producto, // ← Aquí incluyes el producto completo
        Integer cantidad,
        Double subtotal,
        Long ordenVenta
) {}
