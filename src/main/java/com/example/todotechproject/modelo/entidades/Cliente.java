package com.example.todotechproject.modelo.entidades;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;
import java.util.Date;

@Entity
@Table(name = "cliente")
@Data
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String correo;

    @Column(nullable = false)
    private String telefono;

    @Column(nullable = false)
    private String claveSecreta;
}