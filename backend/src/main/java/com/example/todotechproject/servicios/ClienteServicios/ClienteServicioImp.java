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
    if (id == null) {
      return Optional.empty();
    }
    return clienteRepo.findById(id).map(clienteMapper::toDto);
  }

  @Override
  public Optional<ClienteDTO> getClientByCorreo(String correo) {
    if (correo == null) {
      return Optional.empty();
    }
    return clienteRepo.findByCorreo(correo).map(clienteMapper::toDto);
  }

  @Override
  public Optional<ClienteDTO> getClientByIdAndCorreo(Long id, String email) {
    Optional<ClienteDTO> cliente = getClientById(id);
    if (cliente.isPresent()) {
      return cliente;
    } else {
      cliente = getClientByCorreo(email);
    }

    return cliente;
  }
}
