package com.example.todotechproject.repositorios;

import com.example.todotechproject.modelo.entidades.DetalleOrden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DetalleOrdenRepo extends JpaRepository<DetalleOrden, Long> {

    List<DetalleOrden> findByProducto_Id(Long productoId);

    List<DetalleOrden> findByOrdenVenta_Id(Long ordenVentaId);

    Optional<DetalleOrden> findByOrdenVenta_IdAndProducto_Id(Long ordenVentaId, Long productoId);

}

