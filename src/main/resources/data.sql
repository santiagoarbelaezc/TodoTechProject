-- USUARIOS
INSERT INTO usuario (id, usuario, password, tipo_usuario)
VALUES (1, 'vendedor1', '1234', 'VENDEDOR');

INSERT INTO usuario (id, usuario, password, tipo_usuario)
VALUES (2, 'despachador1', '1234', 'DESPACHADOR');

INSERT INTO usuario (id, usuario, password, tipo_usuario)
VALUES (3, 'cajero1', '1234', 'CAJERO');

-- VENDEDORES
INSERT INTO vendedor (id, nombre, correo, telefono, id_usuario)
VALUES (1, 'Juan Pérez', 'juan@tienda.com', '3001112233', 1);

-- DESPACHADORES
INSERT INTO despachador (id, nombre, correo, telefono, id_usuario)
VALUES (1, 'Laura Torres', 'laura@tienda.com', '3002223344', 2);

-- CAJEROS
INSERT INTO cajero (id, nombre, correo, telefono, id_usuario)
VALUES (1, 'Carlos Rivas', 'carlos@tienda.com', '3003334455', 3);

-- CLIENTES
INSERT INTO cliente (id, nombre, correo, telefono, clave)
VALUES (1, 'Ana Gómez', 'ana@cliente.com', '3011112233', 'clave1');

INSERT INTO cliente (id, nombre, correo, telefono, clave)
VALUES (2, 'Luis Martínez', 'luis@cliente.com', '3012223344', 'clave2');

-- CATEGORÍAS
INSERT INTO categoria (id, nombre, descripcion)
VALUES (1, 'Consolas', 'Consolas de videojuegos');

INSERT INTO categoria (id, nombre, descripcion)
VALUES (2, 'Accesorios', 'Accesorios para consolas');

-- PRODUCTOS
INSERT INTO producto (id, nombre, codigo, descripcion, categoria_id, precio, stock)
VALUES (1, 'PlayStation 5', 'PS5-001', 'Consola PS5 con control', 1, 500.00, 10);

INSERT INTO producto (id, nombre, codigo, descripcion, categoria_id, precio, stock)
VALUES (2, 'Control DualSense', 'CTRL-PS5-002', 'Control inalámbrico para PS5', 2, 100.00, 25);

INSERT INTO producto (id, nombre, codigo, descripcion, categoria_id, precio, stock)
VALUES (3, 'Nintendo Switch', 'NSW-003', 'Consola híbrida de Nintendo', 1, 400.00, 15);

-- ORDEN DE VENTA
INSERT INTO orden_venta (id, fecha, cliente_id, vendedor_id, estado, total)
VALUES (1, TO_DATE('2024-04-05','YYYY-MM-DD'), 1, 1, 'PENDIENTE', 700.00);

-- DETALLES DE ORDEN
INSERT INTO detalle_orden (id, producto_id, cantidad, subtotal, orden_venta_id)
VALUES (1, 1, 1, 500.00, 1);

INSERT INTO detalle_orden (id, producto_id, cantidad, subtotal, orden_venta_id)
VALUES (2, 2, 2, 200.00, 1);

-- PAGO
INSERT INTO pago (id, orden_id, monto, metodo_pago)
VALUES (1, 1, 700.00, 'EFECTIVO');
