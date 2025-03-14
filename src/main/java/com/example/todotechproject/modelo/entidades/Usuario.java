package com.example.todotechproject.modelo.entidades;

import com.example.todotechproject.modelo.enums.TipoUsuario;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import jakarta.persistence.Table;
import lombok.*;
@Getter
@Setter

@Entity
@Table(name = "usuario")
@Data

public class Usuario {

    @Id
    @Column(name = "id_usuario", nullable = false, unique = true)
    private String usuario;

    @Column(name = "password", nullable = false)
    private String password;

    //DEPENDIENDO DEL TIPO DE USUARIO, INGRESA A SU INTERFAZ CORRESPONDIENTE

    @Column(name = "tipo", nullable = false)
    private TipoUsuario tipoUsuario;

}
