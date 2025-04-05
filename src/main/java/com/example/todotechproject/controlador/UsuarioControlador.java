package com.example.todotechproject.controlador;

import com.example.todotechproject.dto.UsuarioDTO.UsuarioDTO;
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

    @GetMapping
    public List<Usuario> obtenerUsuarios() throws Exception {
        return usuarioServicio.listarUsuarios();
    }

    @GetMapping("/{usuario}")
    public ResponseEntity<UsuarioDTO> obtenerUsuario(@PathVariable String usuario) {
        Optional<Usuario> optionalUsuario = Optional.ofNullable(usuarioServicio.buscarPorUsuario(usuario));
        if (optionalUsuario.isPresent()) {
            Usuario entidad = optionalUsuario.get();
            UsuarioDTO dto = new UsuarioDTO(
                    entidad.getUsuario(),
                    entidad.getPassword(),
                    entidad.getTipoUsuario()
            );
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    @PostMapping("/crear")
    public String crearUsuario(@RequestBody Usuario usuario) throws Exception {
        usuarioServicio.crearUsuario(usuario);
        return "Usuario " + usuario.getUsuario() + " creado correctamente.";
    }

    @PutMapping("/actualizar")
    public String actualizarUsuario(@RequestBody Usuario usuario) throws Exception {
        usuarioServicio.actualizarUsuario(usuario);
        return "Usuario " + usuario.getUsuario() + " actualizado correctamente.";
    }

    @DeleteMapping("/eliminar/{usuario}")
    public String eliminarUsuario(@PathVariable String usuario) {
        usuarioServicio.eliminarUsuario(usuario);
        return "Usuario " + usuario + " eliminado correctamente.";
    }

    @GetMapping("/tipo/{tipoUsuario}")
    public List<UsuarioDTO> obtenerUsuariosPorTipo(@PathVariable TipoUsuario tipoUsuario) {
        return usuarioServicio.buscarUsuariosPorTipo(tipoUsuario);
    }
}
