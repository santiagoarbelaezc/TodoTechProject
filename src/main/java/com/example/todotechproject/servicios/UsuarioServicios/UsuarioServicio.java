package com.example.todotechproject.servicios.UsuarioServicios;

import com.example.todotechproject.dto.UsuarioDTO.CrearUsuarioDTO;
import com.example.todotechproject.modelo.entidades.Usuario;
import com.example.todotechproject.modelo.enums.TipoUsuario;

import java.util.List;

public interface UsuarioServicio {

    void crearUsuario(Usuario usuario) throws Exception;

    void crearUsuario2(Usuario usuario) throws Exception;

    void crearUsuarioPaquete(CrearUsuarioDTO crearUsuarioDTO)throws Exception;

    void actualizarUsuario(Usuario usuario) throws Exception;

    Usuario buscarPorUsuario(String usuario);

    void eliminarUsuario(Usuario usuario) throws Exception;

    void buscarUsuario(Usuario usuario) throws Exception;

    List<Usuario> listarUsuarios() throws Exception;

    Usuario buscarUsuarioPorId(String usuario);

    List<Usuario> buscarUsuariosPorTipo(TipoUsuario tipoUsuario);

    Usuario validarCredenciales(String usuario, String password);

    void eliminarUsuario(String usuario);

    Usuario obtenerUltimoUsuarioCreado();

}
