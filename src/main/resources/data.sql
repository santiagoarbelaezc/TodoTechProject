-- CATEGORÍAS
INSERT INTO categoria (id, nombre, descripcion)
VALUES (1, 'Laptops', 'Computadoras portátiles para gaming y uso general');
INSERT INTO categoria (id, nombre, descripcion)
VALUES (2, 'Celulares', 'Teléfonos inteligentes de última generación');
INSERT INTO categoria (id, nombre, descripcion)
VALUES (3, 'Accesorios', 'Accesorios para dispositivos electrónicos');
INSERT INTO categoria (id, nombre, descripcion)
VALUES (4, 'PC Gaming', 'Equipos y periféricos para PC gamers');

-- PRODUCTOS
-- Laptops ASUS
INSERT INTO producto (id, nombre, codigo, descripcion, categoria_id, precio, stock, imagen)
VALUES (1, 'ASUS ROG Zephyrus G14', 'ASUS-ROG-001', 'Laptop gaming ASUS ROG Zephyrus G14 con AMD Ryzen 9 y RTX 3060', 1, 1499.99, 10, 'asus1.png');
INSERT INTO producto (id, nombre, codigo, descripcion, categoria_id, precio, stock, imagen)
VALUES (2, 'ASUS TUF Gaming A15', 'ASUS-TUF-001', 'Laptop gaming ASUS TUF Gaming A15 con AMD Ryzen 7 y RTX 3050Ti', 1, 1199.99, 15, 'asus2.png');
INSERT INTO producto (id, nombre, codigo, descripcion, categoria_id, precio, stock, imagen)
VALUES (3, 'ASUS VivoBook 15', 'ASUS-VIVO-001', 'Laptop ASUS VivoBook 15 con Intel Core i5 para uso diario', 1, 699.99, 20, 'asus3.png');
INSERT INTO producto (id, nombre, codigo, descripcion, categoria_id, precio, stock, imagen)
VALUES (4, 'ASUS ZenBook Pro Duo', 'ASUS-ZEN-001', 'Laptop ASUS ZenBook Pro Duo con pantalla táctil secundaria', 1, 1899.99, 8, 'asus4.png');
INSERT INTO producto (id, nombre, codigo, descripcion, categoria_id, precio, stock, imagen)
VALUES (5, 'ASUS Chromebook Flip', 'ASUS-CHR-001', 'Laptop ASUS Chromebook Flip convertible con Chrome OS', 1, 399.99, 12, 'asus5.png');

-- Laptops HP
INSERT INTO producto (id, nombre, codigo, descripcion, categoria_id, precio, stock, imagen)
VALUES (6, 'HP Pavilion Gaming', 'HP-PAV-001', 'Laptop gaming HP Pavilion con Intel Core i7 y NVIDIA GTX 1650', 1, 999.99, 15, 'hp1.png');
INSERT INTO producto (id, nombre, codigo, descripcion, categoria_id, precio, stock, imagen)
VALUES (7, 'HP Spectre x360', 'HP-SPEC-001', 'Laptop convertible HP Spectre x360 con pantalla táctil OLED', 1, 1299.99, 10, 'hp2.png');
INSERT INTO producto (id, nombre, codigo, descripcion, categoria_id, precio, stock, imagen)
VALUES (8, 'HP Envy 15', 'HP-ENVY-001', 'Laptop HP Envy 15 con Intel Core i5 y tarjeta gráfica dedicada', 1, 899.99, 12, 'hp3.png');
INSERT INTO producto (id, nombre, codigo, descripcion, categoria_id, precio, stock, imagen)
VALUES (9, 'HP Elite Dragonfly', 'HP-ELITE-001', 'Laptop ultraligera HP Elite Dragonfly para profesionales', 1, 1599.99, 8, 'hp4.png');
INSERT INTO producto (id, nombre, codigo, descripcion, categoria_id, precio, stock, imagen)
VALUES (10, 'HP Omen 15', 'HP-OMEN-001', 'Laptop gaming HP Omen 15 con Intel Core i7 y RTX 3070', 1, 1499.99, 10, 'hp5.png');

-- Celulares iPhone
INSERT INTO producto (id, nombre, codigo, descripcion, categoria_id, precio, stock, imagen)
VALUES (11, 'iPhone 15 Pro Max', 'IPH-15PM-001', 'iPhone 15 Pro Max con chip A17 Pro y cámara de 48MP', 2, 1099.99, 20, 'iphone1.png');
INSERT INTO producto (id, nombre, codigo, descripcion, categoria_id, precio, stock, imagen)
VALUES (12, 'iPhone 15', 'IPH-15-001', 'iPhone 15 con chip A16 Bionic y pantalla Super Retina XDR', 2, 799.99, 25, 'iphone2.png');
INSERT INTO producto (id, nombre, codigo, descripcion, categoria_id, precio, stock, imagen)
VALUES (13, 'iPhone 14', 'IPH-14-001', 'iPhone 14 con chip A15 Bionic y sistema de cámara dual', 2, 699.99, 15, 'iphone3.png');

-- Celulares Samsung
INSERT INTO producto (id, nombre, codigo, descripcion, categoria_id, precio, stock, imagen)
VALUES (14, 'Samsung Galaxy S24 Ultra', 'SAM-S24U-001', 'Samsung Galaxy S24 Ultra con Snapdragon 8 Gen 3 y cámara de 200MP', 2, 1199.99, 18, 'samsung1.png');
INSERT INTO producto (id, nombre, codigo, descripcion, categoria_id, precio, stock, imagen)
VALUES (15, 'Samsung Galaxy S24', 'SAM-S24-001', 'Samsung Galaxy S24 con procesador Exynos y pantalla Dynamic AMOLED', 2, 799.99, 22, 'samsung2.png');
INSERT INTO producto (id, nombre, codigo, descripcion, categoria_id, precio, stock, imagen)
VALUES (16, 'Samsung Galaxy Z Fold 5', 'SAM-FOLD5-001', 'Samsung Galaxy Z Fold 5 con pantalla plegable y S Pen compatible', 2, 1799.99, 10, 'samsung3.png');
INSERT INTO producto (id, nombre, codigo, descripcion, categoria_id, precio, stock, imagen)
VALUES (17, 'Samsung Galaxy Z Flip 5', 'SAM-FLIP5-001', 'Samsung Galaxy Z Flip 5 con diseño plegable compacto', 2, 999.99, 12, 'samsung4.png');
INSERT INTO producto (id, nombre, codigo, descripcion, categoria_id, precio, stock, imagen)
VALUES (18, 'Samsung Galaxy A54', 'SAM-A54-001', 'Samsung Galaxy A54 con gran batería y pantalla Super AMOLED', 2, 399.99, 30, 'samsung5.png');

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