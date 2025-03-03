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