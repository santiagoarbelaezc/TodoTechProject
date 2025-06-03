package com.example.todotechproject.servicios.CajeroServicios;

import com.example.todotechproject.dto.TrabajadorDTO;
import com.example.todotechproject.dto.UsuarioDTO.UsuarioDTO;
import com.example.todotechproject.excepciones.CajeroExcepciones.*;
import com.example.todotechproject.excepciones.VendedorExcepciones.TrabajadorNoEncontradoException;
import com.example.todotechproject.modelo.entidades.Trabajador;
import com.example.todotechproject.modelo.entidades.OrdenVenta;
import com.example.todotechproject.modelo.enums.EstadoOrden;
import com.example.todotechproject.modelo.enums.MedioPago;
import com.example.todotechproject.repositorios.TrabajadorRepo;
import com.example.todotechproject.repositorios.OrdenVentaRepo;
import com.example.todotechproject.utils.Mappers.TrabajadorMapper;
import com.example.todotechproject.utils.Mappers.Usuarios.UsuarioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CajeroServicioImp implements CajeroServicio {

    @Autowired
    private OrdenVentaRepo ordenVentaRepo;

    @Autowired
    private TrabajadorRepo trabajadorRepo;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Autowired
    private TrabajadorMapper trabajadorMapper;

    @Override
    public void guardarCajero(TrabajadorDTO trabajadorDTO) {
        Trabajador trabajador = trabajadorMapper.toTrabajador(trabajadorDTO);
        trabajadorRepo.save(trabajador);
    }

    @Override
    public void actualizarCajero(Trabajador trabajador) {
        if (!trabajadorRepo.existsById(trabajador.getId())) {
            throw new TrabajadorNoEncontradoException("Trabajador no encontrado con ID: " + trabajador.getId());
        }
        trabajadorRepo.save(trabajador);
    }

    @Override
    public void eliminarCajero(Long id) {
        if (!trabajadorRepo.existsById(id)) {
            throw new TrabajadorNoEncontradoException("Trabajador no encontrado con ID: " + id);
        }
        trabajadorRepo.deleteById(id);
    }

    @Override
    public List<TrabajadorDTO> listarCajeros() {
        return trabajadorRepo.findAll().stream()
                .map(trabajador -> new TrabajadorDTO(
                        trabajador.getId(),
                        trabajador.getNombre(),
                        trabajador.getCorreo(),
                        trabajador.getTelefono(),
                        usuarioMapper.toDTO(trabajador.getUsuario())
                ))
                .collect(Collectors.toList());
    }

    @Override
    public Trabajador buscarCajeroPorId(Long id) {
        return trabajadorRepo.findById(id)
                .orElseThrow(() -> new TrabajadorNoEncontradoException("Cajero no encontrado con ID: " + id));
    }

    @Override
    public TrabajadorDTO obtenerCajeroDTOPorId(Long id) {
        return trabajadorRepo.findById(id).map(cajero -> {
            UsuarioDTO usuarioDTO = usuarioMapper.toDTO(cajero.getUsuario());
            return new TrabajadorDTO(
                    cajero.getId(),
                    cajero.getNombre(),
                    cajero.getCorreo(),
                    cajero.getTelefono(),
                    usuarioDTO
            );
        }).orElseThrow(() -> new TrabajadorNoEncontradoException("Cajero no encontrado con ID: " + id));
    }

    @Override
    public OrdenVenta buscarOrdenVenta(Long ordenId) {
        return ordenVentaRepo.findById(ordenId)
                .orElseThrow(() -> new OrdenNoEncontradaException("Orden no encontrada con ID: " + ordenId));
    }

    @Override
    public void procesarPago(Long ordenId, MedioPago metodoPago, double monto, String referenciaPago) {
        OrdenVenta orden = buscarOrdenVenta(ordenId);

        if (orden.getEstado() == EstadoOrden.PAGADA) {
            throw new EstadoOrdenInvalidoException("La orden ya ha sido pagada");
        }

        switch (metodoPago) {
            case TARJETA_BANCARIA:
                // Lógica tarjeta
                break;
            case CHEQUE:
                // Lógica cheque
                break;
            case EFECTIVO:
                // Lógica efectivo
                break;
            default:
                throw new MetodoPagoInvalidoException("Método de pago no soportado");
        }

        orden.setEstado(EstadoOrden.PAGADA);
        ordenVentaRepo.save(orden);
        // Registrar referenciaPago, monto si es necesario
    }

    @Override
    public boolean validarPagoTarjeta(String numeroTarjeta, String cvv, double monto) {
        return numeroTarjeta != null && numeroTarjeta.length() == 16 &&
                cvv != null && cvv.length() == 3 && monto > 0;
    }

    @Override
    public boolean validarPagoCheque(String numeroCheque) {
        return numeroCheque != null && !numeroCheque.trim().isEmpty();
    }

    @Override
    public void emitirComprobante(Long ordenId) {
        OrdenVenta orden = buscarOrdenVenta(ordenId);
        if (!EstadoOrden.PAGADA.equals(orden.getEstado())) {
            throw new EstadoOrdenInvalidoException("No se puede emitir comprobante para orden no pagada");
        }
        System.out.println("Comprobante generado para la orden: " + ordenId);
    }

    @Override
    public void actualizarEstadoOrden(Long ordenId, String nuevoEstado) {
        OrdenVenta orden = buscarOrdenVenta(ordenId);
        EstadoOrden estado;
        try {
            estado = EstadoOrden.valueOf(nuevoEstado.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new EstadoOrdenInvalidoException("Estado inválido: " + nuevoEstado);
        }
        orden.setEstado(estado);
        ordenVentaRepo.save(orden);
    }

    @Override
    public List<OrdenVenta> listarOrdenesPendientes() {
        return ordenVentaRepo.findByEstado(EstadoOrden.PENDIENTE);
    }

    @Override
    public void generarFactura(Long ordenId) {
        OrdenVenta orden = buscarOrdenVenta(ordenId);
        if (!EstadoOrden.PAGADA.equals(orden.getEstado())) {
            throw new EstadoOrdenInvalidoException("Solo se puede generar factura para órdenes pagadas");
        }
        System.out.println("Factura generada para orden: " + ordenId);
    }

    @Override
    public List<OrdenVenta> buscarPagosPorCliente(String clienteId) {
        try {
            Long idCliente = Long.valueOf(clienteId);
            return ordenVentaRepo.findByCliente_Id(idCliente);
        } catch (NumberFormatException e) {
            throw new MetodoPagoInvalidoException("ID de cliente inválido: " + clienteId);
        }
    }
}
