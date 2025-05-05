package com.example.todotechproject.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.todotechproject.modelo.entidades.Cliente;

@Repository
public interface ClienteRepo extends JpaRepository<Cliente, Long> {
  Optional<Cliente> findByCorreo(String correo);

}
