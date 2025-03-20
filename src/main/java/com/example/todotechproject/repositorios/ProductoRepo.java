package com.example.todotechproject.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.todotechproject.modelo.entidades.Categoria;
import com.example.todotechproject.modelo.entidades.Producto;

@Repository
public interface ProductoRepo extends JpaRepository<Producto, Long> {

  List<Producto> findAllByCategoria(Categoria categoria);
}
