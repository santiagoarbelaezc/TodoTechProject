package com.example.todotechproject.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.todotechproject.modelo.entidades.Cajero;


@Repository
public interface CajeroRepo extends JpaRepository<Cajero, Long> {
    // Métodos personalizados si es necesario

    //Repositorios
}
