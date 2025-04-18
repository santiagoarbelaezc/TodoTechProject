import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { UsuarioService } from '../services/usuario.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  constructor(private authService: UsuarioService, private router: Router) {}

  canActivate(): boolean {
    if (this.authService.getUsuario()) {
      return true; //  Usuario autenticado, permite acceso.
    } else {
      this.router.navigate(['/login']); //  Usuario no autenticado, redirigir a login.
      return false;
    }
  }
}
