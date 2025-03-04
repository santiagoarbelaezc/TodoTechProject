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

## 🗂️ Integraciones
- **Inventario**: Control en tiempo real de stock de productos.
- **Pago con tarjeta**: Validación con **Transbank**.
- **Pago con cheque**: Validación con **Orsan**.
- **Finanzas**: Registro de ventas diarias en un proceso batch.

## 🛠️ Tecnologías Utilizadas
- **Backend**: Spring Boot
- **Base de Datos**: Oracle
- **Frontend**: Angular  