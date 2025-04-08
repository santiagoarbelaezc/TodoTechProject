# TodoTech Shop

## Descripción
TodoTech Shop es una tienda de productos de tecnología, incluyendo:
- Computadores
- Componentes para computadores
- Dispositivos externos
- Complementos e insumos

Actualmente, las ventas se realizan a través de un catálogo en papel, lo que genera errores y ralentiza el proceso. Se necesita un sistema que agilice las ventas y elimine estos inconvenientes.

## Proceso de Compra Ideal
1. **Inicio de Orden:**
    - El vendedor inicia una orden en el sistema.
    - Agrega la información del cliente y una clave secreta ingresada por el cliente.
    - Selecciona los productos desde un catálogo en línea, asegurando disponibilidad en bodega.

2. **Confirmación de Orden:**
    - El sistema genera un número de orden.
    - El cliente es llamado en la caja para realizar el pago.

3. **Pago:**
    - Métodos de pago: Efectivo, tarjeta bancaria, Redcompra o cheque.
    - Validación:
        - Tarjetas bancarias y Redcompra se validan con Transbank.
        - Cheques se validan con Orsan.

4. **Despacho:**
    - El despachador valida el número de orden.
    - Prepara los productos según su ubicación en bodega.
    - El cliente confirma la recepción con su clave secreta.
    - Se cierra la orden de venta.

5. **Gestión de Inventario y Finanzas:**
    - El sistema actualiza el stock en tiempo real.
    - Las órdenes cerradas se almacenan en el sistema financiero en un proceso batch tras el cierre de la tienda.

## Beneficios del Sistema
✔️ Reducción de errores en precios y stock.
✔️ Agilización del proceso de venta y despacho.
✔️ Integración con sistemas financieros y de inventario.
✔️ Mejora en la experiencia del cliente y la eficiencia operativa.



# 📌 TodoTech Shop

## 🎯 Objetivo
Desarrollar un sistema que permita gestionar las ventas de manera eficiente, minimizando errores y mejorando la experiencia del cliente.

## 🛒 Flujo del Proceso de Venta
1. **El vendedor** inicia una orden, registra los datos del cliente y su clave secreta.
2. **El vendedor** selecciona los productos desde un catálogo en línea con el inventario.
3. **El sistema** genera un número de orden para el pago.
4. **El cajero** cobra el monto correspondiente y valida el pago.
5. **El cliente** recibe un comprobante y se dirige al área de despacho.
6. **El despachador** valida la orden, ubica los productos en bodega y entrega el pedido.
7. **El cliente** ingresa su clave para confirmar la recepción.
8. **El sistema** actualiza el stock y almacena la orden para la contabilidad.

## 👥 Roles y Funciones

| **Rol**         | **Funciones principales** |
|----------------|-------------------------|
| **Cliente**     | Selecciona productos, paga y retira el pedido. |
| **Vendedor**    | Registra la compra y verifica el stock. |
| **Cajero**      | Gestiona y valida el pago del cliente. |
| **Despachador** | Entrega los productos y actualiza el stock. |
| **Administrador** | Supervisa el sistema, productos y ventas. |


# Sprint 2 - Backend: Gestión de Permisos, Comprobantes, Descuentos, Usuarios y Entregas

## 🧠 Descripción General
Este sprint se enfoca en implementar funcionalidades críticas del sistema **TodoTech Shop**, específicamente en el **backend**, asegurando que el sistema pueda manejar correctamente permisos de usuario, envío de comprobantes por correo electrónico, aplicación de descuentos, registro de entregas y gestión de usuarios con distintos roles.

## 📍 Alcance
- Nivel técnico: **Backend**
- Enfoque: Funcionalidades de control de acceso, notificaciones por correo, lógica de negocio de descuentos, y gestión de usuarios y entregas.
- Framework: **Spring Boot**
- Base de datos: **Oracle**

## ✅ Historias de Usuario Incluidas en este Sprint

### HU09 - Aplicar Descuentos
- **Rol:** Vendedor  
- **Descripción:** Como vendedor, quiero poder aplicar descuentos a productos específicos o a toda la orden para ofrecer promociones a los clientes.  
- **Objetivo:** Ofrecer promociones limitadas o por temporada a los clientes, controlando el descuento total.  
- **Criterios de Aceptación:**
  - El sistema permite aplicar descuentos válidos.
  - Se rechazan descuentos mayores al permitido y se notifica al usuario.  
- **Alcance Técnico:** Backend

---

### HU12 - Enviar Comprobante
- **Rol:** Cliente  
- **Descripción:** Como cliente, quiero recibir un comprobante de mi compra por correo electrónico para tener un registro de mi transacción.  
- **Objetivo:** Garantizar que los clientes reciban confirmación de su compra vía correo.  
- **Criterios de Aceptación:**
  - El comprobante se envía automáticamente después del pago.
  - El sistema permite reenviar comprobantes anteriores.  
- **Alcance Técnico:** Backend

---

### HU19 - Registrar Entrega
- **Rol:** Despachador  
- **Descripción:** Como despachador, quiero poder registrar la entrega de una orden para llevar un control de los productos entregados.  
- **Objetivo:** Mantener un historial preciso de entregas completadas para evitar inconsistencias.  
- **Criterios de Aceptación:**
  - La entrega se registra correctamente con confirmación.
  - No se permite registrar entregas sin validación del cliente.  
- **Alcance Técnico:** Backend

---

### HU26 - Crear y Gestionar Usuarios
- **Rol:** Administrador  
- **Descripción:** Como administrador, quiero poder crear y gestionar usuarios con diferentes roles (administrador, vendedor, despachador) para controlar el acceso al sistema.  
- **Objetivo:** Asignar permisos específicos y mantener la seguridad del sistema.  
- **Criterios de Aceptación:**
  - Se permite crear usuarios con roles válidos.
  - El sistema evita roles inválidos o duplicaciones.
  - Se pueden editar y eliminar usuarios correctamente.  
- **Alcance Técnico:** Backend

---

### HUXX - Asignar Permisos Específicos a Cada Rol (extensión de HU26)
- **Rol:** Administrador  
- **Descripción:** Como administrador, quiero poder asignar permisos específicos a cada rol del sistema para garantizar que los usuarios accedan solo a las funciones que les corresponden.  
- **Objetivo:** Mantener la seguridad del sistema y prevenir accesos no autorizados.  
- **Criterios de Aceptación:**
  - Cada rol accede únicamente a sus funciones designadas.
  - Se impide el acceso a funciones no autorizadas.  
- **Alcance Técnico:** Backend

## 🗂️ Integraciones
- **Inventario**: Control en tiempo real de stock de productos.
- **Pago con tarjeta**: Validación con **Transbank**.
- **Pago con cheque**: Validación con **Orsan**.
- **Finanzas**: Registro de ventas diarias en un proceso batch.

## 🛠️ Tecnologías Utilizadas
- **Backend**: Spring Boot
- **Base de Datos**: Oracle
- **Frontend**: Angular  
