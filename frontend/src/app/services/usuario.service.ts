import {Injectable} from '@angular/core';
import {UsuarioDTO} from '../models/usuario.dto';
import {BehaviorSubject, Observable, tap} from "rxjs";
import {HttpClient} from "@angular/common/http";

@Injectable({
    providedIn: 'root'
})
export class UsuarioService {
    private apiUrl = 'http://localhost:8080/api/usuarios/login';

    private usuarioActual: UsuarioDTO | null = null;
    public authStatus = new BehaviorSubject<boolean>(this.isAuthenticated());

    constructor(private http: HttpClient) {
    }

    isAuthenticated(): boolean {
        return typeof localStorage !== 'undefined' && !!localStorage.getItem('usuario'); //  Verifica si hay un token almacenado
    }

    setUsuario(usuario: UsuarioDTO): void {
        this.usuarioActual = usuario;
    }

    getUsuario(): UsuarioDTO | null {
        return this.usuarioActual;
    }

    getAuthStatus() {
        return this.authStatus.asObservable();
    }

    limpiarUsuario(): void {
        localStorage.removeItem('usuario');
        this.usuarioActual = null;
        this.authStatus.next(false);
    }

    login(usuario: string, password: string): Observable<UsuarioDTO> {
        return this.http.get<UsuarioDTO>(`${this.apiUrl}/${usuario}/${password}`).pipe(
            tap((usuario: UsuarioDTO) => {
                console.log('paso por service status:', true);
                this.setUsuario(usuario);
                localStorage.setItem('usuario', JSON.stringify(usuario));
                this.authStatus.next(true);// opcional
            })
        );

    }
}
