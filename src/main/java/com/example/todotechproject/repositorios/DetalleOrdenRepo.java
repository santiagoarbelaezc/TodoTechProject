package com.example.todotechproject.repositorios;

import java.util.List;

import com.example.todotechproject.modelo.dto.DetalleOrdenDTO;
import com.example.todotechproject.modelo.entidades.DetalleOrden;
import com.example.todotechproject.modelo.entidades.OrdenVenta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleOrdenRepo extends JpaRepository<DetalleOrden, Long> {

  List<DetalleOrden> findByOrdenVenta(OrdenVenta ordenVenta);
}
