package com.example.todotechproject.modelo.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cajero")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cajero {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cajero_seq")
    @SequenceGenerator(name = "cajero_seq", sequenceName = "cajero_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String correo;

    @Column(nullable = false)
    private String telefono;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id", nullable = false, unique = true)
    private Usuario usuario;
}
