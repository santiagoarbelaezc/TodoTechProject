package com.example.todotechproject.servicios.ClienteServicios;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.todotechproject.modelo.dto.ClienteDTO;
import com.example.todotechproject.modelo.mapper.ClienteMapper;
import com.example.todotechproject.repositorios.ClienteRepo;

@Service
public class ClienteServicioImp implements ClienteServicio {

  private final ClienteRepo clienteRepo;
  private final ClienteMapper clienteMapper;

  public ClienteServicioImp(ClienteRepo clienteRepo, ClienteMapper clienteMapper) {
    this.clienteRepo = clienteRepo;
    this.clienteMapper = clienteMapper;
  }

  @Override
  public Optional<ClienteDTO> getClientById(Long id) {
    return clienteRepo.findById(id).map(clienteMapper::toDto);
  }
}
