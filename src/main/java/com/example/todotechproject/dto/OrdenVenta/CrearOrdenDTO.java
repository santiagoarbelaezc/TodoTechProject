package com.example.todotechproject.dto.OrdenVenta;

import com.example.todotechproject.modelo.entidades.Cliente;

public record CrearOrdenDTO(Cliente cliente, String vendedor) { }
