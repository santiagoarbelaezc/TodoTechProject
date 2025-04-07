package com.example.todotechproject.dto;


public record ClienteDTO(
        Long id,
        String nombre,
        String correo,
        String telefono
) {}