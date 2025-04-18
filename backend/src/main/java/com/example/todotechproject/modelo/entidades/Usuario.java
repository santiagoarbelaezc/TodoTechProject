package com.example.todotechproject.modelo.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import com.example.todotechproject.modelo.enums.TipoUsuario;

@Entity
@Table(name = "usuario")
@Data
@Getter
@Setter
public class Usuario {

  @Id
  @Column(name = "id_usuario", nullable = false, unique = true)
  private String usuario;

  @Column(name = "password", nullable = false)
  private String password;

  //DEPENDIENDO DEL TIPO DE USUARIO, INGRESA A SU INTERFAZ CORRESPONDIENTE

  @Column(name = "tipo", nullable = false)
  @Enumerated(EnumType.STRING)
  private TipoUsuario tipoUsuario;
}
