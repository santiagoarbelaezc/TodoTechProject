package com.example.todotechproject.modelo.dto;

import jakarta.validation.constraints.NotNull;

public record ProductoDTO(Long id,
                          @NotNull(message = "Nombre is required")
                          String nombre,
                          String codigo,
                          String descripcion,
                          CategoriaDTO categoria,
                          Double precio,
                          Integer stock) {

}
