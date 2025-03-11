package com.example.todotechproject.modelo.entidades;

import com.example.todotechproject.modelo.enums.TipoUsuario;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "reporte_usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReporteUsuarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reporte", nullable = false, unique = true)
    private Long id;

    @Column(name = "nombre_usuario", nullable = false)
    private String nombreUsuario;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_usuario", nullable = false)
    private TipoUsuario tipoUsuario;

    @Column(name = "activo", nullable = false)
    private boolean activo;
}
