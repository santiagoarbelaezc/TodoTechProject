package com.example.todotechproject.dto.OrdenVenta;

import com.example.todotechproject.dto.ClienteDTO;
import com.example.todotechproject.dto.DetalleOrden.DetalleOrdenDTO;
import com.example.todotechproject.dto.TrabajadorDTO;

import com.example.todotechproject.modelo.enums.EstadoOrden;

import java.util.Date;
import java.util.List;

public record OrdenVentaDescuentoRequest(
        Long id,
        Date fecha,
        ClienteDTO cliente,
        TrabajadorDTO vendedor,
        List<DetalleOrdenDTO> productos,
        EstadoOrden estado,
        Double total,
        Double descuentoAplicado
) {
}
