package com.example.todotechproject.controlador;

import com.example.todotechproject.dto.ProductoDTO;
import com.example.todotechproject.dto.ProductoReporteRequest;
import com.example.todotechproject.servicios.ProductoServicios.ProductoServicio;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductoControlador {

    @Autowired
    private ProductoServicio productoServicio;

    @GetMapping()
    public List<ProductoDTO> obtenerProductos() {
        return productoServicio.obtenerTodos();
    }

    @GetMapping("/marca/{marca}")
    public List<ProductoDTO> obtenerProductosPorMarca(@PathVariable String marca) {
        return productoServicio.buscarPorNombre(marca);
    }

    @GetMapping("/id/{id}")
    public ProductoDTO obtenerProductoPorId(@PathVariable Long id) {
        return productoServicio.buscarPorId(id);
    }

    @GetMapping("/nombre/{nombre}")
    public List<ProductoDTO> buscarPorNombre(@PathVariable String nombre) {
        return productoServicio.buscarPorNombre(nombre);
    }

    @GetMapping("/disponibilidad/{codigo}")
    public ResponseEntity<?> consultarDisponibilidad(@PathVariable String codigo) {
        return productoServicio.consultarDisponibilidad(codigo);
    }

    @GetMapping("/disponible/{codigo}")
    public ResponseEntity<?> isProductoDisponible(@PathVariable String codigo) {
        return productoServicio.isProductoDisponible(codigo);
    }

    @GetMapping("/categoria/{categoria}")
    public List<ProductoDTO> buscarPorCategoria(@PathVariable String categoria) {
        return productoServicio.buscarPorCategoria(categoria);
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crearProducto(@RequestBody ProductoDTO productoDTO) {
        return productoServicio.crearProductoResponse(productoDTO);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable Long id) {
        return productoServicio.eliminarProductoResponse(id);
    }

    @GetMapping("/reporte/ventas")
    public ResponseEntity<List<ProductoReporteRequest>> obtenerReporteVentas() {
        return ResponseEntity.ok(productoServicio.obtenerReporteVentasPorProducto());
    }
}
