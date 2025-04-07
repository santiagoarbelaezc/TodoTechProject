package com.example.todotechproject.repositorios;

import com.example.todotechproject.modelo.entidades.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepo extends JpaRepository<Producto, Long> {

    List<Producto> findByNombreContaining(String nombre);
    List<Producto> findByCategoriaNombre(String categoriaNombre);

}
