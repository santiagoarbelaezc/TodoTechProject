package com.example.todotechproject.servicios.ProductoServicios;

import com.example.todotechproject.dto.ProductoDTO;
import com.example.todotechproject.dto.ProductoReporteRequest;
import com.example.todotechproject.modelo.entidades.Categoria;
import com.example.todotechproject.modelo.entidades.DetalleOrden;
import com.example.todotechproject.modelo.entidades.Producto;
import com.example.todotechproject.repositorios.CategoriaRepo;
import com.example.todotechproject.repositorios.DetalleOrdenRepo;
import com.example.todotechproject.repositorios.ProductoRepo;
import com.example.todotechproject.utils.Mappers.ProductoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductoServicioImp implements ProductoServicio {

    @Autowired
    private ProductoRepo productoRepo;

    @Autowired
    private CategoriaRepo categoriaRepo;

    @Autowired
    private DetalleOrdenRepo detalleOrdenRepo;

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

    @Override
    public void eliminarProducto(Long id) {
        if (!productoRepo.existsById(id)) {
            throw new IllegalArgumentException("Producto no encontrado con ID: " + id);
        }
        productoRepo.deleteById(id);
    }

    @Override
    public ProductoDTO crearProducto(ProductoDTO productoDTO) {
        Producto producto = new Producto();
        producto.setNombre(productoDTO.getNombre());
        producto.setCodigo(productoDTO.getCodigo());
        producto.setDescripcion(productoDTO.getDescripcion());
        producto.setPrecio(productoDTO.getPrecio());
        producto.setStock(productoDTO.getStock());
        producto.setImagen(productoDTO.getImagen());

        // Suponiendo que el campo `categoria` del DTO es el nombre de la categoría
        Categoria categoria = categoriaRepo.findCategoriaByNombre(productoDTO.getCategoria());
        if (categoria == null) {
            throw new IllegalArgumentException("Categoría no encontrada: " + productoDTO.getCategoria());
        }
        producto.setCategoria(categoria);

        producto = productoRepo.save(producto);
        return ProductoMapper.toDTO(producto);
    }

    @Override
    public List<ProductoReporteRequest> obtenerReporteVentasPorProducto() {
        List<DetalleOrden> detalles = detalleOrdenRepo.findAll();
        Map<Long, ProductoReporteRequest> reporteMap = new HashMap<>();

        for (DetalleOrden detalle : detalles) {
            Producto producto = detalle.getProducto();
            Long productoId = producto.getId();
            int cantidad = detalle.getCantidad();
            double total = cantidad * producto.getPrecio();

            ProductoReporteRequest actual = reporteMap.get(productoId);
            if (actual == null) {
                ProductoDTO productoDTO = ProductoMapper.toDTO(producto);
                reporteMap.put(productoId, new ProductoReporteRequest(productoDTO, cantidad, total));
            } else {
                int nuevaCantidad = actual.cantidadVentas() + cantidad;
                double nuevoValor = actual.valorVentas() + total;
                reporteMap.put(productoId, new ProductoReporteRequest(actual.producto(), nuevaCantidad, nuevoValor));
            }
        }

        return new ArrayList<>(reporteMap.values());
    }

    @Override
    public ProductoDTO actualizarProducto(Long id, ProductoDTO productoDTO) {
        Producto productoExistente = productoRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado con ID: " + id));

        // Actualizar campos
        productoExistente.setNombre(productoDTO.getNombre());
        productoExistente.setCodigo(productoDTO.getCodigo());
        productoExistente.setDescripcion(productoDTO.getDescripcion());
        productoExistente.setPrecio(productoDTO.getPrecio());
        productoExistente.setStock(productoDTO.getStock());
        productoExistente.setImagen(productoDTO.getImagen());

        // Actualizar categoría si es necesario
        if (!productoExistente.getCategoria().getNombre().equals(productoDTO.getCategoria())) {
            Categoria nuevaCategoria = categoriaRepo.findCategoriaByNombre(productoDTO.getCategoria());
            if (nuevaCategoria == null) {
                throw new IllegalArgumentException("Categoría no encontrada: " + productoDTO.getCategoria());
            }
            productoExistente.setCategoria(nuevaCategoria);
        }

        productoExistente = productoRepo.save(productoExistente);
        return ProductoMapper.toDTO(productoExistente);
    }


}
