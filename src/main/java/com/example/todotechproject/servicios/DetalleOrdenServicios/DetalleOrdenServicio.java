package com.example.todotechproject.servicios.DetalleOrdenServicios;

import com.example.todotechproject.dto.DetalleOrden.DetalleOrdenDTO;
import com.example.todotechproject.dto.DetalleOrden.EliminarDetalleRequest;
import com.example.todotechproject.dto.ProductoDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DetalleOrdenServicio {

    DetalleOrdenDTO crearDetalle(ProductoDTO productoDTO, Long ordenVentaId);

    boolean eliminarPorProductoYOrden(Long productoId, Long ordenVentaId);

    List<DetalleOrdenDTO> obtenerPorOrdenVenta(Long id)throws Exception;

    ResponseEntity<String> eliminar(EliminarDetalleRequest request);

    DetalleOrdenDTO aumentarCantidad(Long productoId, Long ordenVentaId);

    DetalleOrdenDTO disminuirCantidad(Long productoId, Long ordenVentaId);
}

