package com.example.todotechproject.modelo.dto;

import lombok.NonNull;

public record DetalleOrdenDTO(Long id, @NonNull ProductoDTO producto, @NonNull Integer cantidad, Double subtotal, Long idOrdenVenta) {}
