package com.example.todotechproject.servicios.CajeroServicios;

import com.example.todotechproject.dto.CajeroDTO;
import com.example.todotechproject.modelo.entidades.Cajero;
import com.example.todotechproject.modelo.entidades.OrdenVenta;
import com.example.todotechproject.modelo.enums.EstadoOrden;
import com.example.todotechproject.modelo.enums.MedioPago;
import com.example.todotechproject.repositorios.CajeroRepo;
import com.example.todotechproject.repositorios.OrdenVentaRepo;

import com.example.todotechproject.utils.Mappers.CajeroMapper;
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
    private CajeroRepo cajeroRepo;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Autowired
    private CajeroMapper cajeroMapper;

    // ... Métodos anteriores de pagos ...

    @Override
    public void guardarCajero(CajeroDTO cajero) {
        cajeroRepo.save(cajeroMapper.cajeroDTOToCajero(cajero));
    }

    @Override
    public void actualizarCajero(Cajero cajero) {
        if (!cajeroRepo.existsById(cajero.getId())) {
            throw new RuntimeException("Cajero no encontrado");
        }
        cajeroRepo.save(cajero);
    }

    @Override
    public void eliminarCajero(Long id) {
        if (!cajeroRepo.existsById(id)) {
            throw new RuntimeException("Cajero no encontrado");
        }
        cajeroRepo.deleteById(id);
    }

    @Override
    public List<CajeroDTO> listarCajeros() {
        return cajeroRepo.findAll().stream()
                .map(cajero -> new CajeroDTO(cajero.getId(), cajero.getNombre(), cajero.getCorreo(),
                        cajero.getTelefono(), usuarioMapper.toDTO(cajero.getUsuario())))
                .collect(Collectors.toList());
    }

    @Override
    public OrdenVenta buscarOrdenVenta(Long ordenId) throws Exception {
        return ordenVentaRepo.findById(ordenId)
                .orElseThrow(() -> new Exception("Orden no encontrada con ID: " + ordenId));
    }

    @Override
    public void procesarPago(Long ordenId, MedioPago metodoPago, double monto, String referenciaPago) throws Exception {

    }

    @Override
    public boolean validarPagoTarjeta(String numeroTarjeta, String cvv, double monto) {
        // Simular lógica de validación con Transbank
        return numeroTarjeta.length() == 16 && cvv.length() == 3 && monto > 0;
    }

    @Override
    public boolean validarPagoCheque(String numeroCheque) {
        // Simular validación con Orsan
        return numeroCheque != null && !numeroCheque.isEmpty();
    }

    @Override
    public void emitirComprobante(Long ordenId) throws Exception {
        OrdenVenta orden = buscarOrdenVenta(ordenId);
        if (!orden.getEstado().equals("PAGADO")) {
            throw new Exception("No se puede emitir comprobante para orden no pagada");
        }
        // Aquí generarías un PDF o documento con JasperReports
        System.out.println("Comprobante generado para la orden: " + ordenId);
    }

    @Override
    public void actualizarEstadoOrden(Long ordenId, String nuevoEstado) throws Exception {
        OrdenVenta orden = buscarOrdenVenta(ordenId);
        orden.setEstado(EstadoOrden.PAGADA);
        ordenVentaRepo.save(orden);
    }

    @Override
    public List<OrdenVenta> listarOrdenesPendientes() {
        return ordenVentaRepo.findByEstado(EstadoOrden.valueOf("PENDIENTE"));
    }

    @Override
    public void generarFactura(Long ordenId) throws Exception {
        OrdenVenta orden = buscarOrdenVenta(ordenId);
        if (!orden.getEstado().equals("PAGADO")) {
            throw new Exception("Solo se puede generar factura para ordenes pagadas");
        }
        System.out.println("Factura generada para orden: " + ordenId);
    }

    @Override
    public List<OrdenVenta> buscarPagosPorCliente(String clienteId) throws Exception {
        return ordenVentaRepo.findByCliente_Id(Long.valueOf(clienteId));
    }
}
