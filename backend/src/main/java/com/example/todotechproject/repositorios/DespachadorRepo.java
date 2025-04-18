package com.example.todotechproject.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.todotechproject.modelo.entidades.Despachador;

@Repository
public interface DespachadorRepo extends JpaRepository<Despachador, Long> {
}
