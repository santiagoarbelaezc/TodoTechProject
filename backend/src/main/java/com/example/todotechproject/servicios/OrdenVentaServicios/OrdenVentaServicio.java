package com.example.todotechproject.servicios.OrdenVentaServicios;

import java.util.List;
import java.util.Optional;

import com.example.todotechproject.modelo.dto.OrdenVentaDTO;
import com.example.todotechproject.modelo.enums.EstadoOrden;

public interface OrdenVentaServicio {

  OrdenVentaDTO save(OrdenVentaDTO ordenVenta);

  void delete(Long id);

  OrdenVentaDTO updateOrder(OrdenVentaDTO ordenVenta);

  Optional<OrdenVentaDTO> getOrderById(Long id);

  List<OrdenVentaDTO> list();

  List<OrdenVentaDTO> listByUserAndStatus(Long id, EstadoOrden estadoOrden);
}
