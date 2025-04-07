package com.example.todotechproject.servicios.OrdenVentaServicios;

import com.example.todotechproject.dto.OrdenVenta.CrearOrdenDTO;
import com.example.todotechproject.modelo.entidades.OrdenVenta;

public interface OrdenVentaServicio {
    boolean crearOrdenVentaDesdeDTO(CrearOrdenDTO request);

    OrdenVenta crearOrdenVenta(CrearOrdenDTO request) throws Exception;
}
