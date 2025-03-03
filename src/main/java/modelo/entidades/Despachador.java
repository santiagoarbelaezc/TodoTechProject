package modelo.entidades;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "despachador")
@Data
public class Despachador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String correo;

    @Column(nullable = false)
    private String telefono;

    @Column(nullable = false, unique = true)
    private String usuario;

    @Column(nullable = false)
    private String contraseña;
}
