package com.example.todotechproject.dto.UsuarioDTO;

import com.example.todotechproject.modelo.enums.TipoUsuario;

public record UsuarioDTO2(
        String usuario,
        String password,
        TipoUsuario tipoUsuario
) {}

