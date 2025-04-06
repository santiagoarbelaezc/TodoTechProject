package com.example.todotechproject.controlador;

import com.example.todotechproject.dto.ProductoDTO;
import com.example.todotechproject.servicios.ProductoServicios.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductoControlador {

    @Autowired
    private ProductoServicio productoServicio;

    @GetMapping
    public List<ProductoDTO> obtenerProductos() {
        return productoServicio.obtenerTodos();
    }
}
