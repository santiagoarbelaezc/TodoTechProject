package com.example.todotechproject.dto.OrdenVenta;

import com.example.todotechproject.dto.ClienteDTO;
import com.example.todotechproject.dto.DetalleOrdenDTO;
import com.example.todotechproject.dto.VendedorDTO;
import com.example.todotechproject.modelo.enums.EstadoOrden;

import java.util.Date;
import java.util.List;

public record OrdenVentaDTO(
        Long id,
        Date fecha,
        ClienteDTO cliente,
        VendedorDTO vendedor,
        List<DetalleOrdenDTO> productos,
        EstadoOrden estado,
        Double total
) {}


