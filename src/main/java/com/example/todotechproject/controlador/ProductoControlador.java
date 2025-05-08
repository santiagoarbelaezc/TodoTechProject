package com.example.todotechproject.controlador;

import com.example.todotechproject.dto.ProductoDTO;
import com.example.todotechproject.servicios.ProductoServicios.ProductoServicio;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.example.todotechproject.servicios.VendedorServicios.VendedorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductoControlador {
    @Autowired
    private ProductoServicio productoServicio;
    @Autowired
    private VendedorServicio vendedorServicio;

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
        try {
            int stock = vendedorServicio.consultarDisponibilidad(codigo);

            Map<String, Object> response = new HashMap<>();
            response.put("codigo", codigo);
            response.put("stockDisponible", stock);
            response.put("disponible", stock > 0);

            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error al consultar disponibilidad: " + e.getMessage()));
        }
    }


    @GetMapping("/disponible/{codigo}")
    public ResponseEntity<?> isProductoDisponible(@PathVariable String codigo) {
        try {
            int stock = vendedorServicio.consultarDisponibilidad(codigo);

            Map<String, Object> response = new HashMap<>();
            response.put("codigo", codigo);
            response.put("disponible", stock > 0);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error al verificar disponibilidad: " + e.getMessage()));
        }
    }


    @GetMapping("/categoria/{categoria}")
    public List<ProductoDTO> buscarPorCategoria(@PathVariable String categoria) {
        return productoServicio.buscarPorCategoria(categoria);
    }


    @PostMapping("/crear")
    public ResponseEntity<?> crearProducto(@RequestBody ProductoDTO productoDTO) {
        try {
            productoServicio.crearProducto(productoDTO);
            // Solo guarda, no devuelve nada
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(Map.of("mensaje", "Producto creado con éxito"));

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error al crear el producto: " + e.getMessage()));
        }
    }


}
