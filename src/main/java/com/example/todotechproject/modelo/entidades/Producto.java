package com.example.todotechproject.modelo.entidades;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "producto")
@Data
public class Producto {

    //Identificador en tablas
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "producto_seq")
    @SequenceGenerator(name = "producto_seq", sequenceName = "producto_seq", allocationSize = 1)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    //Identificador en TODOTECH

    @Column(name = "codigo", nullable = false)
    private String codigo;

    @Column(name = "descripcion")
    private String descripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    @Column(name = "precio", nullable = false)
    private Double precio;

    @Column(name = "stock", nullable = false)
    private Integer stock;
}
