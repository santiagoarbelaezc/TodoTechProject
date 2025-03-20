package com.example.todotechproject.servicios.OrdenVentaServicios;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.todotechproject.modelo.dto.ClienteDTO;
import com.example.todotechproject.modelo.dto.OrdenVentaDTO;
import com.example.todotechproject.modelo.entidades.OrdenVenta;
import com.example.todotechproject.modelo.entidades.Vendedor;
import com.example.todotechproject.modelo.mapper.ClienteMapper;
import com.example.todotechproject.modelo.mapper.OrdenVentaMapper;
import com.example.todotechproject.repositorios.OrdenVentaRepo;
import com.example.todotechproject.servicios.ClienteServicios.ClienteServicio;
import com.example.todotechproject.servicios.DetalleOrdenServicios.DetalleOrdenServicio;
import com.example.todotechproject.servicios.VendedorServicios.VendedorServicio;
import com.example.todotechproject.utils.exceptions.NotFoundException;

@Service
public class OrdenVentaServicioImp implements OrdenVentaServicio {

  private final OrdenVentaRepo ordenVentaRepo;
  private final OrdenVentaMapper ordenVentaMapper;
  private final DetalleOrdenServicio detalleOrdenServicio;
  private final VendedorServicio vendedorServicio;
  private final ClienteServicio clienteServicio;

  OrdenVentaServicioImp(OrdenVentaRepo ordenVentaRepo, OrdenVentaMapper ordenVentaMapper,
      DetalleOrdenServicio detalleOrdenServicio, VendedorServicio vendedorServicio, ClienteServicio clienteServicio) {
    this.ordenVentaRepo = ordenVentaRepo;
    this.ordenVentaMapper = ordenVentaMapper;
    this.detalleOrdenServicio = detalleOrdenServicio;
    this.vendedorServicio = vendedorServicio;
    this.clienteServicio = clienteServicio;
  }

  @Override
  public OrdenVentaDTO save(OrdenVentaDTO ordenVentaDTO) {
    Optional<ClienteDTO> cliente = clienteServicio.getClientById(ordenVentaDTO.cliente().id());
    Vendedor vendedor = vendedorServicio.buscarVendedorPorId(ordenVentaDTO.vendedor().id());
    if (cliente.isEmpty()) {
      throw new NotFoundException("Cliente no encontrado id: " + ordenVentaDTO.cliente().id());
    }
    if (vendedor == null) {
      throw new NotFoundException("Vendedor no encontrado id: " + ordenVentaDTO.vendedor().id());
    }
    OrdenVenta ordenVenta = ordenVentaMapper.toEntity(ordenVentaDTO);
    ordenVenta.setCliente(cliente.map(ClienteMapper.INSTANCE::toEntity).get());
    ordenVenta.setVendedor(vendedor);


    return ordenVentaMapper.toDTO(ordenVentaRepo.save(ordenVenta));
  }

  @Override
  public void delete(Long id) {
    verifyExist(id);
    ordenVentaRepo.deleteById(id);
  }

  @Override
  public OrdenVentaDTO updateOrder(OrdenVentaDTO ordenVenta) {
    verifyExist(ordenVenta.id());
    return ordenVentaMapper.toDTO(ordenVentaRepo.save(ordenVentaMapper.toEntity(ordenVenta)));
  }

  @Override
  public Optional<OrdenVentaDTO> getOrderById(Long id) {
    return ordenVentaRepo.findById(id).map(ordenVentaMapper::toDTO);
  }

  @Override
  public List<OrdenVentaDTO> list() {
    return ordenVentaRepo.findAll().stream().map(ordenVentaMapper::toDTO).collect(Collectors.toList());
  }

  private void verifyExist(Long idOrdenVenta) {
    // Verificar si la orden de venta existe
    Optional<OrdenVenta> existingOrder = ordenVentaRepo.findById(idOrdenVenta);
    if (!existingOrder.isPresent()) {
      throw new NotFoundException("Orden de venta no encontrada id: " + idOrdenVenta);
    }
  }
}
