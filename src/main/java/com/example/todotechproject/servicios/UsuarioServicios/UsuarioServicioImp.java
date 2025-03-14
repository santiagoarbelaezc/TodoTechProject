package com.example.todotechproject.servicios.UsuarioServicios;

import com.example.todotechproject.dto.UsuarioDTO.UsuarioDTO;
import com.example.todotechproject.modelo.entidades.Usuario;
import com.example.todotechproject.modelo.enums.TipoUsuario;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UsuarioServicioImp implements UsuarioServicio{
    @Override
    public void crearUsuario(Usuario usuario) throws Exception {

    }

    @Override
    public void actualizarUsuario(Usuario usuario) throws Exception {

    }

    @Override
    public void eliminarUsuario(Usuario usuario) throws Exception {

    }

    @Override
    public void buscarUsuario(Usuario usuario) throws Exception {

    }

    @Override
    public List<Usuario> listarUsuarios() throws Exception {
        return List.of();
    }

    @Override
    public Usuario buscarUsuarioPorId(String usuario) {
        return null;
    }

    @Override
    public List<UsuarioDTO> buscarUsuariosPorTipo(TipoUsuario tipoUsuario) {
        return List.of();
    }

    @Override
    public void eliminarUsuario(String usuario) {

    }
}
