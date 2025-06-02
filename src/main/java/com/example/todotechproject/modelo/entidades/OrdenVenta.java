package com.example.todotechproject.modelo.entidades;

import com.example.todotechproject.modelo.enums.EstadoOrden;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orden_venta")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrdenVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orden_venta_seq")
    @SequenceGenerator(name = "orden_venta_seq", sequenceName = "orden_venta_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private Date fecha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendedor_id", nullable = false)
    private Trabajador trabajador;

    @OneToMany(mappedBy = "ordenVenta", cascade = CascadeType.ALL)
    private List<DetalleOrden> productos;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoOrden estado;

    @Column(nullable = false)
    private Double total;
}

