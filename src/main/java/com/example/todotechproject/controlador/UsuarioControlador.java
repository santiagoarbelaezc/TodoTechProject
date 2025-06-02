package com.example.todotechproject.controlador;

import com.example.todotechproject.dto.UsuarioDTO.UsuarioDTO;
import com.example.todotechproject.dto.UsuarioDTO.UsuarioSimpleDTO;
import com.example.todotechproject.modelo.enums.TipoUsuario;
import com.example.todotechproject.servicios.UsuarioServicios.UsuarioServicio;
import com.example.todotechproject.utils.Mappers.Usuarios.UsuarioMapper;
import com.example.todotechproject.utils.Mappers.Usuarios.UsuarioSimpleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/api/usuarios")

public class UsuarioControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listarUsuarios() throws Exception {
        var usuarios = usuarioServicio.listarUsuariosDTO();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/tipo/{tipoUsuario}")
    public ResponseEntity<List<UsuarioDTO>> listarPorTipo(@PathVariable TipoUsuario tipoUsuario) {
        var usuarios = usuarioServicio.buscarUsuariosPorTipoDTO(tipoUsuario);
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/ultimo")
    public ResponseEntity<UsuarioSimpleDTO> obtenerUltimo() {
        var usuario = usuarioServicio.obtenerUltimoUsuarioCreadoDTO();
        return usuario != null
                ? ResponseEntity.ok(usuario)
                : ResponseEntity.notFound().build();
    }

    @GetMapping("/login/{usuario}/{password}")
    public ResponseEntity<UsuarioDTO> login(@PathVariable String usuario, @PathVariable String password) {
        var dto = usuarioServicio.validarCredencialesDTO(usuario, password);
        return dto != null
                ? ResponseEntity.ok(dto)
                : ResponseEntity.status(401).build();
    }

    @PostMapping("/crear")
    public ResponseEntity<Map<String, String>> crear(@RequestBody UsuarioSimpleDTO dto) throws Exception {
        usuarioServicio.crearUsuarioDesdeDTO(dto);
        return ResponseEntity.ok(Map.of("mensaje", "Usuario creado correctamente"));
    }

    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizar(@RequestBody UsuarioDTO dto) throws Exception {
        usuarioServicio.actualizarUsuarioDesdeDTO(dto);
        return ResponseEntity.ok("Usuario actualizado correctamente.");
    }

    @DeleteMapping("/eliminar/{usuario}")
    public ResponseEntity<String> eliminar(@PathVariable String usuario) {
        usuarioServicio.eliminarUsuario(usuario);
        return ResponseEntity.ok("Usuario eliminado correctamente.");
    }
}

