package com.example.todotechproject.servicios.DespachadorServicios;

import com.example.todotechproject.dto.DespachadorDTO;
import com.example.todotechproject.modelo.entidades.Despachador;
import com.example.todotechproject.repositorios.DespachadorRepo;
import com.example.todotechproject.utils.Mappers.DespachadorMapper;
import com.example.todotechproject.utils.Mappers.Usuarios.UsuarioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DespachadorServicioImp implements DespachadorServicio {

    @Autowired
    private DespachadorRepo despachadorRepo;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Autowired
    private DespachadorMapper despachadorMapper;

    @Override
    public void guardarDespachador(DespachadorDTO despachador) {
        despachadorRepo.save(despachadorMapper.despachadorDTOToDespachador(despachador));
    }

    @Override
    public void actualizarDespachador(Despachador despachador) {
        if (!despachadorRepo.existsById(despachador.getId())) {
            throw new RuntimeException("Despachador no encontrado");
        }
        despachadorRepo.save(despachador);
    }

    @Override
    public void eliminarDespachador(Long id) {
        if (!despachadorRepo.existsById(id)) {
            throw new RuntimeException("Despachador no encontrado");
        }
        despachadorRepo.deleteById(id);
    }

    @Override
    public List<DespachadorDTO> listarDespachadores() {
        return despachadorRepo.findAll().stream()
                .map(despachador -> new DespachadorDTO(despachador.getId(), despachador.getNombre(),
                        despachador.getCorreo(), despachador.getTelefono(), usuarioMapper.toDTO(despachador.getUsuario())))
                .collect(Collectors.toList());
    }
}
