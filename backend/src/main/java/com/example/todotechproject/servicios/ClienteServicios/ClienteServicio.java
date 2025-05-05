package com.example.todotechproject.servicios.ClienteServicios;

import java.util.Optional;

import com.example.todotechproject.modelo.dto.ClienteDTO;

public interface ClienteServicio {
  Optional<ClienteDTO> getClientById(Long id);
  Optional<ClienteDTO> getClientByCorreo(String email);
  Optional<ClienteDTO> getClientByIdAndCorreo(Long id, String email);
}
