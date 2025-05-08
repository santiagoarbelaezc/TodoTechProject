package com.example.todotechproject.servicios.DetalleOrdenServicios;

import com.example.todotechproject.dto.DetalleOrden.CrearDetalleRequest;
import com.example.todotechproject.dto.DetalleOrden.DetalleOrdenDTO;
import com.example.todotechproject.dto.DetalleOrden.EliminarDetalleRequest;
import com.example.todotechproject.dto.ProductoDTO;
import com.example.todotechproject.modelo.entidades.DetalleOrden;
import com.example.todotechproject.modelo.entidades.OrdenVenta;
import com.example.todotechproject.modelo.entidades.Producto;
import com.example.todotechproject.repositorios.DetalleOrdenRepo;
import com.example.todotechproject.repositorios.OrdenVentaRepo;
import com.example.todotechproject.repositorios.ProductoRepo;
import com.example.todotechproject.utils.Mappers.DetalleOrdenMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DetalleOrdenServicioImp implements DetalleOrdenServicio {

    @Autowired
    private DetalleOrdenRepo detalleOrdenRepo;

    @Autowired
    private ProductoRepo productoRepo;

    @Autowired
    private OrdenVentaRepo ordenVentaRepo;

    @Override
    public DetalleOrdenDTO crearDetalle(ProductoDTO productoDTO, Long ordenVentaId) {
        Producto producto = productoRepo.findById(productoDTO.getId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

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
            return DetalleOrdenMapper.toDTO(actualizado);
        }

        // Si no existe, creamos uno nuevo
        DetalleOrden nuevoDetalle = new DetalleOrden();
        nuevoDetalle.setProducto(producto);
        nuevoDetalle.setCantidad(1);
        nuevoDetalle.setSubtotal(producto.getPrecio());
        nuevoDetalle.setOrdenVenta(orden);

        DetalleOrden guardado = detalleOrdenRepo.save(nuevoDetalle);
        return DetalleOrdenMapper.toDTO(guardado);
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

        detalleOrdenRepo.delete(detalle);
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
    public ResponseEntity<DetalleOrdenDTO> aumentarCantidad(Long productoId, Long ordenVentaId) {
        DetalleOrden detalle = detalleOrdenRepo.findByProductoIdAndOrdenVentaId(productoId, ordenVentaId)
                .orElseThrow(() -> new RuntimeException("Detalle de orden no encontrado"));

        int nuevaCantidad = detalle.getCantidad() + 1;
        detalle.setCantidad(nuevaCantidad);
        detalle.setSubtotal(detalle.getProducto().getPrecio() * nuevaCantidad);

        DetalleOrden actualizado = detalleOrdenRepo.save(detalle);
        return ResponseEntity.ok(DetalleOrdenMapper.toDTO(actualizado));
    }

    @Override
    public ResponseEntity<DetalleOrdenDTO> disminuirCantidad(Long productoId, Long ordenVentaId) {
        DetalleOrden detalle = detalleOrdenRepo.findByProductoIdAndOrdenVentaId(productoId, ordenVentaId)
                .orElseThrow(() -> new RuntimeException("Detalle de orden no encontrado"));

        if (detalle.getCantidad() <= 1) {
            return ResponseEntity.badRequest().body(null); // o podrías eliminar el detalle si la cantidad es 1
        }

        int nuevaCantidad = detalle.getCantidad() - 1;
        detalle.setCantidad(nuevaCantidad);
        detalle.setSubtotal(detalle.getProducto().getPrecio() * nuevaCantidad);

        DetalleOrden actualizado = detalleOrdenRepo.save(detalle);
        return ResponseEntity.ok(DetalleOrdenMapper.toDTO(actualizado));
    }


}
