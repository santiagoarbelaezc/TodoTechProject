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
    public ResponseEntity<DetalleOrdenDTO> aumentarCantidad(Long productoId, Long ordenVentaId) {
        // Obtener el detalle de la orden
        DetalleOrden detalle = detalleOrdenRepo.findByProductoIdAndOrdenVentaId(productoId, ordenVentaId)
                .orElseThrow(() -> new RuntimeException("Detalle de orden no encontrado"));

        // Incrementamos la cantidad y calculamos el nuevo subtotal
        int nuevaCantidad = detalle.getCantidad() + 1;
        detalle.setCantidad(nuevaCantidad);
        detalle.setSubtotal(detalle.getProducto().getPrecio() * nuevaCantidad);

        // Actualizamos el stock del producto
        Producto producto = detalle.getProducto();
        if (producto.getStock() > 0) {
            producto.setStock(producto.getStock() - 1); // Restamos 1 al stock del producto
            productoRepo.save(producto); // Guardamos los cambios en el producto
        } else {
            return ResponseEntity.badRequest().body(null); // Si el stock es 0, no podemos aumentar la cantidad
        }

        // Guardamos el detalle actualizado
        DetalleOrden actualizado = detalleOrdenRepo.save(detalle);

        // Obtenemos la ordenVenta por su ID
        OrdenVenta ordenVenta = ordenVentaRepo.findById(ordenVentaId)
                .orElseThrow(() -> new RuntimeException("Orden de venta no encontrada"));

        // Actualizamos el total de la orden
        actualizarTotalOrden(ordenVenta);

        // Retornamos el DTO del detalle actualizado
        return ResponseEntity.ok(DetalleOrdenMapper.toDTO(actualizado));
    }

    @Override
    public ResponseEntity<DetalleOrdenDTO> disminuirCantidad(Long productoId, Long ordenVentaId) {
        // Obtener el detalle de la orden
        DetalleOrden detalle = detalleOrdenRepo.findByProductoIdAndOrdenVentaId(productoId, ordenVentaId)
                .orElseThrow(() -> new RuntimeException("Detalle de orden no encontrado"));

        // Validamos que no sea menor a 1
        if (detalle.getCantidad() <= 1) {
            return ResponseEntity.badRequest().body(null); // Podrías eliminar el detalle si la cantidad es 1
        }

        // Decrementamos la cantidad y calculamos el nuevo subtotal
        int nuevaCantidad = detalle.getCantidad() - 1;
        detalle.setCantidad(nuevaCantidad);
        detalle.setSubtotal(detalle.getProducto().getPrecio() * nuevaCantidad);

        // Actualizamos el stock del producto
        Producto producto = detalle.getProducto();
        producto.setStock(producto.getStock() + 1); // Aumentamos 1 al stock del producto
        productoRepo.save(producto); // Guardamos los cambios en el producto

        // Guardamos el detalle actualizado
        DetalleOrden actualizado = detalleOrdenRepo.save(detalle);

        // Obtenemos la ordenVenta por su ID
        OrdenVenta ordenVenta = ordenVentaRepo.findById(ordenVentaId)
                .orElseThrow(() -> new RuntimeException("Orden de venta no encontrada"));

        // Actualizamos el total de la orden
        actualizarTotalOrden(ordenVenta);

        // Retornamos el DTO del detalle actualizado
        return ResponseEntity.ok(DetalleOrdenMapper.toDTO(actualizado));
    }




}
