package com.example.todotechproject.dto.producto;

public record ProductoDTO (
        Long id,
        String nombre,
        String codigo,
        String descripcion,
        Long categoria,
        Double precio,
        Integer stock
    ) {
}
