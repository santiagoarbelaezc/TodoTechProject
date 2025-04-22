package com.example.todotechproject.servicios.UsuarioServicios;

import com.example.todotechproject.dto.UsuarioDTO.UsuarioDTO;
import com.example.todotechproject.excepciones.UsuarioNoEncontradoException;
import com.example.todotechproject.modelo.entidades.Usuario;
import com.example.todotechproject.modelo.enums.TipoUsuario;

import com.example.todotechproject.repositorios.UsuarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioServicioImp implements UsuarioServicio {

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Override
    public void crearUsuario(Usuario usuario) throws Exception {
        if (usuarioRepo.existsById(usuario.getUsuario())) {
            throw new Exception("El usuario ya existe");
        }
        usuarioRepo.save(usuario);
    }

    @Override
    public void actualizarUsuario(Usuario usuario) throws Exception {
        if (!usuarioRepo.existsById(usuario.getUsuario())) {
            throw new Exception("El usuario no existe");
        }
        usuarioRepo.save(usuario);
    }

    @Override
    public Usuario buscarPorUsuario(String usuario) {
        return usuarioRepo.findByUsuario(usuario)
                .orElseThrow(() -> new UsuarioNoEncontradoException("Usuario no encontrado: " + usuario));
    }




    @Override
    public void eliminarUsuario(Usuario usuario) throws Exception {
        if (!usuarioRepo.existsById(usuario.getUsuario())) {
            throw new Exception("El usuario no existe");
        }
        usuarioRepo.delete(usuario);
    }

    @Override
    public void buscarUsuario(Usuario usuario) throws Exception {
        if (!usuarioRepo.existsById(usuario.getUsuario())) {
            throw new Exception("El usuario no existe");
        }
    }

    @Override
    public List<Usuario> listarUsuarios() throws Exception {
        return usuarioRepo.findAll();
    }

    @Override
    public Usuario buscarUsuarioPorId(String usuario) {
        Optional<Usuario> resultado = usuarioRepo.findById(usuario);
        return resultado.orElse(null);
    }

    @Override
    public List<Usuario> buscarUsuariosPorTipo(TipoUsuario tipoUsuario) {
        return new ArrayList<>(usuarioRepo.findByTipoUsuario(tipoUsuario)); // ✅ Se mantiene como Lista<Usuario>
    }

    @Override
    public Usuario validarCredenciales(String usuario, String password) {
        Usuario entidad = usuarioRepo.findByUsuario(usuario)
                .orElseThrow(() -> new UsuarioNoEncontradoException("Usuario no encontrado: " + usuario));
        return entidad.getPassword().equals(password) ? entidad : null;
    }

    @Override
    public void eliminarUsuario(String usuario) {
        usuarioRepo.deleteById(usuario);
    }
}
