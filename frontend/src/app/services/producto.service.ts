import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable, tap} from 'rxjs';
import {ProductoDTO} from "../models/producto.dto";

@Injectable({
    providedIn: 'root'
})
export class ProductoService {

    private apiUrl = 'http://localhost:8080/api/producto';

    private productos: ProductoDTO[] = [];

    constructor(private http: HttpClient) {
    }
    getProductos(): Observable<ProductoDTO[]> {
        return this.http.get<ProductoDTO[]>(this.apiUrl).pipe(
            tap(productosDTO => {
                this.productos = productosDTO;
            })
        );
    }
}
