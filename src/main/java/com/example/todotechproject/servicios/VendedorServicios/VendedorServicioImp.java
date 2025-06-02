package com.example.todotechproject.servicios.VendedorServicios;

import com.example.todotechproject.dto.TrabajadorDTO;
import com.example.todotechproject.dto.UsuarioDTO.UsuarioDTO;
import com.example.todotechproject.modelo.entidades.*;
import com.example.todotechproject.modelo.enums.EstadoOrden;
import com.example.todotechproject.repositorios.*;
import com.example.todotechproject.utils.Mappers.TrabajadorMapper;

import com.example.todotechproject.utils.Mappers.Usuarios.UsuarioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VendedorServicioImp implements VendedorServicio {

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private TrabajadorRepo trabajadorRepo;

    @Autowired
    private OrdenVentaRepo ordenVentaRepo;

    @Autowired
    private ProductoRepo productoRepo;

    @Autowired
    private ClienteRepo clienteRepo;

    @Autowired
    private TrabajadorMapper trabajadorMapper;

    @Autowired
    private UsuarioMapper usuarioMapper;


    @Override
    public void crearVendedor(TrabajadorDTO trabajadorDTO) {
        Optional<Usuario> usuarioOptional = usuarioRepo.findByUsuario(trabajadorDTO.usuario().usuario());

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            Trabajador trabajador = trabajadorMapper.toTrabajador(trabajadorDTO);
            trabajador.setUsuario(usuario);
            trabajadorRepo.save(trabajador);
        } else {
            throw new RuntimeException("El usuario asociado no fue encontrado");
        }
    }

    @Override
    public OrdenVenta crearOrdenVenta(LocalDateTime fecha, Cliente cliente, Trabajador trabajador) throws Exception {
        Cliente clienteGuardado = clienteRepo.save(cliente);
        OrdenVenta ordenVenta = new OrdenVenta();
        ordenVenta.setFecha(java.sql.Timestamp.valueOf(fecha));
        ordenVenta.setCliente(clienteGuardado);
        ordenVenta.setTrabajador(trabajador);
        ordenVenta.setEstado(EstadoOrden.PENDIENTE);
        ordenVenta.setTotal(0.0);
        ordenVenta.setProductos(List.of());
        return ordenVentaRepo.save(ordenVenta);
    }

    @Override
    public Trabajador buscarVendedorPorUsuario(Usuario usuario) {
        return trabajadorRepo.findByUsuario(usuario)
                .orElseThrow(() -> new RuntimeException("Trabajador no encontrado para el usuario: " + usuario.getUsuario()));
    }

    @Override
    public void cancelarOrdenVenta(Long ordenId) throws Exception {
        // Método vacío por ahora
    }

    @Override
    public void agregarProducto(Producto producto) throws Exception {
        // Método vacío por ahora
    }

    @Override
    public void eliminarProducto(Producto producto) throws Exception {
        // Método vacío por ahora
    }

    @Override
    public void buscarProducto(String codigo) throws Exception {
        // Método vacío por ahora
    }

    @Override
    public List<Producto> buscarProductoNombre(String nombre) throws Exception {
        return List.of(); // Vacío de momento
    }

    @Override
    public List<Producto> buscarProductoId(String id) throws Exception {
        return List.of(); // Vacío de momento
    }

    @Override
    public List<Producto> buscarProductoCategoria(Categoria categoria) throws Exception {
        return List.of(); // Vacío de momento
    }

    @Override
    public int consultarDisponibilidad(String codigo) throws Exception {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("El código del producto no puede ser nulo o vacío");
        }

        try {
            Producto producto = productoRepo.findByCodigo(codigo)
                    .orElseThrow(() -> new Exception("El producto con código " + codigo + " no existe"));

            return producto.getStock();
        } catch (Exception e) {
            throw new Exception("Error al consultar disponibilidad del producto: " + e.getMessage(), e);
        }
    }


    @Override
    public void ingresarDescuento(String codigo, double descuento) throws Exception {
        // Método vacío por ahora
    }

    @Override
    public void modificarCantidadProducto(String codigo, int nuevaCantidad) throws Exception {
        // Método vacío por ahora
    }

    @Override
    public void actualizarVendedor(Trabajador trabajador) {
        if (trabajadorRepo.existsById(trabajador.getId())) {
            trabajadorRepo.save(trabajador);
        }
    }

    @Override
    public void eliminarVendedor(Long id) {
        trabajadorRepo.deleteById(id);
    }

    @Override
    public Trabajador buscarVendedorPorId(Long id) {
        return trabajadorRepo.findById(id).orElse(null);
    }

    @Override
    public List<TrabajadorDTO> listarVendedores() {
        return trabajadorRepo.findAll().stream()
                .map(trabajadorMapper::toTrabajadorDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, String> crearVendedorYMensaje(TrabajadorDTO trabajadorDTO) {
        crearVendedor(trabajadorDTO); // ya implementado
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "Vendedor creado correctamente");
        return response;
    }


    @Override
    public TrabajadorDTO obtenerVendedorDTOPorId(Long id) {
        Trabajador vendedor = buscarVendedorPorId(id);
        if (vendedor == null) return null;

        UsuarioDTO usuarioDTO = usuarioMapper.toDTO(vendedor.getUsuario());

        return new TrabajadorDTO(
                vendedor.getId(),
                vendedor.getNombre(),
                vendedor.getCorreo(),
                vendedor.getTelefono(),
                usuarioDTO
        );
    }


    @Override
    public void actualizarVendedorDTO(TrabajadorDTO dto) {
        Trabajador vendedor = trabajadorMapper.toTrabajador(dto);
        if (trabajadorRepo.existsById(vendedor.getId())) {
            trabajadorRepo.save(vendedor);
        } else {
            throw new RuntimeException("Vendedor no encontrado con ID: " + vendedor.getId());
        }
    }
}
