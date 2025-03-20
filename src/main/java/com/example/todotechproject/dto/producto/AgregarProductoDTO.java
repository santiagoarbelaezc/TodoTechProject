package com.example.todotechproject.dto.producto;

public record AgregarProductoDTO(
        String nombre,
        String codigo,
        String descripcion,
        Long categoria,
        Double precio,
        Integer stock
) {
}
