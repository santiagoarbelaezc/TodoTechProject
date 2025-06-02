package com.example.todotechproject.repositorios;

import com.example.todotechproject.modelo.entidades.Trabajador;
import com.example.todotechproject.modelo.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TrabajadorRepo extends JpaRepository<Trabajador, Long> {
    Optional<Trabajador> findByUsuario(Usuario usuario);
}
