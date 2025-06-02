package com.example.todotechproject.dto.UsuarioDTO;

import com.example.todotechproject.modelo.enums.TipoUsuario;

public record UsuarioSimpleDTO(
        String usuario,
        String password,
        TipoUsuario tipoUsuario
) {}

