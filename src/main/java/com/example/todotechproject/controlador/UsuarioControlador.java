package com.example.todotechproject.controlador;

import com.example.todotechproject.dto.UsuarioDTO.UsuarioDTO;
import com.example.todotechproject.modelo.enums.TipoUsuario;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioControlador {
    //PRUEBA SIN BASE DE DATOS
    @GetMapping
    public List<UsuarioDTO> obtenerUsuarios() {
        return Arrays.asList(
                new UsuarioDTO("admin", "admin123", TipoUsuario.ADMINISTRADOR),
                new UsuarioDTO("vendedor", "password1", TipoUsuario.VENDEDOR)
        );
    }

    @GetMapping("/{usuario}")
    public UsuarioDTO obtenerUsuarioPorNombre(@PathVariable String usuario) {
        return new UsuarioDTO(usuario, "passwordMock", TipoUsuario.VENDEDOR);
    }

    @PostMapping("/crear")
    public String crearUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        return "Usuario " + usuarioDTO.usuario() + " creado correctamente.";
    }

    @DeleteMapping("/eliminar/{usuario}")
    public String eliminarUsuario(@PathVariable String usuario) {
        return "Usuario " + usuario + " eliminado correctamente.";
    }
}
