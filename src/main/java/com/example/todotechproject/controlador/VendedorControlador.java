package com.example.todotechproject.controlador;

import com.example.todotechproject.dto.Reporte.ReporteRendimientoDTO;
import com.example.todotechproject.dto.VendedorDTO;
import com.example.todotechproject.modelo.entidades.Vendedor;

import com.example.todotechproject.servicios.AdminServicios.AdminServicio;
import com.example.todotechproject.servicios.VendedorServicios.VendedorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendedores")
public class VendedorControlador {

    @Autowired
    private VendedorServicio vendedorServicio;

    @GetMapping("/reporteRendimiento")
    public List<ReporteRendimientoDTO> obtenerReporteRendimiento() {
        return vendedorServicio.obtenerReporteRendimiento();
    }
}

