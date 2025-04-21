import {ProductoDTO} from "./producto.dto";

export interface DetalleOrdenDto {
    id: number;
    producto: ProductoDTO;
    cantidad: number;
    subtotal: number;
    idOrdenVenta: number;
}