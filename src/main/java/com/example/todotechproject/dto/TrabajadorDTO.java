package com.example.todotechproject.dto;

import com.example.todotechproject.dto.UsuarioDTO.UsuarioDTO;

public record TrabajadorDTO(Long id, String nombre, String correo, String telefono,
                            UsuarioDTO usuario) {
}
