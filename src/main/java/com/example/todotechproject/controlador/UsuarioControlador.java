package com.example.todotechproject.controlador;

import com.example.todotechproject.dto.UsuarioDTO.UsuarioDTO;
import com.example.todotechproject.utils.Mappers.UsuarioMapper;
import org.springframework.http.HttpStatus;

import com.example.todotechproject.modelo.entidades.Usuario;
import com.example.todotechproject.modelo.enums.TipoUsuario;
import com.example.todotechproject.servicios.UsuarioServicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> obtenerUsuarios() throws Exception {
        List<UsuarioDTO> usuarios = usuarioMapper.toDTOList(usuarioServicio.listarUsuarios());
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/login/{usuario}/{password}")
    public ResponseEntity<UsuarioDTO> login(@PathVariable String usuario, @PathVariable String password) {
        Usuario entidad = usuarioServicio.validarCredenciales(usuario, password);
        return entidad != null
                ? ResponseEntity.ok(usuarioMapper.toDTO(entidad))
                : ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PostMapping("/crear")
    public ResponseEntity<String> crearUsuario(@RequestBody UsuarioDTO usuarioDTO) throws Exception {
        usuarioServicio.crearUsuario(usuarioMapper.toEntity(usuarioDTO));
        return ResponseEntity.ok("Usuario creado correctamente.");
    }

    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizarUsuario(@RequestBody UsuarioDTO usuarioDTO) throws Exception {
        usuarioServicio.actualizarUsuario(usuarioMapper.toEntity(usuarioDTO));
        return ResponseEntity.ok("Usuario actualizado correctamente.");
    }

    @DeleteMapping("/eliminar/{usuario}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable String usuario) {
        usuarioServicio.eliminarUsuario(usuario);
        return ResponseEntity.ok("Usuario eliminado correctamente.");
    }

    @GetMapping("/tipo/{tipoUsuario}")
    public ResponseEntity<List<UsuarioDTO>> obtenerUsuariosPorTipo(@PathVariable TipoUsuario tipoUsuario) {
        List<UsuarioDTO> usuarios = usuarioMapper.toDTOList(usuarioServicio.buscarUsuariosPorTipo(tipoUsuario)); // ✅ Ahora la conversión ocurre aquí
        return ResponseEntity.ok(usuarios);
    }
}