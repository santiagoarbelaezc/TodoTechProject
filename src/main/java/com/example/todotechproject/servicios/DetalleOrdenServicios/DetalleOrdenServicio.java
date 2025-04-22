package com.example.todotechproject.servicios.DetalleOrdenServicios;

import com.example.todotechproject.dto.DetalleOrden.DetalleOrdenDTO;
import com.example.todotechproject.dto.ProductoDTO;

public interface DetalleOrdenServicio {

    DetalleOrdenDTO crearDetalle(ProductoDTO productoDTO, Long ordenVentaId);

    boolean eliminarPorProductoYOrden(Long productoId, Long ordenVentaId);
}

