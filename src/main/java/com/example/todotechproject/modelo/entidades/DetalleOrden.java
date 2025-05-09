package com.example.todotechproject.modelo.entidades;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "detalle_orden")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetalleOrden {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "detalle_orden_seq")
    @SequenceGenerator(name = "detalle_orden_seq", sequenceName = "detalle_orden_seq", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(nullable = false)
    private Double subtotal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orden_venta_id")
    private OrdenVenta ordenVenta;
}


