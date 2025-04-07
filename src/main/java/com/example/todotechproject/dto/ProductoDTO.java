package com.example.todotechproject.dto;

import lombok.Data;

@Data
public class ProductoDTO {
    private Long id;
    private String nombre;
    private String codigo;
    private String descripcion;
    private String categoria; // nombre de la categoría
    private Double precio;
    private Integer stock;
    private String imagen;
}
