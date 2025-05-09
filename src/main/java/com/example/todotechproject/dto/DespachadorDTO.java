package com.example.todotechproject.dto;

import com.example.todotechproject.dto.UsuarioDTO.UsuarioDTO;
import com.example.todotechproject.modelo.entidades.Usuario;

public record DespachadorDTO (Long id, String nombre, String correo, String telefono,
                         UsuarioDTO usuario){
}
