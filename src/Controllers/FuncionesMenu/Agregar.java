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
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Agregar {
    static Scanner sc = new Scanner(System.in);
    //Métodos para agregar a listas.
    public static void agregar(Cafeteria cafe) throws ElementoRepetidoException, ListaNoCargadaException, ElementoNoEncontradoException {
        char control = 's';
        int opcion = 0;
        while(control == 's') {
            try {
                System.out.println("-----------------------------------");
                System.out.println("- Agregar -");
                Utilidades.mostrarListasModificar();
                opcion = sc.nextInt();
                sc.nextLine();
                System.out.println("-----------------------------------");
            } catch (InputMismatchException e) {
                System.out.println("- Error: la opción debe ser numérica.");
                sc.nextLine();
            }
            switch (opcion) {
                case 0:
                    control = 'n';
                    break;
                case 1:
                    try {
                        agregarEmpleado(cafe);
                        System.out.println("- Empleado cargado correctamente!");
                    } catch (SalirDelIngresoDeDatosException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        agregarCliente(cafe);
                        System.out.println("- Cliente cargado correctamente!");
                    } catch (SalirDelIngresoDeDatosException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        agregarProveedor(cafe);
                        System.out.println("- Proveedor cargado correctamente!");
                    } catch (SalirDelIngresoDeDatosException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    try {
                        agregarProducto(cafe);
                        System.out.println("- Producto cargado correctamente!");
                    } catch (SalirDelIngresoDeDatosException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 5:
                    try {
                        agregarPedido(cafe);
                        System.out.println("- Pedido cargado correctamente!");
                    } catch (SalirDelIngresoDeDatosException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 6:
                    try {
                        agregarMarca(cafe);
                        System.out.println("- Marca cargada correctamente!");
                    } catch (SalirDelIngresoDeDatosException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 7:
                    try {
                        agregarCategoria(cafe);
                        System.out.println("- Categoría cargada correctamente!");
                    } catch (SalirDelIngresoDeDatosException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 8:
                    if(Pedido.gastoMinimo == 0.0){
                        try{
                            agregarMontoMinimo();
                        } catch (SalirDelIngresoDeDatosException e){
                            System.out.println(e.getMessage());
                        }
                    } else System.out.println("- Monto mínimo ingresado anteriormente. Vaya a modificar.");
                    break;
                case 9:
                    if(Pedido.descuentoAAplicar == 0.0) {
                        try {
                            agregarDescuento();
                        } catch (SalirDelIngresoDeDatosException e) {
                            System.out.println(e.getMessage());
                        }
                    } else System.out.println("- Descuento ingresado anteriormente. Vaya a modificar.");
                    break;
                default:
                    System.out.println("- Opción inválida.");
                    break;
            }
            try{
                if(control == 's') {
                    control = Utilidades.continuar("agregando");
                }
            }catch(IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public static void agregarEmpleado(Cafeteria cafe) throws ElementoRepetidoException, SalirDelIngresoDeDatosException{
        Empleado e = new Empleado();
        while(true){
            try {
                System.out.println("- Ingrese DNI del empleado (ingrese 0 para salir): ");
                String dni = sc.next();
                if(dni.equals("0")){
                    sc.nextLine();
                    throw new SalirDelIngresoDeDatosException();
                }
                Utilidades.validarCodigo(dni,8);
                e.setDni(dni);
                sc.nextLine();
                break;
            }catch(IllegalArgumentException x){
                System.out.println("- Error: " + x.getMessage());
            }
        }
        while(true) {
            try {
                System.out.println("- Ingrese nombre del empleado (ingrese 0 para salir): ");
                String nombre = sc.nextLine();
                if(nombre.equals("0")) throw new SalirDelIngresoDeDatosException();
                Utilidades.validarString(nombre);
                e.setNombre(nombre);
                break;
            }catch(IllegalArgumentException ex){
                System.out.println("- Error: " + ex.getMessage());
            }
        }
        while(true){
            try{
                System.out.println("- Ingrese apellido del empleado (ingrese 0 para salir): ");
                String apellido = sc.nextLine();
                if(apellido.equals("0")) throw new SalirDelIngresoDeDatosException();
                Utilidades.validarString(apellido);
                e.setApellido(apellido);
                break;
            }catch(IllegalArgumentException ex){
                System.out.println("- Error: " + ex.getMessage());
            }
        }
        while (true) {
            try{
                System.out.println("- Ingrese fecha de nacimiento del empleado (YYYY-MM-DD) en números (ingrese 0 para salir): ");
                String fecha = sc.nextLine();
                if(fecha.equals("0")) throw new SalirDelIngresoDeDatosException();
                LocalDate temp = LocalDate.parse(fecha);
                if(temp.isAfter(LocalDate.now())) throw new IllegalArgumentException("La fecha no debe ser posterior al dia de hoy");
                e.setFechaNacimiento(temp);
                int edad = e.calcularEdad();
                e.setEdad(edad);
                if(edad < 16) throw new IllegalArgumentException("La edad debe ser mayor o igual a 16 años.");
                break;
            } catch(DateTimeParseException x){
                System.out.println("- El formato de la fecha es erroneo, debe ser YYYY-MM-DD en números.");
            } catch(IllegalArgumentException ex){
                System.out.println("- Error: " + ex.getMessage());
            }
        }
        while(true){
            try {
                System.out.println("- Ingrese teléfono del empleado (ingrese 0 para salir): ");
                String telefono = sc.nextLine();
                if(telefono.equals("0")) throw new SalirDelIngresoDeDatosException();
                e.setTelefono(telefono);
                if(!e.validarTelefono()) throw new IllegalArgumentException("El télefono ingresado no es válido.");
                break;
            }catch(IllegalArgumentException x){
                System.out.println("- Error: " + x.getMessage());
            }
        }
        while(true) {
            try {
                System.out.println("- Ingrese sueldo del empleado (ingrese 0 para salir): ");
                double sueldo = sc.nextDouble();
                if(sueldo == 0){
                    sc.nextLine();
                    throw new SalirDelIngresoDeDatosException();
                }
                if(sueldo < 0) throw new IllegalArgumentException("El sueldo debe ser mayor que 0.");
                e.setSueldo(sueldo);
                break;
            }catch(IllegalArgumentException x){
                System.out.println("- Error: " + x.getMessage());
            }catch(InputMismatchException x){
                System.out.println("- Error: El sueldo debe ser numérico ");
                sc.nextLine();
            }
        }
        cafe.listaEmpleados.agregar(e.getDni(), e);
    }

    public static void agregarCliente(Cafeteria cafe) throws ElementoRepetidoException, SalirDelIngresoDeDatosException {
        Cliente c = new Cliente();
        while(true){
            try {
                System.out.println("- Ingrese DNI del cliente (ingrese 0 para salir): ");
                String dni = sc.next();
                sc.nextLine();
                if(dni.equals("0")) {
                    throw new SalirDelIngresoDeDatosException();
                }
                Utilidades.validarCodigo(dni,8);
                c.setDni(dni);
                break;
            }catch(IllegalArgumentException x){
                System.out.println("- Error: " + x.getMessage());
            }
        }
        while(true) {
            try {
                System.out.println("- Ingrese nombre del cliente (ingrese 0 para salir): ");
                String nombre = sc.nextLine();
                if(nombre.equals("0")) throw new SalirDelIngresoDeDatosException();
                Utilidades.validarString(nombre);
                c.setNombre(nombre);
                break;
            }catch(IllegalArgumentException ex){
                System.out.println("- Error: " + ex.getMessage());
            }
        }
        while(true){
            try{
                System.out.println("- Ingrese apellido del cliente (ingrese 0 para salir): ");
                String apellido = sc.nextLine();
                if(apellido.equals("0")) throw new SalirDelIngresoDeDatosException();
                Utilidades.validarString(apellido);
                c.setApellido(apellido);
                break;
            }catch(IllegalArgumentException ex){
                System.out.println("- Error: " + ex.getMessage());
            }
        }
        while (true) {
            try{
                System.out.println("- Ingrese fecha de nacimiento del cliente (YYYY-MM-DD) en números (ingrese 0 para salir): ");
                String fecha = sc.nextLine();
                if(fecha.equals("0")) throw new SalirDelIngresoDeDatosException();
                LocalDate temp = LocalDate.parse(fecha);
                if(temp.isAfter(LocalDate.now())) throw new IllegalArgumentException("La fecha no debe ser posterior al día de hoy.");
                c.setFechaNacimiento(temp);
                int edad = c.calcularEdad();
                c.setEdad(edad);
                if(edad < 16) throw new IllegalArgumentException("La edad debe ser mayor o igual a 16 años.");
                break;
            } catch(DateTimeParseException x){
                System.out.println("- El formato de la fecha es erroneo, debe ser YYYY-MM-DD en números.");
            } catch(IllegalArgumentException ex){
                System.out.println("- Error: " + ex.getMessage());
            }
        }
        while(true){
            try {
                System.out.println("- Ingrese teléfono del cliente (ingrese 0 para salir): ");
                String telefono = sc.nextLine();
                if(telefono.equals("0")) throw new SalirDelIngresoDeDatosException();
                c.setTelefono(telefono);
                if(!c.validarTelefono()) throw new IllegalArgumentException("El télefono ingresado no es válido.");
                break;
            }catch(IllegalArgumentException x){
                System.out.println("- Error: " + x.getMessage());
            }
        }
        cafe.listaClientes.agregar(c.getDni(), c);
    }

    public static void agregarProveedor(Cafeteria cafe) throws ElementoRepetidoException, SalirDelIngresoDeDatosException{
        Proveedor p = new Proveedor();
        while(true) {
            try {
                System.out.println("- Ingrese nombre del proveedor (ingrese 0 para salir): ");
                String nombre = sc.nextLine();
                if(nombre.equals("0")) throw new SalirDelIngresoDeDatosException();
                Utilidades.validarString(nombre);
                p.setNombre(nombre);
                break;
            }catch(IllegalArgumentException ex){
                System.out.println("- Error: " + ex.getMessage());
            }
        }
        while(true){
            try {
                System.out.println("- Ingrese CUIL del proveedor (ingrese 0 para salir): ");
                String cuil = sc.next();
                if(cuil.equals("0")){
                    sc.nextLine();
                    throw new SalirDelIngresoDeDatosException();
                }
                Utilidades.validarCodigo(cuil,11);
                p.setCuil(cuil);
                sc.nextLine();
                break;
            }catch(IllegalArgumentException x){
                System.out.println("- Error: " + x.getMessage());
                sc.nextLine();
            }
        }
        while(true){
            try {
                System.out.println("- Ingrese teléfono del proveedor (ingrese 0 para salir): ");
                String telefono = sc.nextLine();
                if(telefono.equals("0")) throw new SalirDelIngresoDeDatosException();
                p.setTelefono(telefono);
                if(!p.validarTelefono()) throw new IllegalArgumentException("El télefono ingresado no es válido.");
                break;
            }catch(IllegalArgumentException x){
                System.out.println("- Error: " + x.getMessage());
            }
        }
        cafe.listaProveedores.agregar(p.getCuil(), p);
    }

    public static void agregarProducto(Cafeteria cafe) throws ElementoRepetidoException, ListaNoCargadaException, SalirDelIngresoDeDatosException{
        if(cafe.listaMarcas.getMap().isEmpty()) throw new ListaNoCargadaException("No hay marcas de productos.");
        if(cafe.listaCategorias.getMap().isEmpty()) throw new ListaNoCargadaException("No hay categorías.");
        if(cafe.listaProveedores.getMap().isEmpty()) throw new ListaNoCargadaException("No hay proveedores ingresados.");
        Producto p = new Producto();
        while(true) {
            try {
                System.out.println("- Ingrese nombre del producto (ingrese 0 para salir): ");
                String nombre = sc.nextLine();
                if(nombre.equals("0")) throw new SalirDelIngresoDeDatosException();
                Utilidades.validarString(nombre);
                p.setNombre(nombre);
                break;
            }catch(IllegalArgumentException x){
                System.out.println("- Error: " + x.getMessage());
            }
        }
        while(true) {
            try {
                System.out.println("- Ingrese UPC del producto (no debe empezar con 0)(ingrese 0 para salir): ");
                String upc = sc.next();
                sc.nextLine();
                if(upc.equals("0")) throw new SalirDelIngresoDeDatosException();
                Utilidades.validarCodigo(upc,12);
                p.setUpc(upc);
                break;
            }catch(IllegalArgumentException x){
                System.out.println("- Error: " + x.getMessage());
            }
        }
        while(true) {
            try {
                System.out.println("- Seleccione marca del producto (ingrese 0 para salir): ");
                System.out.println(cafe.listaMarcas.mostrar());
                String marca = sc.nextLine();
                if(marca.equals("0")) throw new SalirDelIngresoDeDatosException();
                if(!cafe.listaMarcas.buscar(marca)) throw new ElementoNoEncontradoException();
                p.setMarca(marca);
                break;
            }catch(ElementoNoEncontradoException e){
                System.out.println("- Error: " + e.getMessage());
            }
        }
        while(true) {
            try {
                System.out.println("- Ingrese precio del producto (ingrese 0 para salir): ");
                double precio = sc.nextDouble();
                if(precio == 0){
                    sc.nextLine();
                    throw new SalirDelIngresoDeDatosException();
                }
                if(precio < 0) throw new IllegalArgumentException("El precio de un producto no puede ser menor a 0.");
                p.setPrecio(precio);
                break;
            }catch(InputMismatchException e){
                System.out.println("- Error: El precio debe ser numérico.");
                sc.nextLine();
            }catch(IllegalArgumentException e){
                System.out.println("- Error: " + e.getMessage());
            }
        }
        while(true) {
            try {
                System.out.println("- Seleccione el proveedor del producto por su CUIL (ingrese 0 para salir): ");
                System.out.println(cafe.listaProveedores.mostrarLista());
                String cuilABuscar = sc.next();
                sc.nextLine();
                if(cuilABuscar.equals("0")) throw new SalirDelIngresoDeDatosException();
                Proveedor proveedor = cafe.listaProveedores.buscarPorId(cuilABuscar);
                if(proveedor == null) throw new  ElementoNoEncontradoException();
                p.setProveedor(proveedor);
                break;
            } catch (ElementoNoEncontradoException e){
                System.out.println("- Error: " + e.getMessage());
            }
        }
        while(true){
            try {
                System.out.println("- Seleccione una categoría para el producto (ingrese 0 para salir): ");
                System.out.println(cafe.listaCategorias.mostrar());
                String categoria = sc.nextLine();
                if(categoria.equals("0")) throw new SalirDelIngresoDeDatosException();
                if(!cafe.listaCategorias.buscar(categoria)) throw new ElementoNoEncontradoException();
                p.setCategoria(categoria);
                break;
            }catch(ElementoNoEncontradoException e){
                System.out.println("- Error: " + e.getMessage());
            }
        }
        ETipoProducto et = cafe.listaCategorias.getMap().get(p.getCategoria());
        p.setTipoProducto(et);
        cafe.listaProductos.agregar(p.getUpc(),p);
    }

    public static void agregarPedido(Cafeteria cafe) throws ElementoRepetidoException, ListaNoCargadaException, ElementoNoEncontradoException, SalirDelIngresoDeDatosException {
        if(cafe.listaProductos.getMap().isEmpty())throw new ListaNoCargadaException("No hay productos para agregar al pedido.");
        if(cafe.listaClientes.getMap().isEmpty())throw new ListaNoCargadaException("No hay clientes a quienes asignarles los pedidos.");
        Pedido p = new Pedido();
        String upc;
        int cantidad;
        while(true){
            try {
                System.out.println(cafe.listaProductos.mostrarLista());
                System.out.println("- Seleccione el producto por su UPC (ingrese 0 para salir): ");
                upc = sc.next();
                if(upc.equals("0")) throw new SalirDelIngresoDeDatosException();
                Producto pr = cafe.listaProductos.buscarPorId(upc);
                System.out.println("- Ingrese la cantidad (ingrese 0 para salir): ");
                cantidad = sc.nextInt();
                sc.nextLine();
                if(cantidad <= 0) throw new IllegalArgumentException("La cantidad debe ser mayor que 0.");
                p.agregar(pr,cantidad);
                break;
            } catch(ElementoNoEncontradoException | IllegalArgumentException e){
                System.out.println("- Error: " + e.getMessage());
            } catch (InputMismatchException e){
                System.out.println("- Error: valor inválido.");
                sc.nextLine();
            }
        }
        while(true){
            try{
                System.out.println("- Seleccione el tipo de pago (ingrese 0 para salir): ");
                System.out.println("- EFECTIVO / TRANSFERENCIA / CREDITO -");
                String tipo = sc.next().toUpperCase();
                // Hicimos un if, pero intellij nos recomendó hacer el switch.
                switch (tipo) {
                    case "0" -> throw new SalirDelIngresoDeDatosException();
                    case "EFECTIVO" -> p.setTipoPago(ETipoPago.EFECTIVO);
                    case "TRANSFERENCIA" -> p.setTipoPago(ETipoPago.TRANSFERENCIA);
                    case "CREDITO" -> p.setTipoPago(ETipoPago.CREDITO);
                    default -> throw new IllegalArgumentException("Valor inválido.");
                }
                break;
            }catch(IllegalArgumentException e ){
                System.out.println("- Error: " + e.getMessage());
            }
        }
        while(true){
            try{
                System.out.println("- Ingrese DNI del cliente (ingrese 0 para salir): ");
                String dni = sc.next();
                if(dni.equals("0")) throw new SalirDelIngresoDeDatosException();
                Utilidades.validarCodigo(dni,8);
                cafe.listaClientes.buscarPorId(dni);
                p.setDniCliente(dni);
                break;
            }catch(ElementoNoEncontradoException e){
                System.out.println(e.getMessage());
            }catch(IllegalArgumentException x){
                System.out.println("- Error: " + x.getMessage());
            }
        }
        p.setTotal(p.calcularTotal());
        p.setFecha(LocalDateTime.now());
        cafe.listaPedidos.agregar(String.valueOf(p.getId()),p);

        cafe.calcularGastoTotalDeCliente(p.getDniCliente());

        cafe.calcularDescuento(p.getDniCliente(), Pedido.getGastoMinimo(), Pedido.getDescuentoAAplicar());
        Cliente cliente = cafe.listaClientes.buscarPorId(p.getDniCliente());
        Pedido.setDescuentoAAplicar(cliente.getDescuento());
        Cafeteria.aplicarDescuento(p,cliente);
    }

    public static void agregarMarca(Cafeteria cafe) throws SalirDelIngresoDeDatosException{
        while(true) {
            try {
                System.out.println("- Ingrese nombre de la marca (ingrese 0 para salir): ");
                String marca = sc.nextLine();
                if(marca.equals("0")) throw new SalirDelIngresoDeDatosException();
                Utilidades.validarString(marca);
                cafe.listaMarcas.agregar(marca, ETipoProducto.VACIO);
                break;
            }catch(IllegalArgumentException ex){
                System.out.println("- Error: " + ex.getMessage());
            }
        }
    }

    public static void agregarCategoria(Cafeteria cafe) throws SalirDelIngresoDeDatosException{
        while(true) {
            try {
                System.out.println("- Ingrese nombre de la categoría (ingrese 0 para salir): ");
                String categoria = sc.nextLine();
                if(categoria.equals("0")) throw new SalirDelIngresoDeDatosException();
                Utilidades.validarString(categoria);
                System.out.println("- Seleccione el tipo de producto que tiene esta categoria (ingrese 0 para salir): ");
                System.out.println("COMESTIBLE / BEBIBLE ");
                String tipo = sc.next().toUpperCase();
                if(tipo.equals("0")) throw new SalirDelIngresoDeDatosException();
                ETipoProducto et;
                if(tipo.equals("COMESTIBLE")) et = ETipoProducto.COMESTIBLE;
                else if(tipo.equals("BEBIBLE")) et = ETipoProducto.BEBIBLE;
                else throw new IllegalArgumentException("Valor inválido.");
                cafe.listaCategorias.agregar(categoria,et);
                break;
            }catch(IllegalArgumentException ex){
                System.out.println("- Error: " + ex.getMessage());
            }
        }
    }

    public static void agregarMontoMinimo() throws SalirDelIngresoDeDatosException {
        try {
            System.out.println("- Ingrese monto mínimo para aplicar descuento (ingrese 0 para salir): ");
            double montoMinimo = sc.nextDouble();
            sc.nextLine();
            if(montoMinimo == 0) throw new SalirDelIngresoDeDatosException();
            if(montoMinimo < 0) throw new IllegalArgumentException("El monto debe ser mayor a 0.");
            Pedido.setGastoMinimo(montoMinimo);
        } catch (IllegalArgumentException e) {
            System.out.println("- Error: " + e.getMessage());
        }
    }

    public static void agregarDescuento() throws SalirDelIngresoDeDatosException {
        try {
            System.out.println("- Ingrese descuento: ");
            double descuento = sc.nextDouble();
            sc.nextLine();
            if(descuento < 0) throw new IllegalArgumentException("El monto debe ser mayor a 0.");
            Pedido.setDescuentoAAplicar(descuento);
        } catch (IllegalArgumentException e) {
            System.out.println("- Error: " + e.getMessage());
        }catch(InputMismatchException e){
            System.out.println("- Error: El descuento debe ser numérico");
            sc.nextLine();
        }
    }
}