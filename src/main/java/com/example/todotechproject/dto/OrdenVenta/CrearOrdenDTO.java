package com.example.todotechproject.dto.OrdenVenta;

import com.example.todotechproject.modelo.entidades.Cliente;
import com.example.todotechproject.modelo.entidades.Vendedor;

public record CrearOrdenDTO(Cliente cliente, String vendedor) { }
