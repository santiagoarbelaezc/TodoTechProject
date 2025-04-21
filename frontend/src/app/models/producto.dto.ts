export interface CategoriaDTO {
    id: number;
    nombre: string;
    descripcion: string;
}

export interface ProductoDTO {
    id: number;
    nombre: string;
    codigo: string;
    descripcion: string;
    categoria: CategoriaDTO;
    precio: number;
    stock: number;
}