package com.example.todotechproject.modelo.dto;

import lombok.NonNull;

import com.example.todotechproject.modelo.entidades.OrdenVenta;

public record DetalleOrdenDTO(Long id, @NonNull ProductoDTO producto, @NonNull Integer cantidad, Double subtotal, Long idOrdenVenta) {}
