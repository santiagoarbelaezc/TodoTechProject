package com.example.todotechproject.servicios.DetalleOrdenServicios;

import java.util.List;
import java.util.Optional;

import com.example.todotechproject.modelo.dto.DetalleOrdenDTO;

public interface DetalleOrdenServicio {

  Optional<DetalleOrdenDTO> save(DetalleOrdenDTO detalleOrdenDTO);

  Optional<DetalleOrdenDTO> findById(Long id);

  List<DetalleOrdenDTO> findAll();

  List<DetalleOrdenDTO> findByOrdenVenta(Long id);

  void delete(Long id);
}
