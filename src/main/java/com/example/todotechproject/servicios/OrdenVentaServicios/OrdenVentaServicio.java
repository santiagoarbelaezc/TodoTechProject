package com.example.todotechproject.servicios.OrdenVentaServicios;

import com.example.todotechproject.dto.OrdenVenta.CrearOrdenDTO;
import com.example.todotechproject.dto.OrdenVenta.OrdenVentaDTO;
import com.example.todotechproject.modelo.entidades.OrdenVenta;

import java.util.List;
import java.util.Optional;

public interface OrdenVentaServicio {
    boolean crearOrdenVentaDesdeDTO(CrearOrdenDTO request);

    OrdenVenta crearOrdenVenta(CrearOrdenDTO request) throws Exception;

    OrdenVentaDTO obtenerUltimaOrden();

    Optional<OrdenVentaDTO> obtenerOrdenPorId(Long id);



    OrdenVenta crearOrdenTemporal();

    List<OrdenVentaDTO> obtenerTodasLasOrdenes();
}
