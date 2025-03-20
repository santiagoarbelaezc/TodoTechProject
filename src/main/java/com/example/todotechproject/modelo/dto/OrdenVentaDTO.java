package com.example.todotechproject.modelo.dto;

import java.util.Date;
import java.util.List;

import com.example.todotechproject.modelo.enums.EstadoOrden;

public record OrdenVentaDTO(Long id, Date fecha, ClienteDTO cliente, VendedorDTO vendedor,
                            List<DetalleOrdenDTO> productos, EstadoOrden estado, Double total) {
}
