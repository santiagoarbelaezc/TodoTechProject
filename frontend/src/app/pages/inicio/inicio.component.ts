import {AfterViewInit, Component, HostListener, OnInit} from '@angular/core';
import {CommonModule} from '@angular/common';
import {Router} from '@angular/router'; // Importar Router
import {UsuarioDTO} from '../../models/usuario.dto';
import {HttpClient} from '@angular/common/http';
import {ProductoDTO} from "../../models/producto.dto";
import {ProductoService} from "../../services/producto.service";
import {CarritoService} from "../../services/carrito.service";

@Component({
    selector: 'app-inicio',
    standalone: true,
    imports: [CommonModule],
    templateUrl: './inicio.component.html',
    styleUrl: './inicio.component.css'
})
export class InicioComponent implements OnInit {
    productos: ProductoDTO[] = [];

    constructor(private productoService: ProductoService, private carrito: CarritoService) {

    }

    addCarrito(producto: ProductoDTO): void {
        this.carrito.addProducto(producto);
        console.log('Producto agregado al carrito');
        console.log(this.carrito.getCarrito());
    }

    removeCarrito(producto: ProductoDTO): void {
        this.carrito.removeProducto(producto);
        console.log('Producto elimnado del carrito');
        console.log(this.carrito.getCarrito());
    }


    ngOnInit(): void {
            this.productoService.getProductos().subscribe({
                next: (data) => this.productos = data,
                error: (err) => console.error('Error al cargar productos:', err)
            });
    }

}
