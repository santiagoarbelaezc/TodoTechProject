package com.example.todotechproject.modelo.entidades;

import com.example.todotechproject.modelo.enums.TipoUsuario;
import jakarta.persistence.*;
import lombok.Data;
@Entity
@Table(name = "usuario")
@Data
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_seq")
    @SequenceGenerator(name = "usuario_seq", sequenceName = "usuario_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false, unique = true)
    private String usuario;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_usuario", nullable = false)
    private TipoUsuario tipoUsuario;

}

