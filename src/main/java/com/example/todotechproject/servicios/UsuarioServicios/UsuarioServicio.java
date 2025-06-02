package com.example.todotechproject.servicios.UsuarioServicios;

import com.example.todotechproject.dto.UsuarioDTO.CrearUsuarioDTO;
import com.example.todotechproject.dto.UsuarioDTO.UsuarioDTO;
import com.example.todotechproject.dto.UsuarioDTO.UsuarioSimpleDTO;
import com.example.todotechproject.modelo.entidades.Usuario;
import com.example.todotechproject.modelo.enums.TipoUsuario;

import java.util.List;

public interface UsuarioServicio {

    void crearUsuarioDesdeDTO(UsuarioSimpleDTO dto) throws Exception;
    void actualizarUsuarioDesdeDTO(UsuarioDTO dto) throws Exception;

    List<UsuarioDTO> listarUsuariosDTO();
    List<UsuarioDTO> buscarUsuariosPorTipoDTO(TipoUsuario tipoUsuario);
    UsuarioDTO validarCredencialesDTO(String usuario, String password);
    UsuarioSimpleDTO obtenerUltimoUsuarioCreadoDTO();

    Usuario buscarPorUsuario(String usuario);
    void eliminarUsuario(String usuario);

    //SIN USO AUN
    List<Usuario> listarUsuarios();
    Usuario buscarUsuarioPorId(String usuario);
    List<Usuario> buscarUsuariosPorTipo(TipoUsuario tipoUsuario);
    Usuario validarCredenciales(String usuario, String password);
    Usuario obtenerUltimoUsuarioCreado();
}
