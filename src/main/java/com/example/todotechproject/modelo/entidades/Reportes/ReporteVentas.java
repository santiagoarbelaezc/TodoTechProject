package com.example.todotechproject.modelo.entidades.Reportes;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "reporte_ventas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReporteVentas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reporte", nullable = false, unique = true)
    private Long id;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "total_ventas", nullable = false)
    private double totalVentas;

    @Column(name = "cantidad_ordenes", nullable = false)
    private int cantidadOrdenes;

}
