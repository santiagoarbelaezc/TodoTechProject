package com.example.todotechproject.modelo.entidades.Reportes;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "reporte_stock")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReporteStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reporte", nullable = false, unique = true)
    private Long id;

    @Column(name = "nombre_producto", nullable = false)
    private String nombreProducto;

    @Column(name = "codigo_producto", nullable = false, unique = true)
    private String codigoProducto;

    @Column(name = "stock_actual", nullable = false)
    private int stockActual;

    @Column(name = "stock_minimo", nullable = false)
    private int stockMinimo;
}
