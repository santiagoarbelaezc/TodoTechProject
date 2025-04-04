package com.example.todotechproject.controlador;

import com.example.todotechproject.dto.VendedorDTO;
import com.example.todotechproject.modelo.entidades.Vendedor;

import com.example.todotechproject.servicios.AdminServicios.AdminServicio;
import com.example.todotechproject.servicios.VendedorServicios.VendedorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendedores")
public class VendedorControlador {
/*
    @Autowired
    private VendedorServicio vendedorService;

    @Autowired
    private AdminServicio adminServicio;

    // Crear vendedor
    @PostMapping("/crear")
    public void crearVendedor(@RequestBody VendedorDTO vendedorDTO) throws Exception {
        Vendedor vendedor = new Vendedor();
        vendedor.setNombre(vendedorDTO.nombre());
        vendedor.setCorreo(vendedorDTO.correo());
        vendedor.setTelefono(vendedorDTO.telefono());
        vendedor.setUsuario(vendedorDTO.usuario());

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
        vendedor.setUsuario(vendedorDTO.usuario());

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
        return new VendedorDTO(vendedor.getNombre(), vendedor.getCorreo(), vendedor.getTelefono(), vendedor.getUsuario());
    }

    // Listar todos los vendedores
    @GetMapping("/listar")
    public List<VendedorDTO> listarVendedores() {
        return vendedorService.listarVendedores();
    }

*/

}
