package com.example.todotechproject.dto.UsuarioDTO;

import com.example.todotechproject.modelo.enums.TipoUsuario;

public record UsuarioDTO(
        Long id, // ✅ Nuevo campo ID
        String usuario,
        String password,
        TipoUsuario tipoUsuario
) {}

