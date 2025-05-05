import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {OrdenVentaDto} from "../models/orden.venta";

@Injectable({
    providedIn: 'root'
})
export class OrdenVentaService {

    private apiUrl = 'http://localhost:8080/api/orden-venta'; // Base URL for the order API

    constructor(private http: HttpClient) { }

    /**
     * Creates a new sales order by sending it to the backend.
     * @param ordenVenta The order data transfer object.
     * @returns An Observable containing the created order DTO from the backend.
     */
    crearOrdenVenta(ordenVenta: OrdenVentaDto): Observable<OrdenVentaDto> {
        return this.http.post<OrdenVentaDto>(this.apiUrl, ordenVenta);
    }

    // You can add other methods here later to call other endpoints
    // like update, list, delete, etc. based on OrdenVentaControlador

    // Example for listing orders (implement based on your needs)
    // listarOrdenes(): Observable<OrdenVentaDTO[]> {
    //   return this.http.get<OrdenVentaDTO[]>(this.apiUrl);
    // }

    // Example for getting orders by vendor and status
    // listarOrdenesPorVendedorYEstado(vendedorId: number, estado: string): Observable<OrdenVentaDTO[]> {
    //   return this.http.get<OrdenVentaDTO[]>(`${this.apiUrl}/vendedor/${vendedorId}/estado/${estado}`);
    // }
}