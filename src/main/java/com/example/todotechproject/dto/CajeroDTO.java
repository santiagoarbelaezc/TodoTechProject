package com.example.todotechproject.dto;

import com.example.todotechproject.modelo.entidades.Usuario;

public record CajeroDTO (String nombre, String correo, String telefono,
                            Usuario usuario){
}
