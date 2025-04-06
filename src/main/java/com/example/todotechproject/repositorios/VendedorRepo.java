package com.example.todotechproject.repositorios;

import com.example.todotechproject.modelo.entidades.Usuario;
import com.example.todotechproject.modelo.entidades.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VendedorRepo extends JpaRepository<Vendedor, Long> {

    Optional<Vendedor> findByUsuario(Usuario usuario);

}
