package com.example.todotechproject.servicios.OrdenVentaServicios;

import com.example.todotechproject.dto.OrdenVenta.CrearOrdenDTO;
import com.example.todotechproject.dto.OrdenVenta.OrdenVentaDTO;
import com.example.todotechproject.dto.OrdenVenta.OrdenVentaDescuentoRequest;
import com.example.todotechproject.modelo.entidades.OrdenVenta;
import com.example.todotechproject.modelo.enums.EstadoOrden;

import java.util.List;
import java.util.Optional;

public interface OrdenVentaServicio {
    boolean crearOrdenVentaDesdeDTO(CrearOrdenDTO request);

    OrdenVenta crearOrdenVenta(CrearOrdenDTO request) throws Exception;

    OrdenVentaDTO obtenerUltimaOrden();

    Optional<OrdenVentaDTO> obtenerOrdenPorId(Long id);
    //SERVICIOS PARA EL DESCUENTO
    OrdenVentaDescuentoRequest aplicarDescuento(Long ordenVentaId, Double porcentajeDescuento);
    OrdenVentaDTO removerDescuento(Long ordenVentaId);

    OrdenVenta crearOrdenTemporal();

    List<OrdenVentaDTO> obtenerTodasLasOrdenes();

    List<OrdenVentaDTO> listarOrdenesPorFecha();
    List<OrdenVentaDTO> listarOrdenesPorValor();
    List<OrdenVentaDTO> listarOrdenesPorEstado(EstadoOrden estado);

}
