import {UsuarioDTO} from "./usuario.dto";

export interface VendedorDTO {
    id?: number;
  nombre?: string;
  correo?: string;
  telefono?: string;
  usuario: UsuarioDTO;
}