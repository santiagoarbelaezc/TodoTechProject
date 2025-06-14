package com.example.todotechproject.servicios.CajeroServicios;

import com.example.todotechproject.dto.TrabajadorDTO;

import com.example.todotechproject.modelo.entidades.OrdenVenta;
import com.example.todotechproject.modelo.entidades.Trabajador;
import com.example.todotechproject.modelo.enums.MedioPago;

import java.util.List;

public interface CajeroServicio {



    void guardarCajero(TrabajadorDTO cajero);


    void actualizarCajero(Trabajador cajero);

    void eliminarCajero(Long id);

    List<TrabajadorDTO> listarCajeros();

    OrdenVenta buscarOrdenVenta(Long ordenId) throws Exception;

    void procesarPago(Long ordenId, MedioPago metodoPago, double monto, String referenciaPago) throws Exception; // Registra el pago.

    boolean validarPagoTarjeta(String numeroTarjeta, String cvv, double monto) throws Exception; // Validar con Transbank.

    boolean validarPagoCheque(String numeroCheque) throws Exception; // Validar con Orsan.

    void emitirComprobante(Long ordenId) throws Exception; // Generar recibo de pago.

    void actualizarEstadoOrden(Long ordenId, String nuevoEstado) throws Exception; //ACTUALIZAR EL ESTADO DE ORDEN MANUAL

    List<OrdenVenta> listarOrdenesPendientes() throws Exception; //LISTAR ORDENES

    void generarFactura(Long ordenId) throws Exception;

    List<OrdenVenta> buscarPagosPorCliente(String clienteId) throws Exception;


    Trabajador buscarCajeroPorId(Long id);

    TrabajadorDTO obtenerCajeroDTOPorId(Long id);
}
