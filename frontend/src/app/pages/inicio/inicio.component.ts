import {AfterViewInit, Component, HostListener, OnInit} from '@angular/core';
import {CommonModule} from '@angular/common';
import {Router} from '@angular/router'; // Importar Router
import {UsuarioDTO} from '../../models/usuario.dto';
import {HttpClient} from '@angular/common/http';
import {ProductoDTO} from "../../models/producto.dto";
import {ProductoService} from "../../services/producto.service";

@Component({
    selector: 'app-inicio',
    standalone: true,
    imports: [CommonModule],
    templateUrl: './inicio.component.html',
    styleUrl: './inicio.component.css'
})
export class InicioComponent implements AfterViewInit, OnInit {
    productos: ProductoDTO[] = [];

    private apiUrl = 'http://localhost:8080/api/usuarios';

    mostrarCarrito = false; // Controla si el carrito aparece al bajar

    usuarios: UsuarioDTO [] = [];

    constructor(private http: HttpClient, private router: Router, private productoService: ProductoService) {


    } // Inyectar el Router

    @HostListener('window:scroll', [])
    onScroll(): void {
        const bannerAltura = document.getElementById('banner')?.clientHeight || 0;
        this.mostrarCarrito = window.scrollY > bannerAltura;
    }


    ngOnInit(): void {
        setTimeout(() => {
            this.productoService.getProductos().subscribe({
                next: (data) => this.productos = data,
                error: (err) => console.error('Error al cargar productos:', err)
            });
        }, 2);
    }

    ngAfterViewInit(): void {
        this.initCarousel('.carousel', '#prevBtn', '#nextBtn');
        this.initCarousel('.carouselCelulares', '#prevBtnCel', '#nextBtnCel');
    }

    private initCarousel(carouselSelector: string, prevBtnSelector: string, nextBtnSelector: string):
        void {
        const carousel = document.querySelector(carouselSelector) as HTMLElement;
        const prevBtn = document.querySelector(prevBtnSelector) as HTMLElement;
        const nextBtn = document.querySelector(nextBtnSelector) as HTMLElement;

        if (!carousel || !prevBtn || !nextBtn)
            return; // Evitar errores si algún elemento no se encuentra

        let index = 0;
        const items = carousel.querySelectorAll('.carousel-item');
        const totalItems = items.length;
        const visibleItems = 3; // Número de elementos visibles al mismo tiempo
        const itemWidth = items[0].clientWidth + 20; // Ancho del primer item + margen

        function updateCarousel() {
            const offset = -index * itemWidth;
            carousel.style.transform = `translateX(${offset}px)`;
        }

        nextBtn.addEventListener('click', () => {
            if (index < totalItems - visibleItems) {
                index++;
                updateCarousel();
            }
        });

        prevBtn.addEventListener('click', () => {
            if (index > 0) {
                index--;
                updateCarousel();
            }
        });

        updateCarousel();
    }
}
