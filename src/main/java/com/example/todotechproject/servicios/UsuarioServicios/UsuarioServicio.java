package com.example.todotechproject.servicios.UsuarioServicios;

import com.example.todotechproject.modelo.entidades.Usuario;

import java.util.List;

public interface UsuarioServicio {

    void crearUsuario(Usuario usuario) throws Exception;

    void actualizarUsuario(Usuario usuario) throws Exception;

    void eliminarUsuario(Usuario usuario) throws Exception;

    void buscarUsuario(Usuario usuario) throws Exception;

    List<Usuario> listarUsuarios() throws Exception;

}
