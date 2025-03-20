package com.example.todotechproject.dto.detalleOrden;

import com.example.todotechproject.modelo.entidades.Producto;

public record DetalleOrdenDTO (
        Long id,
        Producto producto,
        Integer cantidad,
        Double subtotal
){
}
