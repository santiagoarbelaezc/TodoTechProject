import {Component, HostListener, OnInit} from '@angular/core';
import {UsuarioService} from "../../../services/usuario.service";
import {NgClass, NgFor, NgIf} from "@angular/common";
import {CarritoService} from "../../../services/carrito.service";
import {Subscription} from "rxjs";
import {DetalleOrdenDto} from "../../../models/detalle.orden.dto";

@Component({
    selector: 'carrito',
    templateUrl: './carrito.component.html',
    styleUrl: './carrito.component.css',
    standalone: true,
    imports: [NgClass, NgIf, NgFor]
})
export class CarritoComponent implements OnInit {
    mostrarCarrito = false; // Controla si el carrito aparece al bajar
    carritoVisible = false; // Controla si el carrito está desplegado
    carritoItems: DetalleOrdenDto[] = []; // Lista de productos en el carrito
    private carritoSubscription!: Subscription;


    constructor(public authService: UsuarioService, public carritoService: CarritoService) {
    }

    @HostListener('window:scroll', [])
    onScroll(): void {
        const bannerAltura = document.getElementById('banner')?.clientHeight || 0;
        this.mostrarCarrito = window.scrollY > bannerAltura;
    }

    toggleCarrito(): void {
        this.carritoVisible = !this.carritoVisible;
    }

    ngOnInit(): void {
        if (this.authService.authStatus) {
            this.authService.getUsuario();
        }
        // Sincroniza la lista local con el carrito del servicio
        this.carritoSubscription = this.carritoService.getCarrito().subscribe((items: DetalleOrdenDto[]) => {
            this.carritoItems = items;
        }, (error: any) => {
            console.error('Error al obtener el carrito:', error);
        });
    }


}
