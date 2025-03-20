package com.example.todotechproject.modelo.dto.UsuarioDTO;

import lombok.Builder;

import com.example.todotechproject.modelo.enums.TipoUsuario;

@Builder
public record UsuarioDTO(String usuario, String password, TipoUsuario tipoUsuario) {

}
