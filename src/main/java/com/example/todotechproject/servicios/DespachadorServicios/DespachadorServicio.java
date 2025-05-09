package com.example.todotechproject.servicios.DespachadorServicios;

import com.example.todotechproject.dto.DespachadorDTO;
import com.example.todotechproject.modelo.entidades.Despachador;

import java.util.List;

public interface DespachadorServicio {

    void guardarDespachador(DespachadorDTO despachador);

    void actualizarDespachador(Despachador despachador);
    void eliminarDespachador(Long id);
    List<DespachadorDTO> listarDespachadores();
}
