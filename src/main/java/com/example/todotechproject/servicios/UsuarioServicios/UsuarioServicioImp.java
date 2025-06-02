package com.example.todotechproject.servicios.UsuarioServicios;

import com.example.todotechproject.dto.UsuarioDTO.UsuarioDTO;
import com.example.todotechproject.dto.UsuarioDTO.UsuarioSimpleDTO;
import com.example.todotechproject.excepciones.UsuarioExcepciones.CredencialesInvalidasException;
import com.example.todotechproject.excepciones.UsuarioExcepciones.UsuarioYaExisteException;
import com.example.todotechproject.excepciones.UsuarioNoEncontradoException;
import com.example.todotechproject.modelo.entidades.Usuario;
import com.example.todotechproject.modelo.enums.TipoUsuario;

import com.example.todotechproject.repositorios.UsuarioRepo;
import com.example.todotechproject.utils.Mappers.Usuarios.UsuarioMapper;
import com.example.todotechproject.utils.Mappers.Usuarios.UsuarioSimpleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UsuarioServicioImp implements UsuarioServicio {

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Autowired
    private UsuarioSimpleMapper usuarioSimpleMapper;

    @Override
    public void crearUsuarioDesdeDTO(UsuarioSimpleDTO dto) {
        Usuario usuario = usuarioSimpleMapper.toEntity(dto);
        if (usuarioRepo.findByUsuario(usuario.getUsuario()).isPresent()) {
            throw new UsuarioYaExisteException("El usuario '" + usuario.getUsuario() + "' ya existe");
        }
        usuarioRepo.save(usuario);
    }

    @Override
    public void actualizarUsuarioDesdeDTO(UsuarioDTO dto) {
        Usuario usuario = usuarioMapper.toEntity(dto);
        if (!usuarioRepo.existsById(usuario.getId())) {
            throw new UsuarioNoEncontradoException("No se encontró el usuario con ID: " + usuario.getId());
        }
        usuarioRepo.save(usuario);
    }

    @Override
    public List<UsuarioDTO> listarUsuariosDTO() {
        return usuarioMapper.toDTOList(usuarioRepo.findAll());
    }

    @Override
    public List<UsuarioDTO> buscarUsuariosPorTipoDTO(TipoUsuario tipoUsuario) {
        return usuarioMapper.toDTOList(usuarioRepo.findByTipoUsuario(tipoUsuario));
    }

    @Override
    public UsuarioDTO validarCredencialesDTO(String usuario, String password) {
        return usuarioRepo.findByUsuario(usuario)
                .filter(u -> u.getPassword().equals(password))
                .map(usuarioMapper::toDTO)
                .orElseThrow(() -> new CredencialesInvalidasException("Credenciales inválidas para el usuario: " + usuario));
    }

    @Override
    public UsuarioSimpleDTO obtenerUltimoUsuarioCreadoDTO() {
        return usuarioRepo.findTopByOrderByIdDesc()
                .map(usuarioSimpleMapper::toDTO)
                .orElseThrow(() -> new UsuarioNoEncontradoException("No hay usuarios registrados"));
    }

    @Override
    public Usuario buscarPorUsuario(String usuario) {
        return usuarioRepo.findByUsuario(usuario)
                .orElseThrow(() -> new UsuarioNoEncontradoException("Usuario no encontrado: " + usuario));
    }

    @Override
    public void eliminarUsuario(String usuario) {
        usuarioRepo.findByUsuario(usuario)
                .ifPresentOrElse(
                        usuarioRepo::delete,
                        () -> { throw new UsuarioNoEncontradoException("Usuario no encontrado: " + usuario); }
                );
    }

    @Override
    public Usuario buscarUsuarioPorId(String usuario) {
        return usuarioRepo.findByUsuario(usuario)
                .orElseThrow(() -> new UsuarioNoEncontradoException("Usuario no encontrado: " + usuario));
    }

    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioRepo.findAll();
    }

    @Override
    public List<Usuario> buscarUsuariosPorTipo(TipoUsuario tipoUsuario) {
        return new ArrayList<>(usuarioRepo.findByTipoUsuario(tipoUsuario));
    }

    @Override
    public Usuario obtenerUltimoUsuarioCreado() {
        return usuarioRepo.findTopByOrderByIdDesc()
                .orElseThrow(() -> new UsuarioNoEncontradoException("No hay usuarios registrados"));
    }

    @Override
    public Usuario validarCredenciales(String usuario, String password) {
        return usuarioRepo.findByUsuario(usuario)
                .filter(u -> u.getPassword().equals(password))
                .orElseThrow(() -> new CredencialesInvalidasException("Credenciales inválidas para el usuario: " + usuario));
    }
}
