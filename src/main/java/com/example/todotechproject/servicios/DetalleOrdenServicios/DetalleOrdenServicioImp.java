package com.example.todotechproject.servicios.DetalleOrdenServicios;

import com.example.todotechproject.dto.DetalleOrden.*;
import com.example.todotechproject.dto.OrdenVenta.OrdenVentaDTO;
import com.example.todotechproject.dto.OrdenVenta.OrdenVentaDescuentoRequest;
import com.example.todotechproject.dto.ProductoDTO;
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
        // Recuperamos el producto desde la base de datos
        Producto producto = productoRepo.findById(productoDTO.getId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        // Recuperamos la orden de venta
        OrdenVenta orden = ordenVentaRepo.findById(ordenVentaId)
                .orElseThrow(() -> new RuntimeException("Orden de venta no encontrada"));

        // Verificamos si ya existe un detalle con ese producto y orden
        DetalleOrden detalleExistente = detalleOrdenRepo
                .findByProductoIdAndOrdenVentaId(producto.getId(), orden.getId())
                .orElse(null);

        if (detalleExistente != null) {
            // Si existe, incrementamos cantidad y subtotal
            int nuevaCantidad = detalleExistente.getCantidad() + 1;
            detalleExistente.setCantidad(nuevaCantidad);
            detalleExistente.setSubtotal(producto.getPrecio() * nuevaCantidad);
            DetalleOrden actualizado = detalleOrdenRepo.save(detalleExistente);

            // Actualizamos el total de la orden
            actualizarTotalOrden(orden);

            // Restamos el stock del producto
            producto.setStock(producto.getStock() - 1);
            productoRepo.save(producto); // Guardamos los cambios en el stock

            return DetalleOrdenMapper.toDTO(actualizado);
        }

        // Si no existe, creamos un detalle nuevo
        DetalleOrden nuevoDetalle = new DetalleOrden();
        nuevoDetalle.setProducto(producto);
        nuevoDetalle.setCantidad(1);
        nuevoDetalle.setSubtotal(producto.getPrecio());
        nuevoDetalle.setOrdenVenta(orden);

        DetalleOrden guardado = detalleOrdenRepo.save(nuevoDetalle);

        // Actualizamos el total de la orden
        actualizarTotalOrden(orden);

        // Restamos el stock del producto
        producto.setStock(producto.getStock() - 1);
        productoRepo.save(producto); // Guardamos los cambios en el stock

        return DetalleOrdenMapper.toDTO(guardado);
    }


    private void actualizarTotalOrden(OrdenVenta orden) {
        // Calculamos el nuevo total sumando los subtotales de todos los detalles de la orden
        Double nuevoTotal = orden.getProductos().stream()
                .mapToDouble(DetalleOrden::getSubtotal)
                .sum();

        // Actualizamos el total de la orden
        orden.setTotal(nuevoTotal);

        // Guardamos la orden con el nuevo total
        ordenVentaRepo.save(orden);
    }



    public ResponseEntity<DetalleOrdenDTO> crear(CrearDetalleRequest request) {
        try {
            DetalleOrdenDTO dto = crearDetalle(request.getProducto(), request.getOrdenVentaId());
            return ResponseEntity.status(HttpStatus.CREATED).body(dto);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Override
    public boolean eliminarPorProductoYOrden(Long productoId, Long ordenVentaId) {
        DetalleOrden detalle = detalleOrdenRepo.findByProductoIdAndOrdenVentaId(productoId, ordenVentaId)
                .orElseThrow(() -> new RuntimeException("Detalle de orden no encontrado"));

        // Restaurar el stock del producto
        Producto producto = detalle.getProducto();
        int cantidadADevolver = detalle.getCantidad();
        producto.setStock(producto.getStock() + cantidadADevolver);
        productoRepo.save(producto); // Guardamos los cambios en el stock

        // Eliminar el detalle
        detalleOrdenRepo.delete(detalle);

        // Actualizar el total de la orden
        OrdenVenta orden = detalle.getOrdenVenta();
        actualizarTotalOrden(orden);

        return true;
    }

    //OBTENER PRODUCTOS ASOCIADOS A LA ORDEN ID

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

            // Crear una respuesta JSON como String
            String responseMessage;
            if (eliminado) {
                responseMessage = "{\"message\": \"Detalle eliminado correctamente\"}";
            } else {
                responseMessage = "{\"message\": \"No se encontró el detalle\"}";
            }

            // Devolver la respuesta como String con código de estado adecuado
            return ResponseEntity.status(eliminado ? HttpStatus.OK : HttpStatus.NOT_FOUND)
                    .body(responseMessage);

        } catch (RuntimeException e) {
            // En caso de error, devolver un mensaje JSON de error
            String errorMessage = "{\"error\": \"" + e.getMessage() + "\"}";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }



    @Override
    public DetalleOrdenDTO aumentarCantidad(Long productoId, Long ordenVentaId) {
        DetalleOrden detalle = detalleOrdenRepo.findByProductoIdAndOrdenVentaId(productoId, ordenVentaId)
                .orElseThrow(() -> new RuntimeException("Detalle de orden no encontrado"));

        Producto producto = detalle.getProducto();
        if (producto.getStock() <= 0) {
            throw new RuntimeException("No hay stock disponible");
        }

        int nuevaCantidad = detalle.getCantidad() + 1;
        detalle.setCantidad(nuevaCantidad);
        detalle.setSubtotal(producto.getPrecio() * nuevaCantidad);

        producto.setStock(producto.getStock() - 1);
        productoRepo.save(producto);

        detalleOrdenRepo.save(detalle);

        OrdenVenta ordenVenta = ordenVentaRepo.findById(ordenVentaId)
                .orElseThrow(() -> new RuntimeException("Orden de venta no encontrada"));
        actualizarTotalOrden(ordenVenta);

        return DetalleOrdenMapper.toDTO(detalle);
    }


    @Override
    public DetalleOrdenDTO disminuirCantidad(Long productoId, Long ordenVentaId) {
        DetalleOrden detalle = detalleOrdenRepo.findByProductoIdAndOrdenVentaId(productoId, ordenVentaId)
                .orElseThrow(() -> new RuntimeException("Detalle de orden no encontrado"));

        if (detalle.getCantidad() <= 1) {
            throw new RuntimeException("No se puede disminuir más la cantidad");
        }

        int nuevaCantidad = detalle.getCantidad() - 1;
        detalle.setCantidad(nuevaCantidad);
        detalle.setSubtotal(detalle.getProducto().getPrecio() * nuevaCantidad);

        Producto producto = detalle.getProducto();
        producto.setStock(producto.getStock() + 1);
        productoRepo.save(producto);

        detalleOrdenRepo.save(detalle);

        OrdenVenta ordenVenta = ordenVentaRepo.findById(ordenVentaId)
                .orElseThrow(() -> new RuntimeException("Orden de venta no encontrada"));
        actualizarTotalOrden(ordenVenta);

        return DetalleOrdenMapper.toDTO(detalle);
    }



    public ResponseEntity<List<DetalleOrdenDTO>> obtenerDetallesPorOrden(Long ordenId) {
        try {
            List<DetalleOrdenDTO> detalles = obtenerPorOrdenVenta(ordenId);
            return ResponseEntity.ok(detalles);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
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
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
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
