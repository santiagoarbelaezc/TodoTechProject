package com.example.todotechproject.repositorios;

import com.example.todotechproject.modelo.entidades.OrdenVenta;
import com.example.todotechproject.modelo.entidades.Vendedor;
import com.example.todotechproject.modelo.enums.EstadoOrden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdenVentaRepo extends JpaRepository<OrdenVenta, Long> {

    OrdenVenta findTopByOrderByIdDesc();

    List<OrdenVenta> findByVendedor(Vendedor vendedor);

    List<OrdenVenta> findByEstado(EstadoOrden estado);

    List<OrdenVenta> findByCliente_Id(Long id);
}
