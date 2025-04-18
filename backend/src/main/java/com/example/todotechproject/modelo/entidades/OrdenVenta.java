package com.example.todotechproject.modelo.entidades;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

import com.example.todotechproject.modelo.enums.EstadoOrden;
@Entity
@Table(name = "orden_venta")
@Data
public class OrdenVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orden_venta_seq")
    @SequenceGenerator(name = "orden_venta_seq", sequenceName = "orden_venta_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private Date fecha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", referencedColumnName = "id", nullable = false)
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendedor_id", referencedColumnName = "id", nullable = false)
    private Vendedor vendedor;

    @OneToMany(mappedBy = "id", cascade = CascadeType.REFRESH)
    private List<DetalleOrden> productos;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoOrden estado;

    @Column(nullable = false)
    private Double total;
}

