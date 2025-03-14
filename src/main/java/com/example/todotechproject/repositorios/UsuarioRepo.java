package com.example.todotechproject.repositorios;

import com.example.todotechproject.modelo.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepo extends JpaRepository<Usuario, String> {


}
