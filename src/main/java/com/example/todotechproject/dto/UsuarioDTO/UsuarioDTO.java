package com.example.todotechproject.dto.UsuarioDTO;

import com.example.todotechproject.modelo.enums.TipoUsuario;

public record UsuarioDTO(
        Long id,
        String usuario,
        String password,
        TipoUsuario tipoUsuario
) {}

