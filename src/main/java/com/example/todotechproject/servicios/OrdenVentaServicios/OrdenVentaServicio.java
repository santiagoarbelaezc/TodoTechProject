package com.example.todotechproject.servicios.OrdenVentaServicios;

import com.example.todotechproject.dto.OrdenVenta.CrearOrdenDTO;

public interface OrdenVentaServicio {
    boolean crearOrdenVentaDesdeDTO(CrearOrdenDTO request);
}
