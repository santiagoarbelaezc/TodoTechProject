package com.example.todotechproject.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.todotechproject.modelo.entidades.DetalleOrden;
import com.example.todotechproject.modelo.entidades.OrdenVenta;

@Repository
public interface DetalleOrdenRepo extends JpaRepository<DetalleOrden, Long> {

  List<DetalleOrden> findByOrdenVenta(OrdenVenta ordenVenta);
}
