package com.example.todotechproject.servicios.DetalleOrdenServicios;

import com.example.todotechproject.dto.DetalleOrden.*;
import com.example.todotechproject.dto.OrdenVenta.OrdenVentaDTO;
import com.example.todotechproject.dto.OrdenVenta.OrdenVentaDescuentoRequest;
import com.example.todotechproject.dto.ProductoDTO;

import com.example.todotechproject.excepciones.DetalleOrdenExcepciones.OperacionInvalidaException;
import com.example.todotechproject.excepciones.DetalleOrdenExcepciones.RecursoNoEncontradoException;
import com.example.todotechproject.excepciones.DetalleOrdenExcepciones.StockInsuficienteException;
import com.example.todotechproject.modelo.entidades.DetalleOrden;
import com.example.todotechproject.modelo.entidades.OrdenVenta;
import com.example.todotechproject.modelo.entidades.Producto;
import com.example.todotechproject.repositorios.DetalleOrdenRepo;
import com.example.todotechproject.repositorios.OrdenVentaRepo;
import com.example.todotechproject.repositorios.ProductoRepo;
import com.example.todotechproject.servicios.OrdenVentaServicios.OrdenVentaServicio;
import com.example.todotechproject.utils.Mappers.DetalleOrdenMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DetalleOrdenServicioImp implements DetalleOrdenServicio {

    @Autowired
    private DetalleOrdenRepo detalleOrdenRepo;

    @Autowired
    private ProductoRepo productoRepo;

    @Autowired
    private OrdenVentaRepo ordenVentaRepo;

    @Autowired
    private OrdenVentaServicio ordenVentaServicio;

    @Override
    public DetalleOrdenDTO crearDetalle(ProductoDTO productoDTO, Long ordenVentaId) {
        Producto producto = productoRepo.findById(productoDTO.getId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Producto no encontrado"));

        OrdenVenta orden = ordenVentaRepo.findById(ordenVentaId)
                .orElseThrow(() -> new RecursoNoEncontradoException("Orden de venta no encontrada"));

        if (producto.getStock() <= 0) {
            throw new StockInsuficienteException("Stock insuficiente para el producto");
        }

        DetalleOrden detalleExistente = detalleOrdenRepo
                .findByProductoIdAndOrdenVentaId(producto.getId(), orden.getId())
                .orElse(null);

        if (detalleExistente != null) {
            int nuevaCantidad = detalleExistente.getCantidad() + 1;
            detalleExistente.setCantidad(nuevaCantidad);
            detalleExistente.setSubtotal(producto.getPrecio() * nuevaCantidad);
            DetalleOrden actualizado = detalleOrdenRepo.save(detalleExistente);

            producto.setStock(producto.getStock() - 1);
            productoRepo.save(producto);
            actualizarTotalOrden(orden);

            return DetalleOrdenMapper.toDTO(actualizado);
        }

        DetalleOrden nuevoDetalle = new DetalleOrden();
        nuevoDetalle.setProducto(producto);
        nuevoDetalle.setCantidad(1);
        nuevoDetalle.setSubtotal(producto.getPrecio());
        nuevoDetalle.setOrdenVenta(orden);

        DetalleOrden guardado = detalleOrdenRepo.save(nuevoDetalle);

        producto.setStock(producto.getStock() - 1);
        productoRepo.save(producto);
        actualizarTotalOrden(orden);

        return DetalleOrdenMapper.toDTO(guardado);
    }

    private void actualizarTotalOrden(OrdenVenta orden) {
        Double nuevoTotal = orden.getProductos().stream()
                .mapToDouble(DetalleOrden::getSubtotal)
                .sum();
        orden.setTotal(nuevoTotal);
        ordenVentaRepo.save(orden);
    }

    public ResponseEntity<DetalleOrdenDTO> crear(CrearDetalleRequest request) {
        try {
            DetalleOrdenDTO dto = crearDetalle(request.getProducto(), request.getOrdenVentaId());
            return ResponseEntity.status(HttpStatus.CREATED).body(dto);
        } catch (RecursoNoEncontradoException | StockInsuficienteException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @Override
    public boolean eliminarPorProductoYOrden(Long productoId, Long ordenVentaId) {
        DetalleOrden detalle = detalleOrdenRepo.findByProductoIdAndOrdenVentaId(productoId, ordenVentaId)
                .orElseThrow(() -> new RecursoNoEncontradoException("Detalle de orden no encontrado"));

        Producto producto = detalle.getProducto();
        producto.setStock(producto.getStock() + detalle.getCantidad());
        productoRepo.save(producto);

        detalleOrdenRepo.delete(detalle);

        actualizarTotalOrden(detalle.getOrdenVenta());

        return true;
    }

    @Override
    public List<DetalleOrdenDTO> obtenerPorOrdenVenta(Long id) {
        List<DetalleOrden> detalles = detalleOrdenRepo.findByOrdenVentaId(id);
        return detalles.stream()
                .map(DetalleOrdenMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<String> eliminar(EliminarDetalleRequest request) {
        try {
            boolean eliminado = eliminarPorProductoYOrden(
                    request.productoId(),
                    request.ordenVentaId()
            );

            String responseMessage = eliminado
                    ? "{\"message\": \"Detalle eliminado correctamente\"}"
                    : "{\"message\": \"No se encontró el detalle\"}";

            return ResponseEntity.status(eliminado ? HttpStatus.OK : HttpStatus.NOT_FOUND)
                    .body(responseMessage);

        } catch (RecursoNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

    @Override
    public DetalleOrdenDTO aumentarCantidad(Long productoId, Long ordenVentaId) {
        DetalleOrden detalle = detalleOrdenRepo.findByProductoIdAndOrdenVentaId(productoId, ordenVentaId)
                .orElseThrow(() -> new RecursoNoEncontradoException("Detalle de orden no encontrado"));

        Producto producto = detalle.getProducto();
        if (producto.getStock() <= 0) {
            throw new StockInsuficienteException("No hay stock disponible");
        }

        int nuevaCantidad = detalle.getCantidad() + 1;
        detalle.setCantidad(nuevaCantidad);
        detalle.setSubtotal(producto.getPrecio() * nuevaCantidad);

        producto.setStock(producto.getStock() - 1);
        productoRepo.save(producto);
        detalleOrdenRepo.save(detalle);

        OrdenVenta orden = ordenVentaRepo.findById(ordenVentaId)
                .orElseThrow(() -> new RecursoNoEncontradoException("Orden de venta no encontrada"));
        actualizarTotalOrden(orden);

        return DetalleOrdenMapper.toDTO(detalle);
    }

    @Override
    public DetalleOrdenDTO disminuirCantidad(Long productoId, Long ordenVentaId) {
        DetalleOrden detalle = detalleOrdenRepo.findByProductoIdAndOrdenVentaId(productoId, ordenVentaId)
                .orElseThrow(() -> new RecursoNoEncontradoException("Detalle de orden no encontrado"));

        if (detalle.getCantidad() <= 1) {
            throw new OperacionInvalidaException("No se puede disminuir más la cantidad");
        }

        int nuevaCantidad = detalle.getCantidad() - 1;
        detalle.setCantidad(nuevaCantidad);
        detalle.setSubtotal(detalle.getProducto().getPrecio() * nuevaCantidad);

        Producto producto = detalle.getProducto();
        producto.setStock(producto.getStock() + 1);
        productoRepo.save(producto);
        detalleOrdenRepo.save(detalle);

        OrdenVenta orden = ordenVentaRepo.findById(ordenVentaId)
                .orElseThrow(() -> new RecursoNoEncontradoException("Orden de venta no encontrada"));
        actualizarTotalOrden(orden);

        return DetalleOrdenMapper.toDTO(detalle);
    }

    public ResponseEntity<List<DetalleOrdenDTO>> obtenerDetallesPorOrden(Long ordenId) {
        try {
            List<DetalleOrdenDTO> detalles = obtenerPorOrdenVenta(ordenId);
            return ResponseEntity.ok(detalles);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    public ResponseEntity<?> aplicarDescuentoOrden(AplicarDescuentoRequest request) {
        try {
            OrdenVentaDescuentoRequest ordenConDescuento = ordenVentaServicio.aplicarDescuento(
                    request.ordenVentaId(), request.porcentajeDescuento()
            );
            return ResponseEntity.ok(ordenConDescuento);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        } catch (RecursoNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error al aplicar descuento: " + e.getMessage()));
        }
    }

    public ResponseEntity<?> removerDescuentoOrden(Long ordenVentaId) {
        try {
            OrdenVentaDTO ordenDTO = ordenVentaServicio.removerDescuento(ordenVentaId);
            return ResponseEntity.ok(ordenDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error al remover descuento: " + e.getMessage()));
        }
    }

}
