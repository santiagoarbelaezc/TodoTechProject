package com.example.todotechproject.controlador;


import com.example.todotechproject.dto.UsuarioDTO.UsuarioDTO;
import com.example.todotechproject.modelo.entidades.Usuario;
import com.example.todotechproject.modelo.enums.TipoUsuario;

import com.example.todotechproject.servicios.UsuarioServicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioControlador {

    @Autowired
    private UsuarioServicio usuarioService;

    // Crear usuario
    @PostMapping("/crear")
    public ResponseEntity<String> crearUsuario(@RequestBody UsuarioDTO usuarioDTO) throws Exception {
        Usuario usuario = new Usuario();
        usuario.setUsuario(usuarioDTO.usuario());
        usuario.setPassword(usuarioDTO.password());
        usuario.setTipoUsuario(usuarioDTO.tipoUsuario());

        usuarioService.crearUsuario(usuario);
        return ResponseEntity.ok("Usuario creado correctamente");
    }

    // Actualizar usuario
    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizarUsuario(@RequestBody UsuarioDTO usuarioDTO) throws Exception {
        Usuario usuario = new Usuario();
        usuario.setUsuario(usuarioDTO.usuario());
        usuario.setPassword(usuarioDTO.password());
        usuario.setTipoUsuario(usuarioDTO.tipoUsuario());

        usuarioService.actualizarUsuario(usuario);
        return ResponseEntity.ok("Usuario actualizado correctamente");
    }

    // Eliminar usuario
    @DeleteMapping("/eliminar/{usuario}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable String usuario) {
        usuarioService.eliminarUsuario(usuario);
        return ResponseEntity.ok("Usuario eliminado correctamente");
    }

    // Buscar usuario por nombre de usuario
    @GetMapping("/buscar/{usuario}")
    public ResponseEntity<UsuarioDTO> buscarUsuario(@PathVariable String usuario) {
        Usuario encontrado = usuarioService.buscarUsuarioPorId(usuario);
        if (encontrado == null) {
            return ResponseEntity.notFound().build();
        }
        UsuarioDTO usuarioDTO = new UsuarioDTO(encontrado.getUsuario(), encontrado.getPassword(), encontrado.getTipoUsuario());
        return ResponseEntity.ok(usuarioDTO);
    }

    // Buscar usuarios por tipo de usuario
    @GetMapping("/buscarPorTipo/{tipoUsuario}")
    public ResponseEntity<List<UsuarioDTO>> buscarUsuariosPorTipo(@PathVariable TipoUsuario tipoUsuario) {
        List<UsuarioDTO> usuarios = usuarioService.buscarUsuariosPorTipo(tipoUsuario);
        return ResponseEntity.ok(usuarios);
    }
}
