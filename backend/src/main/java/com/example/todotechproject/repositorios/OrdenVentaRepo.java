package com.example.todotechproject.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.todotechproject.modelo.entidades.OrdenVenta;
import com.example.todotechproject.modelo.enums.EstadoOrden;

@Repository
public interface OrdenVentaRepo extends JpaRepository<OrdenVenta, Long> {
  List<OrdenVenta> findByVendedorIdAndEstado(Long vendedor_id, EstadoOrden estado);
}
