package com.example.todotechproject.modelo.entidades;

import jakarta.persistence.*;
import lombok.Data;
@Entity
@Table(name = "categoria")
@Data
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categoria_seq")
    @SequenceGenerator(name = "categoria_seq", sequenceName = "categoria_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    private String descripcion;
}


