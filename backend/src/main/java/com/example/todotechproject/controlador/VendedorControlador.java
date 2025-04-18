package com.example.todotechproject.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todotechproject.modelo.dto.VendedorDTO;
import com.example.todotechproject.modelo.entidades.Vendedor;
import com.example.todotechproject.modelo.mapper.VendedorMapper;
import com.example.todotechproject.servicios.AdminServicios.AdminServicio;
import com.example.todotechproject.servicios.VendedorServicios.VendedorServicio;

@RestController
@RequestMapping("/vendedores")
public class VendedorControlador {

  @Autowired
  private VendedorServicio vendedorService;

  @Autowired
  private AdminServicio adminServicio;

  @Autowired
  private VendedorMapper vendedorMapper;

  // Crear vendedor
  @PostMapping("/crear")
  public void crearVendedor(@RequestBody VendedorDTO vendedorDTO) throws Exception {
    Vendedor vendedor = new Vendedor();
    vendedor.setNombre(vendedorDTO.nombre());
    vendedor.setCorreo(vendedorDTO.correo());
    vendedor.setTelefono(vendedorDTO.telefono());
    //TODO IMPROVe it
    //vendedor.setUsuario(vendedorDTO.usuario());

    adminServicio.crearVendedor(vendedor);
  }

  // Actualizar vendedor
  @PutMapping("/actualizar/{id}")
  public void actualizarVendedor(@PathVariable Long id, @RequestBody VendedorDTO vendedorDTO) {
    Vendedor vendedor = new Vendedor();
    vendedor.setId(id);
    vendedor.setNombre(vendedorDTO.nombre());
    vendedor.setCorreo(vendedorDTO.correo());
    vendedor.setTelefono(vendedorDTO.telefono());
    // TODO improve it
    // vendedor.setUsuario(vendedorDTO.usuario());

    vendedorService.actualizarVendedor(vendedor);
  }

  // Eliminar vendedor
  @DeleteMapping("/eliminar/{id}")
  public void eliminarVendedor(@PathVariable Long id) {
    vendedorService.eliminarVendedor(id);
  }

  // Buscar vendedor por ID
  @GetMapping("/buscar/{id}")
  public VendedorDTO buscarVendedor(@PathVariable Long id) {
    Vendedor vendedor = vendedorService.buscarVendedorPorId(id);
    if (vendedor == null) {
      return null;
    }
    return vendedorMapper.toDto(vendedor);
  }

  // Listar todos los vendedores
  @GetMapping("/listar")
  public List<VendedorDTO> listarVendedores() {
    return vendedorService.listarVendedores();
  }
}
