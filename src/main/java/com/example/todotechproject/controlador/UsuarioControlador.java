package com.example.todotechproject.controlador;

import com.example.todotechproject.dto.UsuarioDTO.CrearUsuarioDTO;
import com.example.todotechproject.dto.UsuarioDTO.UsuarioDTO;
import com.example.todotechproject.dto.UsuarioDTO.UsuarioDTO2;
import com.example.todotechproject.utils.Mappers.Usuarios.UsuarioMapper;
import com.example.todotechproject.utils.Mappers.Usuarios.UsuarioMapper2;
import org.springframework.http.HttpStatus;

import com.example.todotechproject.modelo.entidades.Usuario;
import com.example.todotechproject.modelo.enums.TipoUsuario;
import com.example.todotechproject.servicios.UsuarioServicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Autowired
    private UsuarioMapper2 usuarioMapper2;


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
    public ResponseEntity<Map<String, String>> crearUsuario(@RequestBody UsuarioDTO2 usuarioDTO2) throws Exception {
        usuarioServicio.crearUsuario2(usuarioMapper2.toEntity(usuarioDTO2));
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "Usuario creado correctamente");
        return ResponseEntity.ok(response);
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


    @GetMapping("/ultimo")
    public ResponseEntity<UsuarioDTO2> obtenerUltimoUsuario() {
        Usuario ultimo = usuarioServicio.obtenerUltimoUsuarioCreado();
        if (ultimo == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuarioMapper2.toDTO(ultimo));
    }

}