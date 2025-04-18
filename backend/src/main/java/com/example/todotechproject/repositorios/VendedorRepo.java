package com.example.todotechproject.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.todotechproject.modelo.entidades.Usuario;
import com.example.todotechproject.modelo.entidades.Vendedor;

@Repository
public interface VendedorRepo extends JpaRepository<Vendedor, Long> {

    Optional<Vendedor> findByUsuario(Usuario usuario);

}
