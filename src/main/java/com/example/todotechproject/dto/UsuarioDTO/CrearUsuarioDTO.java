package com.example.todotechproject.dto.UsuarioDTO;

import com.example.todotechproject.modelo.enums.TipoUsuario;

public record CrearUsuarioDTO(
        String usuario,
        String password,
        TipoUsuario tipoUsuario,
        String nombre,
        String correo,
        String telefono
) {}
