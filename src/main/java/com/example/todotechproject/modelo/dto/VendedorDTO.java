package com.example.todotechproject.modelo.dto;

import com.example.todotechproject.modelo.dto.UsuarioDTO.UsuarioDTO;

public record VendedorDTO(Long id, String nombre, String correo, String telefono,
                          UsuarioDTO usuario) {
}
