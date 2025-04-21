import {AfterViewInit, Component, HostListener, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {Subscription} from "rxjs";
import {UsuarioService} from "../../../services/usuario.service";
import {NgClass, NgIf} from "@angular/common";

@Component({
    selector: 'app-header',
    templateUrl: './header.component.html',
    styleUrl: './header.component.css',
    standalone: true,
    imports: [NgClass, NgIf]
})
export class HeaderComponent implements OnInit{
    mostrarCarrito = false; // Controla si el carrito aparece al bajar
    carritoVisible = false; // Controla si el carrito está desplegado
    isLoggedIn = false;

    private authSubscription!: Subscription;

    constructor(private authService: UsuarioService, private router: Router) {
    }

    @HostListener('window:scroll', [])
    onScroll(): void {
        const bannerAltura = document.getElementById('banner')?.clientHeight || 0;
        this.mostrarCarrito = window.scrollY > bannerAltura;
    }

    toggleCarrito(): void {
        this.carritoVisible = !this.carritoVisible;
    }

    ngOnInit() {
        this.authSubscription = this.authService.getAuthStatus().subscribe(status => {
            this.isLoggedIn = status;
        });
    }

}
