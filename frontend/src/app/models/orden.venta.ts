import {ClienteDTO} from "./cliente.dto";
import {DetalleOrdenDto} from "./detalle.orden.dto";
import {EstadoOrden} from "./enum";
import {VendedorDTO} from "./vendedor.dto";


export interface OrdenVentaDto {
  id?: number;
  fecha?: Date;
  cliente: ClienteDTO;
  vendedor: VendedorDTO;
  productos: DetalleOrdenDto[];
  estado: EstadoOrden;
  total: number;
}