-- CATEGORÍAS
INSERT INTO categoria (id, nombre, descripcion)
VALUES (1, 'Consolas', 'Consolas de videojuegos');
INSERT INTO categoria (id, nombre, descripcion)
VALUES (2, 'Accesorios', 'Accesorios para consolas y gamers');
INSERT INTO categoria (id, nombre, descripcion)
VALUES (3, 'Nintendo', 'Consolas y accesorios de Nintendo');
INSERT INTO categoria (id, nombre, descripcion)
VALUES (4, 'PC Gaming', 'Equipos y periféricos para PC gamers');

-- PRODUCTOS
INSERT INTO producto (id, nombre, codigo, descripcion, categoria_id, precio, stock, imagen)
VALUES (1, 'PlayStation 5', 'PS5-001', 'Consola Sony PS5', 1, 499.99, 10, 'ps5.jpg');
INSERT INTO producto (id, nombre, codigo, descripcion, categoria_id, precio, stock, imagen)
VALUES (2, 'Control Xbox Series X', 'XBX-CNT-001', 'Control inalámbrico Xbox', 2, 59.99, 25, 'xbox-control.jpg');
INSERT INTO producto (id, nombre, codigo, descripcion, categoria_id, precio, stock, imagen)
VALUES (3, 'Nintendo Switch OLED', 'NSW-OLED-001', 'Consola Nintendo Switch edición OLED', 3, 349.99, 15, 'switch-oled.jpg');
INSERT INTO producto (id, nombre, codigo, descripcion, categoria_id, precio, stock, imagen)
VALUES (4, 'Teclado Mecánico RGB', 'PC-KBD-001', 'Teclado mecánico retroiluminado para gamers', 4, 89.99, 30, 'teclado-rgb.jpg');
INSERT INTO producto (id, nombre, codigo, descripcion, categoria_id, precio, stock, imagen)
VALUES (5, 'Mouse Gamer Logitech G502', 'PC-MSE-001', 'Mouse ergonómico de alta precisión', 4, 59.99, 20, 'mouse-g502.jpg');

-- USUARIOS
INSERT INTO usuario (id, usuario, password, tipo_usuario)
VALUES (1, 'cajero1', '1234', 'CAJERO');
INSERT INTO usuario (id, usuario, password, tipo_usuario)
VALUES (2, 'vendedor1', '1234', 'VENDEDOR');
INSERT INTO usuario (id, usuario, password, tipo_usuario)
VALUES (3, 'despachador1', '1234', 'DESPACHADOR');
INSERT INTO usuario (id, usuario, password, tipo_usuario)
VALUES (4, 'cajero2', '1234', 'CAJERO');
INSERT INTO usuario (id, usuario, password, tipo_usuario)
VALUES (5, 'vendedor2', '1234', 'VENDEDOR');
INSERT INTO usuario (id, usuario, password, tipo_usuario)
VALUES (6, 'despachador2', '1234', 'DESPACHADOR');

-- CAJEROS
INSERT INTO cajero (id, nombre, correo, telefono, id_usuario)
VALUES (1, 'Carlos Cajero', 'carlos@todotech.com', '3001234567', 1);
INSERT INTO cajero (id, nombre, correo, telefono, id_usuario)
VALUES (2, 'Camila Cajera', 'camila@todotech.com', '3009876543', 4);

-- VENDEDORES
INSERT INTO vendedor (id, nombre, correo, telefono, id_usuario)
VALUES (1, 'Valeria Vendedora', 'valeria@todotech.com', '3107654321', 2);
INSERT INTO vendedor (id, nombre, correo, telefono, id_usuario)
VALUES (2, 'Victor Vendedor', 'victor@todotech.com', '3112223344', 5);

-- DESPACHADORES
INSERT INTO despachador (id, nombre, correo, telefono, id_usuario)
VALUES (1, 'Diego Despachador', 'diego@todotech.com', '3201112233', 3);
INSERT INTO despachador (id, nombre, correo, telefono, id_usuario)
VALUES (2, 'Daniela Despachadora', 'daniela@todotech.com', '3214445566', 6);

-- CLIENTES
INSERT INTO cliente (id, nombre, correo, telefono, clave)
VALUES (1, 'Andrea Cliente', 'andrea@correo.com', '3019990000', 'clave123');
INSERT INTO cliente (id, nombre, correo, telefono, clave)
VALUES (2, 'Brayan Cliente', 'brayan@correo.com', '3021110001', 'clave456');
INSERT INTO cliente (id, nombre, correo, telefono, clave)
VALUES (3, 'Luisa Cliente', 'luisa@correo.com', '3032220002', 'clave789');
