# Sistema de Gestión de Cafetería

Aplicación en Java para gestionar clientes, productos y pedidos dentro de una cafetería.  
El sistema está pensado para los *empleados* de la cafetería, que navegan por un menú para realizar todas las operaciones: cargar pedidos, registrar clientes, aplicar descuentos, calcular gastos, etc.

---

## Funcionalidades principales

### Gestión de clientes
- Alta, baja y modificación de clientes  
- Validación de DNI  
- Búsqueda por DNI  
- Cálculo de gasto total del cliente  
- Aplicación de descuentos según gasto acumulado

### Gestión de pedidos
- Carga de pedidos con distintos métodos de pago  
- Agregar productos a un pedido  
- Cálculo automático del total  
- Validación del UPC (12 dígitos)

### Gestión de productos
- Carga de nuevos productos  
- Validaciones de precio, stock y UPC  
- Búsqueda de productos por UPC

### Menú interactivo
Sistema de menú que permite al empleado navegar entre las distintas funciones del programa.

## Persistencia de datos (JSON)

El sistema guarda toda la información de la cafetería utilizando archivos *JSON*.  
Esto permite que los datos se mantengan incluso después de cerrar el programa.

La persistencia incluye:
- Clientes
- Productos
- Pedidos
- Configuraciones (como descuentos o valores mínimos)

El guardado y la carga se realizan usando lectura/escritura de archivos JSON, permitiendo:
- Exportar los datos de la cafetería
- Recuperarlos al iniciar el sistema
- Mantener un historial de operaciones

---

## Tecnologías utilizadas
- *Java 17+*
- Programación orientada a objetos
- Manejo de excepciones personalizadas
- Colecciones (HashMap, ArrayList)
- Validaciones de entrada de usuario

---
