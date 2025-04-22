package com.example.todotechproject.servicios.ProductoServicios;

import com.example.todotechproject.dto.ProductoDTO;
import com.example.todotechproject.modelo.entidades.Producto;
import com.example.todotechproject.repositorios.ProductoRepo;
import com.example.todotechproject.utils.Mappers.ProductoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoServicioImp implements ProductoServicio {

    @Autowired
    private ProductoRepo productoRepo;

    @Override
    public List<ProductoDTO> obtenerTodos() {
        List<Producto> productos = productoRepo.findAll();

        return productos.stream().map(prod -> {
            ProductoDTO dto = new ProductoDTO();
            dto.setId(prod.getId());
            dto.setNombre(prod.getNombre());
            dto.setCodigo(prod.getCodigo());
            dto.setDescripcion(prod.getDescripcion());
            dto.setCategoria(prod.getCategoria().getNombre());
            dto.setPrecio(prod.getPrecio());
            dto.setStock(prod.getStock());
            dto.setImagen(prod.getImagen());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public ProductoDTO buscarPorId(Long id) {
        return productoRepo.findById(id)
                .map(ProductoMapper::toDTO)
                .orElse(null);
    }

    @Override
    public List<ProductoDTO> buscarPorNombre(String nombre) {
        return productoRepo.findByNombreContainingIgnoreCase(nombre).stream()
                .map(ProductoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductoDTO> buscarPorCategoria(String categoria) {
        return productoRepo.findByCategoriaNombre(categoria).stream()
                .map(ProductoMapper::toDTO)
                .collect(Collectors.toList());
    }

    //OBTENER PRODUCTOS POR NOMBRE

    @Override
    public List<ProductoDTO> obtenerProductosAsus() {
        return buscarPorNombre("asus");
    }

    @Override
    public List<ProductoDTO> obtenerProductosIphone() {
        return buscarPorNombre("iphone");
    }

    @Override
    public List<ProductoDTO> obtenerProductosHp() {
        return buscarPorNombre("hp");
    }

    @Override
    public List<ProductoDTO> obtenerProductosSamsung() {
        return buscarPorNombre("samsung");
    }


}
