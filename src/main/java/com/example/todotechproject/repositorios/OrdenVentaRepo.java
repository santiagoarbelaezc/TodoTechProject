package com.example.todotechproject.repositorios;

import com.example.todotechproject.modelo.entidades.OrdenVenta;
import com.example.todotechproject.modelo.entidades.Trabajador;
import com.example.todotechproject.modelo.enums.EstadoOrden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdenVentaRepo extends JpaRepository<OrdenVenta, Long> {

    OrdenVenta findTopByOrderByIdDesc();

    List<OrdenVenta> findByTrabajador(Trabajador trabajador);

    List<OrdenVenta> findByEstado(EstadoOrden estado);

    List<OrdenVenta> findByCliente_Id(Long id);


    List<OrdenVenta> findAllByOrderByFechaAsc();

    List<OrdenVenta> findAllByOrderByTotalDesc();

    @Query("SELECT o FROM OrdenVenta o WHERE o.estado = 'PAGADA'")
    List<OrdenVenta> findOrdenesPagadas();

    @Query("SELECT o FROM OrdenVenta o WHERE o.estado = 'PENDIENTE'")
    List<OrdenVenta> findOrdenesPendientes();

    @Query("SELECT o FROM OrdenVenta o WHERE o.estado = 'DESPACHADA'")
    List<OrdenVenta> findOrdenesDespachadas();

    @Query("SELECT o FROM OrdenVenta o WHERE o.estado = 'CERRADA'")
    List<OrdenVenta> findOrdenesCerradas();
}
