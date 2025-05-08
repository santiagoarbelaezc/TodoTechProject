package com.example.todotechproject.repositorios;

import com.example.todotechproject.modelo.entidades.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepo extends JpaRepository<Categoria, Long> {

    Categoria findCategoriaByNombre(String categoria);

}
