package com.example.todotechproject.repositorios;

import com.example.todotechproject.modelo.entidades.OrdenVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdenVentaRepo extends JpaRepository<OrdenVenta, Long> {
}
