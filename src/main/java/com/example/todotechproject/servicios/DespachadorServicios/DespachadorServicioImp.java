package com.example.todotechproject.servicios.DespachadorServicios;

import com.example.todotechproject.dto.TrabajadorDTO;
import com.example.todotechproject.modelo.entidades.Trabajador;
import com.example.todotechproject.repositorios.TrabajadorRepo;
import com.example.todotechproject.utils.Mappers.TrabajadorMapper;
import com.example.todotechproject.utils.Mappers.Usuarios.UsuarioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DespachadorServicioImp implements DespachadorServicio {

    @Autowired
    private TrabajadorRepo trabajadorRepo;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Autowired
    private TrabajadorMapper trabajadorMapper;

    @Override
    public void guardarDespachador(TrabajadorDTO despachador) {
        trabajadorRepo.save(trabajadorMapper.toTrabajador(despachador));
    }

    @Override
    public void actualizarDespachador(Trabajador despachador) {
        if (!trabajadorRepo.existsById(despachador.getId())) {
            throw new RuntimeException("Despachador no encontrado");
        }
        trabajadorRepo.save(despachador);
    }

    @Override
    public void eliminarDespachador(Long id) {
        if (!trabajadorRepo.existsById(id)) {
            throw new RuntimeException("Despachador no encontrado");
        }
        trabajadorRepo.deleteById(id);
    }

    @Override
    public List<TrabajadorDTO> listarDespachadores() {
        return trabajadorRepo.findAll().stream()
                .map(despachador -> new TrabajadorDTO(
                        despachador.getId(),
                        despachador.getNombre(),
                        despachador.getCorreo(),
                        despachador.getTelefono(),
                        usuarioMapper.toDTO(despachador.getUsuario())
                ))
                .collect(Collectors.toList());
    }

    @Override
    public Trabajador buscarDespachadorPorId(Long id) {
        return trabajadorRepo.findById(id).orElse(null);
    }

}
