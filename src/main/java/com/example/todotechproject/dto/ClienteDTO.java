package com.example.todotechproject.dto;

public class ClienteDTO {

    @NotNull @NotBlank @Length(max =100) String nombre,
    @NotNull  @NotBlank @Length (max =50) String email,
    @NotNull  @NotBlank @Length (max =100) String ciudad,
    @NotNull  @NotBlank @Length (max =100) String direccion,
    @NotNull @NotBlank @Length (min = 7, max =15) String password,

}
