package com.example.todotechproject.servicios.UsuarioServicios;

import java.util.List;

import com.example.todotechproject.modelo.dto.UsuarioDTO.UsuarioDTO;
import com.example.todotechproject.modelo.entidades.Usuario;
import com.example.todotechproject.modelo.enums.TipoUsuario;

public interface UsuarioServicio {

    void crearUsuario(Usuario usuario) throws Exception;

    void actualizarUsuario(Usuario usuario) throws Exception;

    Usuario buscarPorUsuario(String usuario);

    void eliminarUsuario(Usuario usuario) throws Exception;

    void buscarUsuario(Usuario usuario) throws Exception;

    List<Usuario> listarUsuarios() throws Exception;

    Usuario buscarUsuarioPorId(String usuario);

    List<UsuarioDTO> buscarUsuariosPorTipo(TipoUsuario tipoUsuario);

    void eliminarUsuario(String usuario);
}
