package com.example.todotechproject.servicios.DespachadorServicios;

import com.example.todotechproject.dto.TrabajadorDTO;

import com.example.todotechproject.modelo.entidades.Trabajador;

import java.util.List;

public interface DespachadorServicio {

    void guardarDespachador(TrabajadorDTO despachador);

    void actualizarDespachador(Trabajador despachador);

    void eliminarDespachador(Long id);

    List<TrabajadorDTO> listarDespachadores();

    Trabajador buscarDespachadorPorId(Long id);

}
