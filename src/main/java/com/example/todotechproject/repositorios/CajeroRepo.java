package com.example.todotechproject.repositorios;

import com.example.todotechproject.modelo.entidades.Cajero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CajeroRepo extends JpaRepository<Cajero, Long> {
    // Métodos personalizados si es necesario

    //Repositorios
}
