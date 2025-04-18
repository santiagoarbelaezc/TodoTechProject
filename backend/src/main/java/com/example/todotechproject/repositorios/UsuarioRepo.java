package com.example.todotechproject.repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.todotechproject.modelo.entidades.Usuario;
import com.example.todotechproject.modelo.enums.TipoUsuario;

public interface UsuarioRepo extends JpaRepository<Usuario, String> {
    List<Usuario> findByTipoUsuario(TipoUsuario tipoUsuario);
    Optional<Usuario> findByUsuario(String usuario);

}