import {Component} from '@angular/core';
import {Router} from '@angular/router';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {UsuarioService} from '../../services/usuario.service';

@Component({
    selector: 'app-login',
    standalone: true,
    imports: [CommonModule, FormsModule],
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css']
})
export class LoginComponent {

    username = '';
    password = '';

    constructor(private router: Router,
                private usuarioService: UsuarioService) {
    }

    onLogin() {
        if (!this.username || !this.password) {
            alert('Por favor ingresa usuario y contraseña');
            return;
        }
        this.usuarioService.login(this.username, this.password).subscribe({
            next: () => {
                this.router.navigate(['/ordenVenta']);
            },
            error: () => {
                this.usuarioService.limpiarUsuario();
                alert('Error al obtener los datos del usuario');
            }
        });
    }
}
