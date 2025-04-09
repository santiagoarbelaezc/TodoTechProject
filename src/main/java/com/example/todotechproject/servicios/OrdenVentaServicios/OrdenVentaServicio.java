package com.example.todotechproject.servicios.OrdenVentaServicios;

import com.example.todotechproject.dto.CarritoDTOS.DetalleOrdenDTO;
import com.example.todotechproject.dto.OrdenVenta.CrearOrdenDTO;
import com.example.todotechproject.modelo.entidades.DetalleOrden;
import com.example.todotechproject.modelo.entidades.OrdenVenta;

import java.util.List;

public interface OrdenVentaServicio {
    boolean crearOrdenVentaDesdeDTO(CrearOrdenDTO request);

    OrdenVenta crearOrdenVenta(CrearOrdenDTO request) throws Exception;

    List<DetalleOrden> agregarProductoOrden (DetalleOrdenDTO request) throws Exception;

    List<DetalleOrden> eliminarProductoOrden(DetalleOrdenDTO request) throws Exception;

    List<DetalleOrden> buscarProductoOrden (DetalleOrdenDTO request) throws Exception;




}
