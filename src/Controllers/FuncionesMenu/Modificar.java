package Controllers.FuncionesMenu;
import Controllers.Cafeteria;
import Exceptions.ElementoNoEncontradoException;
import Exceptions.ElementoRepetidoException;
import Exceptions.ListaNoCargadaException;
import Exceptions.SalirDelIngresoDeDatosException;
import Models.Pedidos.Pedido;
import Models.Personas.Cliente;
import Models.Personas.Empleado;
import Models.Productos.Producto;
import Models.Proveedores.Proveedor;
import Enum.ETipoProducto;
import Enum.ETipoPago;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Modificar {
    static Scanner sc = new Scanner(System.in);
    // Métodos modificar listas.
    public static void modificar(Cafeteria cafe) throws ElementoNoEncontradoException, ListaNoCargadaException {
        char control = 's';
        int opcion = 0;
        while(control == 's'){
            try {
                System.out.println("-----------------------------------");
                System.out.println("- Modificar -");
                Utilidades.mostrarListasModificar();
                opcion = sc.nextInt();
                sc.nextLine();
                System.out.println("-----------------------------------");
            } catch (InputMismatchException e) {
                System.out.println("- Error: La opción debe ser numérica.");
                sc.nextLine();
            }
            switch(opcion){
                case 0:
                    control = 'n';
                    break;
                case 1:
                    if(cafe.listaEmpleados.getMap().isEmpty()) throw new ListaNoCargadaException("- No hay empleados para modificar.");
                    try {
                        modificarEmpleado(cafe);
                    } catch (SalirDelIngresoDeDatosException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    if (cafe.listaClientes.getMap().isEmpty()) throw new ListaNoCargadaException("- No hay clientes para modificar.");
                    try {
                        modificarCliente(cafe);
                        System.out.println("- Se actualizó el cliente correctamente!");
                    } catch (SalirDelIngresoDeDatosException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    if (cafe.listaProveedores.getMap().isEmpty()) throw new ListaNoCargadaException("- No hay proveedores para modificar.");
                    try {
                        modificarProveedor(cafe);
                        System.out.println("- Se actualizó el proveedor correctamente!");
                    } catch (SalirDelIngresoDeDatosException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    if (cafe.listaProductos.getMap().isEmpty()) throw new ListaNoCargadaException("- No hay productos para modificar.");
                    try {
                        modificarProducto(cafe);
                        System.out.println("- Se actualizó el producto correctamente!");
                    } catch (SalirDelIngresoDeDatosException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 5:
                    if (cafe.listaPedidos.getMap().isEmpty()) throw new ListaNoCargadaException("- No hay pedidos para modificar.");
                    try {
                        modificarPedido(cafe);
                        System.out.println("- Se actualizó el pedido correctamente!");
                    } catch (SalirDelIngresoDeDatosException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 6:
                    if (cafe.listaMarcas.getMap().isEmpty()) throw new ListaNoCargadaException("- No hay marcas para modificar.");
                    try {
                        modificarMarca(cafe);
                        System.out.println("- Se actualizó la marca correctamente!");
                    } catch (SalirDelIngresoDeDatosException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 7:
                    if (cafe.listaCategorias.getMap().isEmpty()) throw new ListaNoCargadaException("- No hay categorías para modificar.");
                    try {
                        modificarCategoria(cafe);
                        System.out.println("- Se actualizó la categoría correctamente!");
                    } catch (SalirDelIngresoDeDatosException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 8:
                    try {
                        modificarMontoMinimoPedido();
                        System.out.println("- Se actualizó el monto mínimo correctamente!");
                    } catch (SalirDelIngresoDeDatosException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 9:
                    try {
                        modificarDescuentoPedido();
                        System.out.println("- Se actualizó el descuento de los pedidos correctamente!");
                    } catch (SalirDelIngresoDeDatosException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    System.out.println("- Opción inválida.");
                    break;
            }
            try{
                if(control=='s') {
                    control = Utilidades.continuar("modificando");
                }
            }catch(IllegalArgumentException e){
                System.out.println(e.getMessage());
                sc.nextLine();
            }
        }
    }

    public static void modificarEmpleado(Cafeteria cafe) throws ElementoNoEncontradoException, SalirDelIngresoDeDatosException {
        char control = 's';
        Empleado e = Buscar.buscarEmpleado(cafe);
        System.out.println(e);
        String dni = e.getDni();
        while(control == 's') {
            System.out.println("- Seleccione el campo a modificar del empleado: ");
            System.out.println("1 - DNI. ");
            System.out.println("2 - Nombre. ");
            System.out.println("3 - Apellido. ");
            System.out.println("4 - Fecha de nacimiento. ");
            System.out.println("5 - Teléfono. ");
            System.out.println("6 - Sueldo. ");
            System.out.println("0 - Salir. ");
            int opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion) {
                case 0:
                    control = 'n';
                    break;
                case 1:
                    while (true) {
                        try {
                            System.out.println("- Ingrese el DNI nuevo del empleado (ingrese 0 para salir): ");
                            String dniNuevo = sc.next();
                            if(dniNuevo.equals("0")){
                                sc.nextLine();
                                throw new SalirDelIngresoDeDatosException();
                            }
                            Utilidades.validarCodigo(dniNuevo,8);
                            if(cafe.listaEmpleados.getMap().containsKey(dniNuevo)) throw new ElementoRepetidoException();
                            cafe.listaEmpleados.getMap().get(dni).setDni(dniNuevo);
                            System.out.println("- Se actualizó el DNI correctamente!");
                            break;
                        }catch (IllegalArgumentException | ElementoRepetidoException x) {
                            System.out.println("- Error: " + x.getMessage());
                        }
                    }
                    break;
                case 2:
                    while (true) {
                        try {
                            System.out.println("- Ingrese nuevo nombre del empleado (ingrese 0 para salir): ");
                            String nombre = sc.nextLine();
                            if(nombre.equals("0")) throw new SalirDelIngresoDeDatosException();
                            Utilidades.validarString(nombre);
                            cafe.listaEmpleados.getMap().get(dni).setNombre(nombre);
                            System.out.println("- Se actualizó el nombre correctamente!");
                            break;
                        } catch (IllegalArgumentException ex) {
                            System.out.println("- Error: " + ex.getMessage());
                        }
                    }
                    break;
                case 3:
                    while (true) {
                        try {
                            System.out.println("- Ingrese nuevo apellido del empleado (ingrese 0 para salir): ");
                            String apellido = sc.nextLine();
                            if(apellido.equals("0")) throw new SalirDelIngresoDeDatosException();
                            Utilidades.validarString(apellido);
                            cafe.listaEmpleados.getMap().get(dni).setApellido(apellido);
                            System.out.println("- Se actualizó el apellido correctamente!");
                            break;
                        } catch (IllegalArgumentException ex) {
                            System.out.println("- Error: " + ex.getMessage());
                        }
                    }
                    break;
                case 4:
                    while (true) {
                        try {
                            System.out.println("- Ingrese la nueva fecha de nacimiento del empleado (YYYY-MM-DD) en números (ingrese 0 para salir): ");
                            String fecha = sc.nextLine();
                            if(fecha.equals("0")) throw new SalirDelIngresoDeDatosException();
                            LocalDate temp = LocalDate.parse(fecha);
                            if (temp.isAfter(LocalDate.now())) throw new IllegalArgumentException("La fecha no debe ser posterior al día de hoy.");
                            cafe.listaEmpleados.getMap().get(dni).setFechaNacimiento(temp);
                            int edad = e.calcularEdad();
                            cafe.listaEmpleados.getMap().get(dni).setEdad(edad);
                            if (edad < 16) throw new IllegalArgumentException("La edad debe ser mayor o igual a 16 años.");
                            System.out.println("- Se actualizó la fecha de nacimiento correctamente!");
                            break;
                        } catch (DateTimeParseException x) {
                            System.out.println("- Error: El formato de la fecha es erroneo, debe ser YYYY-MM-DD en números.");
                        } catch (IllegalArgumentException ex) {
                            System.out.println("- Error: " + ex.getMessage());
                        }
                    }
                    break;
                case 5:
                    while (true) {
                        try {
                            System.out.println("- Ingrese el nuevo teléfono del empleado (ingrese 0 para salir): ");
                            String telefono = sc.nextLine();
                            if(telefono.equals("0")) throw new SalirDelIngresoDeDatosException();
                            cafe.listaEmpleados.getMap().get(dni).setTelefono(telefono);
                            if (!cafe.listaEmpleados.getMap().get(dni).validarTelefono()) throw new IllegalArgumentException("El teléfono ingresado no es válido.");
                            System.out.println("- Se actualizó el teléfono correctamente!");
                            break;
                        } catch (IllegalArgumentException x) {
                            System.out.println("- Error: " + x.getMessage());
                        }
                    }
                    break;
                case 6:
                    while (true) {
                        try {
                            System.out.println("- Ingrese nuevo sueldo del empleado (ingrese 0 para salir): ");
                            double sueldo = sc.nextDouble();
                            if(sueldo == 0) throw new SalirDelIngresoDeDatosException();
                            if (sueldo < 0) throw new IllegalArgumentException("El sueldo debe ser mayor que 0.");
                            cafe.listaEmpleados.getMap().get(dni).setSueldo(sueldo);
                            System.out.println("- Se actualizó el sueldo correctamente!");
                            break;
                        } catch (IllegalArgumentException x) {
                            System.out.println("- Error: " + x.getMessage());
                        } catch (InputMismatchException x) {
                            System.out.println("- Error: el sueldo debe ser numérico.");
                        }
                    }
                    break;
                default:
                    System.out.println("- Opción inválida.");
                    break;
            }
            try{
                if(control == 's') {
                    control = Utilidades.continuar("modificando datos del empleado");
                }
            }catch(IllegalArgumentException x){
                System.out.println(x.getMessage());
            }
        }
    }

    public static void modificarCliente(Cafeteria cafe) throws ElementoNoEncontradoException, SalirDelIngresoDeDatosException {
        char control = 's';
        Cliente c = Buscar.buscarCliente(cafe);
        System.out.println(c);
        String dni = c.getDni();
        while (control == 's') {
            System.out.println("- Seleccione el campo a modificar del cliente: ");
            System.out.println("1 - DNI.");
            System.out.println("2 - Nombre.");
            System.out.println("3 - Apellido.");
            System.out.println("4 - Fecha de nacimiento.");
            System.out.println("5 - Teléfono.");
            System.out.println("6 - Recalcular Gastos totales.");
            System.out.println("0 - Salir.");
            int opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion) {
                case 0:
                    control = 'n';
                    break;
                case 1:
                    while (true) {
                        try {
                            System.out.println("- Ingrese el DNI nuevo del cliente (ingrese 0 para salir): ");
                            String dniNuevo = sc.next();
                            if(dniNuevo.equals("0")){
                                sc.nextLine();
                                throw new SalirDelIngresoDeDatosException();
                            }
                            Utilidades.validarCodigo(dniNuevo,8);
                            if (cafe.listaClientes.getMap().containsKey(dniNuevo)) throw new ElementoNoEncontradoException();
                            cafe.listaClientes.getMap().get(dni).setDni(dniNuevo);
                            System.out.println("- Se actualizó el DNI correctamente!");
                            break;
                        } catch (IllegalArgumentException | ElementoNoEncontradoException x) {
                            System.out.println("- Error: " + x.getMessage());
                        }
                    }
                    break;
                case 2:
                    while (true) {
                        try {
                            System.out.println("- Ingrese nuevo nombre del cliente (ingrese 0 para salir): ");
                            String nombre = sc.nextLine();
                            if(nombre.equals("0")) throw new SalirDelIngresoDeDatosException();
                            Utilidades.validarString(nombre);
                            cafe.listaClientes.getMap().get(dni).setNombre(nombre);
                            System.out.println("- Se actualizó el nombre correctamente!");
                            break;
                        } catch (IllegalArgumentException ex) {
                            System.out.println("- Error: " + ex.getMessage());
                        }
                    }
                    break;
                case 3:
                    while (true) {
                        try {
                            System.out.println("- Ingrese nuevo apellido del cliente (ingrese 0 para salir): ");
                            String apellido = sc.nextLine();
                            if(apellido.equals("0")) throw new SalirDelIngresoDeDatosException();
                            Utilidades.validarString(apellido);
                            cafe.listaClientes.getMap().get(dni).setApellido(apellido);
                            System.out.println("- Se actualizó el apellido correctamente!");
                            break;
                        } catch (IllegalArgumentException ex) {
                            System.out.println("- Error: " + ex.getMessage());
                        }
                    }
                    break;
                case 4:
                    while (true) {
                        try {
                            System.out.println("- Ingrese la nueva fecha de nacimiento del cliente (YYYY-MM-DD) (ingrese 0 para salir): ");
                            String fecha = sc.nextLine();
                            if(fecha.equals("0")) throw new SalirDelIngresoDeDatosException();
                            LocalDate temp = LocalDate.parse(fecha);
                            if (temp.isAfter(LocalDate.now())) throw new IllegalArgumentException("La fecha no debe ser posterior al día de hoy.");
                            cafe.listaClientes.getMap().get(dni).setFechaNacimiento(temp);
                            System.out.println("- Se actualizó la fecha de nacimiento correctamente!");
                            break;
                        } catch (DateTimeParseException x) {
                            System.out.println("-Error: El formato de fecha es incorrecto. Debe ser YYYY-MM-DD.");
                        } catch (IllegalArgumentException ex) {
                            System.out.println("- Error: " + ex.getMessage());
                        }
                    }
                    break;
                case 5:
                    while (true) {
                        try {
                            System.out.println("- Ingrese nuevo teléfono del cliente (ingrese 0 para salir): ");
                            String telefono = sc.nextLine();
                            if(telefono.equals("0")) throw new SalirDelIngresoDeDatosException();
                            cafe.listaClientes.getMap().get(dni).setTelefono(telefono);
                            if (!cafe.listaClientes.getMap().get(dni).validarTelefono()) throw new IllegalArgumentException("El teléfono ingresado no es válido.");
                            System.out.println("- Se actualizó el teléfono correctamente!");
                            break;
                        } catch (IllegalArgumentException ex) {
                            System.out.println("- Error: " + ex.getMessage());
                        }
                    }
                    break;
                case 6:
                    cafe.calcularGastoTotalDeCliente(c.getDni());
                    break;

                default:
                    System.out.println("- Opción inválida.");
            }
            try {
                if (control == 's')
                    control = Utilidades.continuar("modificando datos del cliente");
            } catch (IllegalArgumentException x) {
                System.out.println(x.getMessage());
            }
        }
    }

    public static void modificarProveedor(Cafeteria cafe) throws ElementoNoEncontradoException, SalirDelIngresoDeDatosException {
        char control = 's';
        Proveedor p = Buscar.buscarProveedor(cafe);
        System.out.println(p);
        String cuil = p.getCuil();
        int opcion;
        while (control == 's') {
            while(true) {
                System.out.println("- Seleccione el campo a modificar del proveedor: ");
                System.out.println("1 - CUIL. ");
                System.out.println("2 - Nombre. ");
                System.out.println("3 - Teléfono. ");
                System.out.println("0 - Salir. ");
                try {
                    opcion = sc.nextInt();
                    sc.nextLine();
                    break;
                }catch(InputMismatchException e){
                    System.out.println("- La opción debe ser numérica. ");
                    sc.nextLine();
                }
            }
            switch(opcion){
                case 0:
                    control = 'n';
                    break;
                case 1:
                    while(true){
                        try{
                            System.out.println("- Ingrese el nuevo CUIL (solo números) (ingrese 0 para salir): ");
                            String cuilNuevo = sc.next();
                            if(cuilNuevo.equals("0")) throw new SalirDelIngresoDeDatosException();
                            Utilidades.validarCodigo(cuilNuevo,11);
                            cafe.listaProveedores.getMap().get(cuil).setCuil(cuilNuevo);
                            System.out.println("- Se actualizó el CUIL correctamente!");
                            break;
                        }catch(IllegalArgumentException e){
                            System.out.println("- Error: " + e.getMessage());
                        }catch(InputMismatchException e){
                            System.out.println("- Error: El CUIL debe ser numérico. ");
                            sc.nextLine();
                        }
                    }
                    break;
                case 2:
                    while(true){
                        try{
                            System.out.println("- Ingrese el nuevo nombre (ingrese 0 para salir): ");
                            String nombre = sc.nextLine();
                            if(nombre.equals("0")) throw new SalirDelIngresoDeDatosException();
                            Utilidades.validarString(nombre);
                            cafe.listaProveedores.getMap().get(cuil).setNombre(nombre);
                            System.out.println("- Se actualizó el nombre correctamente!");
                            break;
                        }catch(IllegalArgumentException e){
                            System.out.println("- Error: " + e.getMessage());
                        }
                    }
                    break;

                case 3:
                    while(true){
                        try {
                            System.out.println("- Ingrese el nuevo teléfono (ingrese 0 para salir): ");
                            String telefono = sc.nextLine();
                            if(telefono.equals("0")) throw new SalirDelIngresoDeDatosException();
                            cafe.listaProveedores.getMap().get(p.getCuil()).setTelefono(telefono);
                            if(!cafe.listaProveedores.getMap().get(p.getCuil()).validarTelefono()) throw new IllegalArgumentException("El teléfono ingresado no es válido.");
                            System.out.println("- Se actualizó el teléfono correctamente!");
                            break;
                        }catch(IllegalArgumentException e){
                            System.out.println("- Error: " + e.getMessage());
                        }
                    }
                    break;
                default:
                    System.out.println("- Opcion invalida.");
                    break;
            }
            try {
                if (control == 's') {
                    control = Utilidades.continuar("modificando datos del proveedor");
                }
            } catch (IllegalArgumentException x) {
                System.out.println(x.getMessage());
            }
        }
    }

    public static void modificarProducto(Cafeteria cafe) throws ElementoNoEncontradoException, SalirDelIngresoDeDatosException {
        char control = 's';
        Producto p = Buscar.buscarProducto(cafe);
        System.out.println(p);
        String upc = p.getUpc();
        while (control == 's') {
            System.out.println("- Seleccione el campo a modificar del producto: ");
            System.out.println("1 - UPC. ");
            System.out.println("2 - Nombre. ");
            System.out.println("3 - Marca. ");
            System.out.println("4 - Precio. ");
            System.out.println("5 - Proveedor. ");
            System.out.println("6 - Categoria. ");
            System.out.println("0 - Salir. ");
            int opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion) {
                case 0:
                    control = 'n';
                    break;
                case 1:
                    while (true) {
                        try {
                            System.out.println("- Ingrese el UPC nuevo del producto (ingrese 0 para salir): ");
                            String upcNuevo = sc.next();
                            if(upcNuevo.equals("0")){
                                sc.nextLine();
                                throw new SalirDelIngresoDeDatosException();
                            }
                            Utilidades.validarCodigo(upcNuevo,12);
                            if (cafe.listaProductos.getMap().containsKey(upcNuevo)) throw new ElementoRepetidoException();
                            cafe.listaProductos.getMap().get(upc).setUpc(upcNuevo);
                            System.out.println("- Se actualizó el UPC correctamente!");
                            break;
                        } catch (IllegalArgumentException | ElementoRepetidoException x) {
                            System.out.println("- Error: " + x.getMessage());
                        }
                    }
                    break;
                case 2:
                    while(true) {
                        try {
                            System.out.println("- Ingrese nombre nuevo del producto (ingrese 0 para salir): ");
                            String nombre = sc.nextLine();
                            if(nombre.equals("0")) throw new SalirDelIngresoDeDatosException();
                            Utilidades.validarString(nombre);
                            cafe.listaProductos.getMap().get(upc).setNombre(nombre);
                            System.out.println("- Se actualizó el nombre del producto correctamente!");
                            break;
                        }catch(IllegalArgumentException x){
                            System.out.println("- Error: " + x.getMessage());
                        }
                    }
                    break;
                case 3:
                    while(true) {
                        try {
                            System.out.println(cafe.listaMarcas.mostrar());
                            System.out.println("- Seleccione marca nueva del producto (ingrese 0 para salir): ");
                            String marca = sc.nextLine();
                            if(marca.equals("0")) throw new SalirDelIngresoDeDatosException();
                            if(!cafe.listaMarcas.buscar(marca)) throw new ElementoNoEncontradoException();
                            cafe.listaProductos.getMap().get(upc).setMarca(marca);
                            System.out.println("- Se actualizó la marca correctamente!");
                            break;
                        }catch(ElementoNoEncontradoException e){
                            System.out.println("- Error: " + e.getMessage());
                        }
                    }
                    break;
                case 4:
                    while(true) {
                        try {
                            System.out.println("- Ingrese precio nuevo del producto (ingrese 0 para salir): ");
                            double precio = sc.nextDouble();
                            if(precio == 0) throw new SalirDelIngresoDeDatosException();
                            if(precio < 0) throw new IllegalArgumentException("El valor de un producto no puede ser menor a 0.");
                            cafe.listaProductos.getMap().get(upc).setPrecio(precio);
                            System.out.println("- Se actualizó el precio correctamente!");
                            break;
                        }catch(InputMismatchException | IllegalArgumentException e){
                            System.out.println("- Error: " + e.getMessage());
                            sc.nextLine();
                        }
                    }
                    break;
                case 5:
                    while(true) {
                        try {
                            System.out.println(cafe.listaProveedores.mostrarLista());
                            System.out.println("- Seleccione el proveedor del producto por su CUIL (ingrese 0 para salir): ");
                            String cuilABuscar =  sc.next();
                            if(cuilABuscar.equals("0")){
                                sc.nextLine();
                                throw new SalirDelIngresoDeDatosException();
                            }
                            Utilidades.validarCodigo(cuilABuscar,12);
                            Proveedor proveedor = cafe.listaProveedores.buscarPorId(cuilABuscar);
                            cafe.listaProductos.getMap().get(upc).setProveedor(proveedor);
                            System.out.println("- Se actualizó el proveedor correctamente!");
                            break;
                        }catch(IllegalArgumentException | ElementoNoEncontradoException e){
                            System.out.println("- Error: " + e.getMessage());
                        }
                    }
                    break;
                case 6:
                    while(true){
                        try {
                            System.out.println(cafe.listaCategorias.mostrar());
                            System.out.println("- Seleccione una categoría nueva para el producto (ingrese 0 para salir): ");
                            String categoria = sc.nextLine();
                            if(categoria.equals("0")) throw new SalirDelIngresoDeDatosException();
                            if(!cafe.listaCategorias.buscar(categoria)) throw new ElementoNoEncontradoException();
                            cafe.listaProductos.getMap().get(upc).setCategoria(categoria);
                            System.out.println("- Se actualizó la categoria correctamente!");
                            break;
                        }catch(ElementoNoEncontradoException e){
                            System.out.println("- Error: " + e.getMessage());
                        }
                    }
                    ETipoProducto et = cafe.listaCategorias.getMap().get(p.getCategoria());
                    cafe.listaProductos.getMap().get(upc).setTipoProducto(et);
                    break;
                default:
                    System.out.println("- Opción inválida.");
            }
            try {
                if (control == 's')
                    control = Utilidades.continuar("modificando datos del producto");
            } catch (IllegalArgumentException x) {
                System.out.println(x.getMessage());
            }
        }
    }

    public static void modificarPedido(Cafeteria cafe) throws ElementoNoEncontradoException, SalirDelIngresoDeDatosException {
        char control = 's';
        Pedido p = Buscar.buscarPedido(cafe);
        System.out.println(p);
        String id = String.valueOf(p.getId());
        while (control == 's') {
            System.out.println("- Seleccione el campo a modificar del pedido: ");
            System.out.println("1 - Tipo de pago. ");
            System.out.println("2 - DNI del cliente. ");
            System.out.println("3 - Productos. ");
            System.out.println("0 - Salir. ");
            int opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion) {
                case 0:
                    control = 'n';
                    break;
                case 1:
                    while (true) {
                        try {
                            System.out.println("- Ingrese el nuevo tipo de pago (EFECTIVO / TARJETA / TRANSFERENCIA) (ingrese 0 para salir): ");
                            String forma = sc.nextLine().toUpperCase();
                            if(forma.equals("0")) throw new SalirDelIngresoDeDatosException();
                            ETipoPago tipo = ETipoPago.valueOf(forma);
                            cafe.listaPedidos.getMap().get(id).setTipoPago(tipo);
                            System.out.println("- Se actualizó el tipo de pago correctamente!");
                            break;
                        } catch (IllegalArgumentException e) {
                            System.out.println("- Error: tipo de pago inválido.");
                        }
                    }
                    break;
                case 2:
                    // Lo pense que como vas a modificar un dni de un pedido y necesitas el dni del cliente, se supone que deberia de estar cargado, asi que recorres y seleccionas el dni que queres.
                    // no tiene sentido cargar un dni desde cero en modificar pedidos.
                    while (true) {
                        try {
                            // si hay un pedido hay un cliente
                            if(cafe.listaClientes.getMap().size() == 1) throw new IllegalArgumentException("- No hay otros clientes para asignar. ");
                            System.out.println("- Lista de clientes -");
                            for (Cliente cli : cafe.listaClientes.getMap().values()) {
                                System.out.println("  DNI: " + cli.getDni() + " - " + cli.getNombre() + " " + cli.getApellido());
                            }
                            System.out.println("- Ingrese el DNI del cliente que desea asignar al pedido (ingrese 0 para salir): ");
                            String dniNuevo = sc.next();
                            if(dniNuevo.equals("0")){
                                sc.nextLine();
                                throw new SalirDelIngresoDeDatosException();
                            }
                            if (!cafe.listaClientes.getMap().containsKey(dniNuevo)) throw new ElementoNoEncontradoException();
                            cafe.listaPedidos.getMap().get(id).setDniCliente(dniNuevo);
                            System.out.println("- Se actualizó el cliente correctamente!");
                            break;
                        } catch (InputMismatchException x) {
                            System.out.println("- Error: el DNI debe ser numérico.");
                            sc.nextLine();
                        } catch (IllegalArgumentException | ElementoNoEncontradoException x) {
                            System.out.println("- Error: " + x.getMessage());
                        }
                    }
                    break;
                case 3:
                    while(true){
                        try{
                            System.out.println(cafe.listaPedidos.getMap().get(id).mostrarListaDeProductos());
                            System.out.println("- Seleccione el producto a cambiar, por su UPC (ingrese 0 para salir): ");
                            String upc =  sc.next();
                            if(upc.equals("0")) throw new SalirDelIngresoDeDatosException();
                            Producto pr = cafe.listaProductos.getMap().get(upc);
                            // busca el producto en la lista de productos de la cafeteria
                            if(pr == null) throw new ElementoNoEncontradoException();
                            // busca el producto en la lista de productos del pedido
                            if(!cafe.listaPedidos.getMap().get(id).existe(pr.getNombre())) throw new ElementoNoEncontradoException();
                            cafe.listaPedidos.getMap().get(id).eliminar(pr.getNombre());
                            System.out.println("- Seleccione el producto que reemplazara el producto ya elegido, por su UPC: ");
                            System.out.println(cafe.listaProductos.mostrarLista());
                            upc = sc.next();
                            if(upc.equals("0")) throw new SalirDelIngresoDeDatosException();
                            pr = cafe.listaProductos.getMap().get(upc);
                            if(pr == null) throw new ElementoNoEncontradoException();
                            System.out.println("- Ingrese la cantidad de este producto: ");
                            int cantidad = sc.nextInt();
                            if(cantidad <= 0) throw new IllegalArgumentException("La cantidad no puede ser menor o igual a 0. ");
                            cafe.listaPedidos.getMap().get(id).agregar(pr,cantidad);
                            System.out.println("- Se actualizó la lista de productos del pedido correctamente!");
                            break;
                        }catch(IllegalArgumentException x){
                            System.out.println("- Error: " + x.getMessage());
                        } catch (ElementoRepetidoException | ElementoNoEncontradoException e) {
                            System.out.println("- " + e.getMessage());
                        }
                    }
                    break;
                default:
                    System.out.println("- Opción inválida.");
                    break;
            }
            try {
                if (control == 's') {
                    control = Utilidades.continuar("modificando datos del pedido");
                }
            } catch (IllegalArgumentException x) {
                System.out.println(x.getMessage());
            }
        }
    }

    public static void modificarMarca(Cafeteria cafe) throws ElementoNoEncontradoException, SalirDelIngresoDeDatosException {
        System.out.println(cafe.listaMarcas.mostrar());
        String marca = Buscar.buscarMarca(cafe);
        while(true) {
            try {
                System.out.println("- Ingrese el nombre nuevo de la marca (ingrese 0 para salir): ");
                String marcaNueva = sc.nextLine();
                if(marcaNueva.equals("0")) throw new SalirDelIngresoDeDatosException();
                Utilidades.validarString(marcaNueva);
                cafe.listaMarcas.eliminar(marca);
                cafe.listaMarcas.agregar(marcaNueva, ETipoProducto.VACIO);
                System.out.println("- Se actualizó el nombre correctamente!");
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("- Error: " + e.getMessage());
            }
        }
    }

    public static void modificarCategoria(Cafeteria cafe) throws ElementoNoEncontradoException, SalirDelIngresoDeDatosException {
        System.out.println(cafe.listaCategorias.mostrar());
        String categoria = Buscar.buscarCategoria(cafe);
        while(true) {
            try {
                System.out.println("- Ingrese el nombre nuevo de la categoría (ingrese 0 para salir): ");
                String categoriaNueva = sc.nextLine();
                if(categoriaNueva.equals("0")) throw new SalirDelIngresoDeDatosException();
                Utilidades.validarString(categoriaNueva);
                System.out.println("- Ingrese el tipo de producto de la categoría: ");
                System.out.println("- COMESTIBLE / BEBIBLE -");
                String tipoString = sc.next().toUpperCase();
                ETipoProducto tipoEnum;
                if(tipoString.equals("COMESTIBLE")) tipoEnum = ETipoProducto.COMESTIBLE;
                else if(tipoString.equals("BEBIBLE")) tipoEnum = ETipoProducto.BEBIBLE;
                else throw new IllegalArgumentException("- Valor inválido. ");
                cafe.listaCategorias.eliminar(categoria);
                cafe.listaCategorias.agregar(categoriaNueva, tipoEnum);
                System.out.println("- Se actualizó la categoría de producto correctamente!");
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("- Error: " + e.getMessage());
            }
        }
    }

    public static void modificarMontoMinimoPedido() throws SalirDelIngresoDeDatosException {
        while(true) {
            try {
                System.out.println("- Ingrese nuevo gasto mínimo para aplicar descuento (ingrese 0 para salir): ");
                double gastoMinimo = sc.nextDouble();
                if(gastoMinimo == 0) throw new SalirDelIngresoDeDatosException();
                if(gastoMinimo < 0) throw new IllegalArgumentException("El valor debe ser mayor a 0.");
                Pedido.setGastoMinimo(gastoMinimo);
                System.out.println("- Se actualizó el gasto minimo correctamente!");
                break;
            }catch (InputMismatchException e){
                System.out.println("- Error: El valor debe ser numérico.");
                sc.nextLine();
            }catch(IllegalArgumentException e){
                System.out.println("- Error: " + e.getMessage());
            }
        }
    }

    public static void modificarDescuentoPedido() throws SalirDelIngresoDeDatosException {
        while(true) {
            try {
                System.out.println("- Ingrese nuevo descuento para aplicar descuento (ingrese 0 para salir): ");
                double descuento = sc.nextDouble();
                if(descuento == 0) throw new SalirDelIngresoDeDatosException();
                if(descuento < 0) throw new IllegalArgumentException("El valor debe ser mayor a 0.");
                Pedido.setDescuentoAAplicar(descuento);
                System.out.println("- Se actualizó el descuento correctamente!");
                break;
            }catch (InputMismatchException e){
                System.out.println("- Error: El valor debe ser numérico.");
                sc.nextLine();
            }catch(IllegalArgumentException e){
                System.out.println("- Error: " + e.getMessage());
            }
        }
    }
}
