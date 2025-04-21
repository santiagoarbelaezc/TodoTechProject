import {AfterViewInit, Component, HostListener, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {Subscription} from "rxjs";
import {UsuarioService} from "../../../services/usuario.service";
import {NgClass, NgIf} from "@angular/common";

@Component({
    selector: 'carrito',
    templateUrl: './carrito.component.html',
    styleUrl: './carrito.component.css',
    standalone: true,
    imports: [NgClass, NgIf]
})
export class CarritoComponent {
    mostrarCarrito = false; // Controla si el carrito aparece al bajar
    carritoVisible = false; // Controla si el carrito está desplegado

    @HostListener('window:scroll', [])
    onScroll(): void {
        const bannerAltura = document.getElementById('banner')?.clientHeight || 0;
        this.mostrarCarrito = window.scrollY > bannerAltura;
    }

    toggleCarrito(): void {
        this.carritoVisible = !this.carritoVisible;
    }



}
