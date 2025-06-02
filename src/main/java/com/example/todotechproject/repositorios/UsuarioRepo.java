package com.example.todotechproject.repositorios;

import com.example.todotechproject.modelo.entidades.Usuario;
import com.example.todotechproject.modelo.enums.TipoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepo extends JpaRepository<Usuario, Long> {
    List<Usuario> findByTipoUsuario(TipoUsuario tipoUsuario);
    Optional<Usuario> findByUsuario(String usuario);

    // UsuarioRepo.java
    Optional<Usuario> findTopByOrderByIdDesc();


}