import {Injectable} from '@angular/core';
import {ProductoDTO} from "../models/producto.dto";
import {DetalleOrdenDto} from "../models/detalle.orden.dto";
import {BehaviorSubject, Observable} from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class CarritoService {
    private carrito: BehaviorSubject<DetalleOrdenDto[]> = new BehaviorSubject<DetalleOrdenDto[]>([]);
    public total: number = 0;
    public totalItems: number = 0;
    private bloqueado = false;

    getCarrito(): Observable<DetalleOrdenDto[]> {
        return this.carrito.asObservable().pipe( carrito =>{
            let carritoList = this.carrito.getValue();
            this.total = carritoList.reduce((total, item) => total + item.subtotal, 0);
            this.totalItems = carritoList.reduce((total, item) => total + item.cantidad, 0);
            return carrito;
        });
    }

    updateCarrito() {
        this.carrito.next(this.carrito.getValue());
    }

    public addProducto(producto: ProductoDTO): void {
        if (this.bloqueado) return;
        if (producto.stock <= 0) {
            alert('No hay stock disponible');
            return;
        }

        this.bloqueado = true;
        producto.stock = producto.stock - 1;
        const currentCarrito: DetalleOrdenDto[] = this.getDetalleDto();

        const index = currentCarrito.findIndex(p => p.producto.id === producto.id);
        if (index > -1) {
            currentCarrito[index].cantidad++;
            currentCarrito[index].subtotal = currentCarrito[index].cantidad * producto.precio;
        } else {
            const detalle: DetalleOrdenDto = {
                id: producto.id,
                producto: producto,
                cantidad: 1,
                subtotal: producto.precio,
                idOrdenVenta: 0
            };
            currentCarrito.push(detalle);
        }
        this.updateCarrito();

        setTimeout(() => {
            this.bloqueado = false;
        }, 50); // evita clicks múltiples rápidos
    }

    public removeProducto(producto: ProductoDTO): void {
        if (this.bloqueado) return;

        this.bloqueado = true;
        const currentCarrito = this.getDetalleDto();

        const index = currentCarrito.findIndex(p => p.producto.id === producto.id);
        if (index > -1) {
            currentCarrito[index].cantidad--;
            producto.stock++;
            if (currentCarrito[index].cantidad == 0) {
                currentCarrito.splice(index, 1);

            } else {
                currentCarrito[index].subtotal = currentCarrito[index].cantidad * producto.precio;
            }
        }
        this.updateCarrito();
        setTimeout(() => {
            this.bloqueado = false;
        }, 50); // evita clicks múltiples rápidos
    }

    private getDetalleDto(): DetalleOrdenDto[] {
        return this.carrito.getValue();
    }
    public clearCarrito(): void {
        this.carrito.next([]);
        this.total = 0;
        this.totalItems = 0;
    }
    public getTotal(): number {
        return this.total;
    }
    public getTotalItems(): number {
        return this.totalItems;
    }
}
