import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {UsuarioService} from '../../services/usuario.service';
import {ClienteDTO} from '../../models/cliente.dto';
import {UsuarioDTO} from '../../models/usuario.dto';

import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import {CommonModule} from '@angular/common';
import {CarritoService} from "../../services/carrito.service";
import {OrdenVentaService} from "../../services/orden-venta.service";
import {DetalleOrdenDto} from "../../models/detalle.orden.dto";
import {Subscription} from "rxjs";
import {MatTableModule} from '@angular/material/table';
import {OrdenVentaDto} from "../../models/orden.venta";
import {EstadoOrden} from "../../models/enum";
import {VendedorDTO} from "../../models/vendedor.dto";

@Component({
    selector: 'app-orden-venta',
    templateUrl: './orden-venta.component.html',
    standalone: true,
    imports: [CommonModule, FormsModule, MatTableModule, ReactiveFormsModule],
    styleUrls: ['./orden-venta.component.css']
})
export class OrdenVentaComponent implements OnInit {
    carritoItems: DetalleOrdenDto[] = []; // Lista de productos en el carrito
    columnsList = ['image', 'producto', 'cantidad', 'subtotal'];
    reportForm: FormGroup;
    private carritoSubscription!: Subscription;

    constructor(
        private fb: FormBuilder,
        private http: HttpClient,
        private usuarioService: UsuarioService,
        public carritoService: CarritoService,
        private ordenVentaService: OrdenVentaService) {
        this.reportForm = this.fb.group({
            nombre: ['', [Validators.required, Validators.minLength(5), Validators.maxLength(100)]],
            correo: ['', [Validators.required, Validators.email]],
            telefono: ['', [Validators.required]],
            claveSecreta: ['', Validators.required],

        });
    }

    ngOnInit(): void {

        // Sincroniza la lista local con el carrito del servicio
        this.carritoSubscription = this.carritoService.getCarrito().subscribe((items: DetalleOrdenDto[]) => {
            this.carritoItems = items;
        }, (error: any) => {
            console.error('Error al obtener el carrito:', error);
        });
    }

    onSubmit(): void {
        const usuarioDto: UsuarioDTO | null = this.usuarioService.getUsuario();

        if (!usuarioDto || !usuarioDto.usuario) {
            alert('Error: no se encontró el usuario autenticado.');
            return;
        }
        const  vendedor: VendedorDTO = {
            usuario:usuarioDto
        }
        if (!this.carritoItems || this.carritoItems.length === 0) {
            alert('Error: no hay productos en el carrito.');
            return;
        }
        if (!this.reportForm.valid) {
            alert('Error: el formulario no es válido.');
            return;
        }
        const cliente: ClienteDTO = {
            nombre: this.reportForm.get('nombre')?.value,
            correo: this.reportForm.get('correo')?.value,
            telefono: this.reportForm.get('telefono')?.value,
            claveSecreta: this.reportForm.get('claveSecreta')?.value
        };


        const ordenVenta: OrdenVentaDto = {
            cliente: cliente,
            vendedor: vendedor,
            productos: this.carritoItems,
            total: this.carritoService.getTotal(),
            estado: EstadoOrden.PENDIENTE
        }
        this.ordenVentaService.crearOrdenVenta(ordenVenta).subscribe(
            (response) => {
                console.log('Orden de venta creada:', response);
                alert('Orden de venta creada con éxito');
                this.carritoService.clearCarrito();
            },
            (error) => {
                console.error('Error al crear la orden de venta:', error);
                alert('Error al crear la orden de venta');
            }
        );
    }
}
