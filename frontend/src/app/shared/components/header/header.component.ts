import {Component, OnInit} from '@angular/core';
import {RouterLink} from "@angular/router";
import {Subscription} from "rxjs";
import {UsuarioService} from "../../../services/usuario.service";
import { NgIf} from "@angular/common";

@Component({
    selector: 'app-header',
    templateUrl: './header.component.html',
    styleUrl: './header.component.css',
    standalone: true,
    imports: [NgIf, RouterLink]
})
export class HeaderComponent implements OnInit{

    isLoggedIn = false;

    private authSubscription!: Subscription;

    constructor(public authService: UsuarioService) {
    }

    ngOnInit() {
        this.authSubscription = this.authService.getAuthStatus().subscribe(status => {
            console.log('Auth status:', status);
            this.isLoggedIn = status;
        });
    }

}
