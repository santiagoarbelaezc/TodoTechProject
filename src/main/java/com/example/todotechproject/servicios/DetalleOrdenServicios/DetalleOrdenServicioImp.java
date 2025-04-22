package com.example.todotechproject.servicios.DetalleOrdenServicios;

import com.example.todotechproject.dto.DetalleOrden.CrearDetalleRequest;
import com.example.todotechproject.dto.DetalleOrden.DetalleOrdenDTO;
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

        DetalleOrden detalle = new DetalleOrden();
        detalle.setProducto(producto);
        detalle.setCantidad(1); // Puedes parametrizarlo si deseas
        detalle.setSubtotal(producto.getPrecio());
        detalle.setOrdenVenta(orden);

        DetalleOrden guardado = detalleOrdenRepo.save(detalle);
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

    public ResponseEntity<String> eliminar(CrearDetalleRequest request) {
        try {
            boolean eliminado = eliminarPorProductoYOrden(
                    request.getProducto().getId(),
                    request.getOrdenVentaId()
            );
            return eliminado ?
                    ResponseEntity.ok("Detalle eliminado correctamente") :
                    ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el detalle");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
