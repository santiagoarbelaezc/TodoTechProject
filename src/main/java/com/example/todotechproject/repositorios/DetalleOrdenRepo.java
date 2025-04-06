package com.example.todotechproject.repositorios;

import com.example.todotechproject.modelo.entidades.DetalleOrden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DetalleOrdenRepo extends JpaRepository<DetalleOrden, Long> {

    List<DetalleOrden> findByIdProducto(Long productoId);

    List<DetalleOrden> findByIdOrdenVenta(Long ordenVentaId);

    Optional<DetalleOrden> findByOrdenVentaIdAndProductoId(Long ordenVentaId, Long productoId);


}
