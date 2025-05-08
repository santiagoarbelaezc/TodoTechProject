package com.example.todotechproject.dto;

import com.example.todotechproject.modelo.entidades.Usuario;

public record DespachadorDTO (String nombre, String correo, String telefono,
                         Usuario usuario){
}
