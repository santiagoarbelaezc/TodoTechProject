package com.example.todotechproject.dto;

import com.example.todotechproject.modelo.entidades.Usuario;

public record VendedorDTO  (String nombre, String correo, String telefono,
                            Usuario usuario){
}
