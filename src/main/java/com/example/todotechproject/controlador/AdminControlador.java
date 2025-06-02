package com.example.todotechproject.controlador;

import com.example.todotechproject.servicios.AdminServicios.AdminServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admins")
public class AdminControlador {

    @Autowired
    private AdminServicio adminServicio;
    
}
