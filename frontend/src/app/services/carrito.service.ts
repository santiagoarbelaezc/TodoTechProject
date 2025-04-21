import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ProductoDTO} from "../models/producto.dto";
import {DetalleOrdenDto} from "../models/detalle.orden.dto";

@Injectable({
    providedIn: 'root'
})
export class CarritoService {
    private carrito: DetalleOrdenDto[] = [];
    private bloqueado = false;

    constructor(private http: HttpClient) {
    }

    getCarrito(): DetalleOrdenDto[] {
        return this.carrito;
    }

    addProducto(producto: ProductoDTO): void {
        if (this.bloqueado) return;

        this.bloqueado = true;
        const index = this.carrito.findIndex(p => p.producto.id === producto.id);
        if (index > -1) {
            this.carrito[index].cantidad++;
            this.carrito[index].subtotal = this.carrito[index].cantidad * producto.precio;
        } else {
            const detalle: DetalleOrdenDto = {
                id: 0,
                producto: producto,
                cantidad: 1,
                subtotal: producto.precio,
                idOrdenVenta: 0
            };
            this.carrito.push(detalle);
        }
        setTimeout(() => {
            this.bloqueado = false;
        }, 50); // evita clicks múltiples rápidos
    }

    removeProducto(producto: ProductoDTO): void {
        if (this.bloqueado) return;

        this.bloqueado = true;
        const index = this.carrito.findIndex(p => p.id === producto.id);
        if (index > -1) {
            this.carrito.splice(index, 1);
        }
        setTimeout(() => {
            this.bloqueado = false;
        }, 50); // evita clicks múltiples rápidos
    }
}
