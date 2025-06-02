package com.example.todotechproject.servicios.CajeroServicios;

import com.example.todotechproject.dto.TrabajadorDTO;
import com.example.todotechproject.dto.UsuarioDTO.UsuarioDTO;
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
            throw new RuntimeException("Trabajador no encontrado con ID: " + trabajador.getId());
        }
        trabajadorRepo.save(trabajador);
    }

    @Override
    public void eliminarCajero(Long id) {
        if (!trabajadorRepo.existsById(id)) {
            throw new RuntimeException("Trabajador no encontrado con ID: " + id);
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
        return trabajadorRepo.findById(id).orElse(null);
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
        }).orElse(null);
    }

    @Override
    public OrdenVenta buscarOrdenVenta(Long ordenId) throws Exception {
        return ordenVentaRepo.findById(ordenId)
                .orElseThrow(() -> new Exception("Orden no encontrada con ID: " + ordenId));
    }

    @Override
    public void procesarPago(Long ordenId, MedioPago metodoPago, double monto, String referenciaPago) throws Exception {
        // Aquí implementa la lógica para procesar el pago según el método de pago
        // Ejemplo:
        OrdenVenta orden = buscarOrdenVenta(ordenId);

        if (orden.getEstado() == EstadoOrden.PAGADA) {
            throw new Exception("La orden ya ha sido pagada");
        }

        // Simulación de proceso de pago
        switch (metodoPago) {
            case TARJETA_BANCARIA:
                // Lógica de pago con tarjeta
                break;
            case CHEQUE:
                // Lógica de pago con cheque
                break;
            case EFECTIVO:
                // Lógica de pago en efectivo
                break;
            default:
                throw new Exception("Método de pago no soportado");
        }

        // Actualizar estado a PAGADA
        orden.setEstado(EstadoOrden.PAGADA);
        ordenVentaRepo.save(orden);

        // Opcional: registrar referenciaPago, monto, etc.
    }

    @Override
    public boolean validarPagoTarjeta(String numeroTarjeta, String cvv, double monto) {
        // Simular validación con Transbank
        return numeroTarjeta != null && numeroTarjeta.length() == 16 &&
                cvv != null && cvv.length() == 3 && monto > 0;
    }

    @Override
    public boolean validarPagoCheque(String numeroCheque) {
        // Simular validación con Orsan
        return numeroCheque != null && !numeroCheque.trim().isEmpty();
    }

    @Override
    public void emitirComprobante(Long ordenId) throws Exception {
        OrdenVenta orden = buscarOrdenVenta(ordenId);
        if (!EstadoOrden.PAGADA.equals(orden.getEstado())) {
            throw new Exception("No se puede emitir comprobante para orden no pagada");
        }
        // Aquí se debería generar un PDF o documento con JasperReports o similar
        System.out.println("Comprobante generado para la orden: " + ordenId);
    }

    @Override
    public void actualizarEstadoOrden(Long ordenId, String nuevoEstado) throws Exception {
        OrdenVenta orden = buscarOrdenVenta(ordenId);
        EstadoOrden estado;
        try {
            estado = EstadoOrden.valueOf(nuevoEstado.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new Exception("Estado inválido: " + nuevoEstado);
        }
        orden.setEstado(estado);
        ordenVentaRepo.save(orden);
    }

    @Override
    public List<OrdenVenta> listarOrdenesPendientes() {
        return ordenVentaRepo.findByEstado(EstadoOrden.PENDIENTE);
    }

    @Override
    public void generarFactura(Long ordenId) throws Exception {
        OrdenVenta orden = buscarOrdenVenta(ordenId);
        if (!EstadoOrden.PAGADA.equals(orden.getEstado())) {
            throw new Exception("Solo se puede generar factura para órdenes pagadas");
        }
        // Aquí podrías integrar JasperReports para generar la factura PDF
        System.out.println("Factura generada para orden: " + ordenId);
    }

    @Override
    public List<OrdenVenta> buscarPagosPorCliente(String clienteId) throws Exception {
        try {
            Long idCliente = Long.valueOf(clienteId);
            return ordenVentaRepo.findByCliente_Id(idCliente);
        } catch (NumberFormatException e) {
            throw new Exception("ID de cliente inválido: " + clienteId);
        }
    }
}
