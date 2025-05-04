package com.example.todotechproject.servicios.DetalleOrdenServicios;

import java.util.List;
import java.util.Optional;

import com.example.todotechproject.modelo.dto.DetalleOrdenDTO;
import com.example.todotechproject.modelo.entidades.OrdenVenta;

public interface DetalleOrdenServicio {

  Optional<DetalleOrdenDTO> save(DetalleOrdenDTO detalleOrdenDTO);
 List<DetalleOrdenDTO> save(List<DetalleOrdenDTO> detalleOrdenDTO, OrdenVenta ordenVenta);

  Optional<DetalleOrdenDTO> findById(Long id);

  List<DetalleOrdenDTO> findAll();

  List<DetalleOrdenDTO> findByOrdenVenta(Long id);

  void delete(Long id);
}
