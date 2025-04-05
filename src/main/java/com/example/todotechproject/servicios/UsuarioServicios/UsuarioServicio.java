package com.example.todotechproject.servicios.UsuarioServicios;

import com.example.todotechproject.dto.UsuarioDTO.UsuarioDTO;
import com.example.todotechproject.modelo.entidades.Usuario;
import com.example.todotechproject.modelo.enums.TipoUsuario;

import java.util.List;

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
