package modelo.entidades;

import jakarta.persistence.*;
import lombok.Data;
import modelo.enums.EstadoOrden;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orden_venta")
@Data
public class OrdenVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orden_venta_seq")
    @SequenceGenerator(name = "orden_venta_seq", sequenceName = "orden_venta_seq", allocationSize = 1)
    private Long id;

    @Column(name = "fecha", nullable = false)
    private Date fecha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendedor_id", nullable = false)
    private Vendedor vendedor;

    @OneToMany(mappedBy = "ordenVenta", cascade = CascadeType.ALL)
    private List<DetalleOrden> productos;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoOrden estado;

    @Column(name = "total", nullable = false)
    private Double total;
}
