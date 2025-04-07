package com.example.todotechproject.servicios.DetalleOrdenServicios;

import com.example.todotechproject.dto.DetalleOrdenDTO;
import com.example.todotechproject.dto.OrdenVenta.OrdenVentaDTO;
import com.example.todotechproject.dto.ProductoDTO;

public interface DetalleOrdenServicio {
    DetalleOrdenDTO crearDetalle(ProductoDTO productoDTO, OrdenVentaDTO ordenVentaDTO, Integer cantidad);
}