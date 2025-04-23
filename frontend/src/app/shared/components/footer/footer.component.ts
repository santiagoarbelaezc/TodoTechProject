import {Component} from '@angular/core';
import {UsuarioService} from "../../../services/usuario.service";
import {NgIf} from "@angular/common";


@Component({
    selector: 'app-footer',
    templateUrl: './footer.component.html',
    styleUrl: './footer.component.css',
    standalone: true,
    imports: [NgIf]
})
export class FooterComponent {
    constructor(public authService: UsuarioService) {
    }

}
