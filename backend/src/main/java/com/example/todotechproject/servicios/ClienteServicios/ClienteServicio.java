package com.example.todotechproject.servicios.ClienteServicios;

import java.util.Optional;

import com.example.todotechproject.modelo.dto.ClienteDTO;

public interface ClienteServicio {
  public Optional<ClienteDTO> getClientById(Long id);
}
