package com.example.todotechproject.modelo.entidades;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "vendedor")
@Data
public class Vendedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String correo;

    @Column(nullable = false)
    private String telefono;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario ", nullable = false)
    private Usuario usuario;
;

}