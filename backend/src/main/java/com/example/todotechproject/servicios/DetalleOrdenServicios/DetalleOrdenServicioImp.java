package com.example.todotechproject.servicios.DetalleOrdenServicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.todotechproject.modelo.dto.DetalleOrdenDTO;
import com.example.todotechproject.modelo.entidades.DetalleOrden;
import com.example.todotechproject.modelo.entidades.OrdenVenta;
import com.example.todotechproject.modelo.entidades.Producto;
import com.example.todotechproject.modelo.mapper.DetalleOrdenMapper;
import com.example.todotechproject.repositorios.DetalleOrdenRepo;
import com.example.todotechproject.repositorios.ProductoRepo;
import com.example.todotechproject.exceptions.NotFoundException;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DetalleOrdenServicioImp implements DetalleOrdenServicio {

  @Autowired
  private DetalleOrdenRepo detalleOrdenRepo;
  @Autowired
  private ProductoRepo productoRepo;
  @Autowired
  private DetalleOrdenMapper detalleOrdenMapper;

  @Override
  public Optional<DetalleOrdenDTO> save(DetalleOrdenDTO detalleOrdenDTO) {
    Producto producto = getProducto(detalleOrdenDTO);
    if (detalleOrdenDTO.id() != null) {
      Optional<DetalleOrden> existingDetalleOrden = detalleOrdenRepo.findById(detalleOrdenDTO.id());
      if (existingDetalleOrden.isPresent()) {
        DetalleOrden detalleOrden = existingDetalleOrden.get();
        updateDetalleOrden(detalleOrden, detalleOrdenDTO, producto);
        return Optional.of(detalleOrdenMapper.toDto(detalleOrdenRepo.save(detalleOrden)));
      }
    }

    DetalleOrden newDetalleOrden = createDetalleOrden(detalleOrdenDTO, producto);
    actualizarProducto(producto, newDetalleOrden);
    return Optional.of(detalleOrdenMapper.toDto(detalleOrdenRepo.save(newDetalleOrden)));
  }

  @Transactional
  public synchronized List<DetalleOrdenDTO> save(List<DetalleOrdenDTO> detalleOrdenDTO, OrdenVenta ordenVenta) {
    List<DetalleOrdenDTO> listadetalle = new ArrayList<>();
    for (int i = 0; i < detalleOrdenDTO.size(); i++) {

      save(detalleOrdenDTO.get(i).withOrdenVenta(ordenVenta.getId())).ifPresent(listadetalle::add);
    }
    return listadetalle;
  }

  void actualizarProducto(Producto producto, DetalleOrden detalleOrden){
    if(producto.getStock() >= detalleOrden.getCantidad() && detalleOrden.getCantidad()!= 0){
      int update = producto.getStock() - detalleOrden.getCantidad();
      producto.setStock(update);
      productoRepo.save(producto);
    } else {
      throw new NotFoundException("No existen suficientes productos en stock para realizar la compra " + producto.getStock());

    }
  }

  private void updateDetalleOrden(DetalleOrden detalleOrden, DetalleOrdenDTO detalleOrdenDTO, Producto producto) {
    detalleOrden.setCantidad(detalleOrdenDTO.cantidad());
    detalleOrden.setProducto(producto);
    detalleOrden.setSubtotal(detalleOrdenDTO.cantidad() * producto.getPrecio());
    OrdenVenta ordenVenta = new OrdenVenta();
    ordenVenta.setId(detalleOrdenDTO.idOrdenVenta());
    detalleOrden.setOrdenVenta(ordenVenta);
  }

  private DetalleOrden createDetalleOrden(DetalleOrdenDTO detalleOrdenDTO, Producto producto) {
    DetalleOrden detalleOrden = detalleOrdenMapper.toEntity(detalleOrdenDTO);
    detalleOrden.setProducto(producto);
    detalleOrden.setSubtotal(detalleOrdenDTO.cantidad() * producto.getPrecio());
    OrdenVenta ordenVenta = new OrdenVenta();
    ordenVenta.setId(detalleOrdenDTO.idOrdenVenta());
    detalleOrden.setOrdenVenta(ordenVenta);
    return detalleOrden;
  }

  private Producto getProducto(DetalleOrdenDTO detalleOrdenDTO) {
    Optional<Producto> producto = productoRepo.findById(detalleOrdenDTO.producto().id());
    if (producto.isEmpty()) {
      throw new NotFoundException("El producto no existe id: " + detalleOrdenDTO.producto().id());
    }
    return producto.get();
  }

  @Override
  public Optional<DetalleOrdenDTO> findById(Long id) {
    return detalleOrdenRepo.findById(id).map(detalleOrdenMapper::toDto);
  }

  @Override
  public List<DetalleOrdenDTO> findAll() {
    return detalleOrdenRepo.findAll().stream().map(detalleOrdenMapper::toDto).collect(Collectors.toList());
  }

  @Override
  public List<DetalleOrdenDTO> findByOrdenVenta(Long id) {
    OrdenVenta ordenVenta = new OrdenVenta();
    ordenVenta.setId(id);

    return detalleOrdenRepo.findByOrdenVenta(ordenVenta).stream()
        .map(detalleOrdenMapper::toDto)
        .collect(Collectors.toList());
  }

  @Override
  public void delete(Long id) {
    detalleOrdenRepo.deleteById(id);
  }
}
