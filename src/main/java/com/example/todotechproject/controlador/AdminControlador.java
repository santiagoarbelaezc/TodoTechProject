package com.example.todotechproject.controlador;

import com.example.todotechproject.dto.VendedorDTO;
import com.example.todotechproject.modelo.entidades.Vendedor;
import com.example.todotechproject.servicios.AdminServicios.AdminServicio;
import com.example.todotechproject.servicios.VendedorServicios.VendedorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admins")
public class AdminControlador {

    @Autowired
    private AdminServicio adminServicio;
    
}
