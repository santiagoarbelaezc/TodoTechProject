import {Component} from '@angular/core';
import {RouterModule} from '@angular/router';
import {HeaderComponent} from "./shared/components/header/header.component";
import {FooterComponent} from "./shared/components/footer/footer.component";
import {CarritoComponent} from "./shared/components/carrito/carrito.component";

@Component({
    selector: 'app-root',
    imports: [RouterModule, HeaderComponent, FooterComponent, CarritoComponent],
    templateUrl: './app.component.html',
    standalone: true,
    styleUrls: ['./app.component.css']
})
export class AppComponent {
}
