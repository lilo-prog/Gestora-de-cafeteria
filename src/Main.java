import Controllers.*;
import Exceptions.*;
import Models.Pedidos.Pedido;
import Models.Personas.Cliente;
import Models.Personas.Empleado;
import Models.Productos.Producto;
import Models.Proveedores.Proveedor;
import Enum.ETipoProducto;
import Enum.ETipoPago;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static Cafeteria Mudy = new Cafeteria();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws ElementoRepetidoException {
        // Trae los datos de los JSON,si es que existen
        JSONTokener tok1 = JsonUtiles.leerUnJson("Empleado.json");
        JSONTokener tok2 = JsonUtiles.leerUnJson("Cliente.json");
        JSONTokener tok3 = JsonUtiles.leerUnJson("Proveedor.json");
        JSONTokener tok4 = JsonUtiles.leerUnJson("Producto.json");
        JSONTokener tok5 = JsonUtiles.leerUnJson("Pedido.json");
        JSONTokener tok6 = JsonUtiles.leerUnJson("marcas.json");
        JSONTokener tok7 = JsonUtiles.leerUnJson("categorias.json");
        try {
            if (tok1 != null) {
                JSONArray array = new JSONArray(tok1);
                Mudy.listaEmpleadosFromJson(array);
            }
            if (tok2 != null) {
                JSONArray array = new JSONArray(tok2);
                Mudy.listaClientesFromJson(array);
            }
            if (tok3 != null) {
                JSONArray array = new JSONArray(tok3);
                Mudy.listaProveedoresFromJson(array);
            }
            if (tok4 != null) {
                JSONArray array = new JSONArray(tok4);
                Mudy.listaProductosFromJson(array);
            }
            if (tok5 != null) {
                JSONArray array = new JSONArray(tok5);
                Mudy.listaPedidosFromJson(array);
            }
            if (tok6 != null) {
                JSONArray array = new JSONArray(tok6);
                Mudy.listaMarcasFromJson(array);
            }
            if (tok7 != null) {
                JSONArray array = new JSONArray(tok7);
                Mudy.listaCategoriasFromJson(array);
            }
        } catch (JSONException  e) {
            System.out.print("");
        }

        int opcion;
        char control = 's';
        Empleado empleado1 = new Empleado("Lilo", "Trola", LocalDate.parse("2004-06-24"), 45993056, "2236182257", 230000.0);
        Mudy.listaEmpleados.agregar((long)empleado1.getDni(),empleado1);
        Empleado empleado2 = new Empleado("Bruno", "Trola", LocalDate.parse("2004-06-24"), 47089846, "2236182257", 230000.0);
        Mudy.listaEmpleados.agregar((long)empleado2.getDni(),empleado2);

        Cliente cliente1 = new Cliente("Abril", "Derdoy", LocalDate.parse("2004-11-02"), 46277898, "2236695548", 10000.50);
        Mudy.listaClientes.agregar((long)cliente1.getDni(),cliente1);
        Cliente cliente2 = new Cliente("Pichicho", "Derdoy", LocalDate.parse("2000-03-11"), 464544432, "2236969696", 6000.50);
        Mudy.listaClientes.agregar((long)cliente2.getDni(),cliente2);


        while(control == 's') {
                while(true) {
                    menuPrincipal();
                    try {
                        opcion = sc.nextInt();
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("La opcion debe ser numerica");
                        sc.nextLine();
                    }
                }
                switch (opcion) {
                    case 0:
                        control = 'n';
                        break;
                    case 1:
                        while (true) {
                            System.out.println("- Agregar -");
                            mostrarListas();
                            try {
                                opcion = sc.nextInt();
                                sc.nextLine();
                                if(opcion < 0 || opcion > 7) throw new IllegalArgumentException("Opcion invalida");
                                agregar(opcion);
                                break;
                            } catch (InputMismatchException e) {
                                System.out.println("- La opcion debe ser numerica");
                                sc.nextLine();
                            } catch (ElementoRepetidoException | ListaNoCargadaException | IllegalArgumentException e) {
                                System.out.println("Error: " + e.getMessage());
                            }
                        }
                        break;
                    case 2:
                        while(true){
                            System.out.println("- Eliminar -");
                            mostrarListas();
                            try {
                                opcion = sc.nextInt();
                                sc.nextLine();
                                if(opcion < 0 || opcion > 7) throw new IllegalArgumentException("Opcion invalida");
                                eliminar(opcion);
                                break;
                            } catch (ElementoNoEncontradoException | ListaNoCargadaException | IllegalArgumentException e) {
                                System.out.println("- Error: " + e.getMessage());
                            } catch (InputMismatchException e) {
                                System.out.println("- Error: La opcion debe ser numerica");
                                sc.nextLine();
                            }
                        }
                        break;
                    case 3:
                        while(true) {
                            System.out.println("- Buscar -");
                            mostrarListas();
                            try {
                                opcion = sc.nextInt();
                                sc.nextLine();
                                if(opcion < 0 || opcion > 7) throw new IllegalArgumentException("Opcion invalida");
                                buscar(opcion);
                                break;
                            } catch (ElementoNoEncontradoException | ListaNoCargadaException | IllegalArgumentException e) {
                                System.out.println("- Error:" + e.getMessage());
                            } catch (InputMismatchException e) {
                                System.out.println("- Error: La opcion debe ser numerica");
                                sc.nextLine();
                            }
                        }
                        break;
                    case 4:
                        while(true) {
                            System.out.println("- Modificar -");
                            mostrarListas();
                            try {
                                opcion = sc.nextInt();
                                sc.nextLine();
                                if(opcion < 0 || opcion > 7) throw new IllegalArgumentException("Opcion invalida");
                                modificar(opcion);
                                break;
                            } catch (ElementoNoEncontradoException | ListaNoCargadaException | IllegalArgumentException e) {
                                System.out.println("- Error: " + e.getMessage());
                            } catch (InputMismatchException e) {
                                System.out.println("- Error: La opcion debe ser numerica");
                                sc.nextLine();
                            }
                        }
                        break;
                    case 5:
                        while(true) {
                            System.out.println("- Mostrar -");
                            mostrarListas();
                            try {
                                opcion = sc.nextInt();
                                sc.nextLine();
                                if(opcion < 0 || opcion > 7) throw new IllegalArgumentException("Opcion invalida");
                                mostrar(opcion);
                                break;
                            } catch (ListaNoCargadaException | IllegalArgumentException e) {
                                System.out.println("- Error: " + e.getMessage());
                            } catch (InputMismatchException e) {
                                System.out.println("- Error: La opcion debe ser numerica");
                                sc.nextLine();
                            }
                        }
                        break;
                    default:
                        System.out.println("- Opción inválida.");
                        break;
                    }
                try {
                    if (control == 's') {
                        control = continuar("en el menu principal");
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    sc.nextLine();
                }
        }
        if(!Mudy.listaEmpleados.getMap().isEmpty()) Mudy.toJsonEmpleado();
        if(!Mudy.listaClientes.getMap().isEmpty()) Mudy.toJsonClientes();
        if(!Mudy.listaProveedores.getMap().isEmpty()) Mudy.toJsonProveedores();
        if(!Mudy.listaProductos.getMap().isEmpty()) Mudy.toJsonProductos();
        if(!Mudy.listaPedidos.getMap().isEmpty()) Mudy.toJsonPedidos();
        if(!Mudy.listaMarcas.getMap().isEmpty()) Mudy.toJsonMarcas();
        if(!Mudy.listaCategorias.getMap().isEmpty()) Mudy.toJsonCategorias();
        System.out.println("- Saliendo de programa...");
    }

        // Método menú principal.
    public static void menuPrincipal(){
        System.out.println("- Menú Principal -");
        System.out.println("1 - Agregar.");
        System.out.println("2 - Eliminar.");
        System.out.println("3 - Buscar.");
        System.out.println("4 - Modificar.");
        System.out.println("5 - Mostrar.");
        System.out.println("0 - Salir.");
        System.out.println("- Ingrese opción: ");
    }

        // Método mostrar listas para que el usuario elija qué clase manipular.
    public static void mostrarListas(){
        System.out.println("1 - Empleados.");
        System.out.println("2 - Clientes.");
        System.out.println("3 - Proveedores.");
        System.out.println("4 - Productos.");
        System.out.println("5 - Pedidos.");
        System.out.println("6 - Marcas.");
        System.out.println("7 - Categorías.");
        System.out.println("0 - Salir.");
        System.out.println("- Ingrese opción: ");
    }

    public static char continuar(String nombreMenu){
        char c;
        System.out.println("- Continuar " + nombreMenu + "?? (s/n): ");
        c = sc.next().toLowerCase().charAt(0);
        sc.nextLine();
        if (c != 'n' && c != 's') throw new IllegalArgumentException("- La opción debe ser 's' o 'n'. ");
        return c;
    }
    public static void validarString(String string) throws IllegalArgumentException {
        System.out.println(string);
        // esa expresion da true si tiene algo que NO es una letra de la a-z,A-Z,
        // sus variantes con acentos y espacios.
        if(string.matches(".*[^a-zA-ZáéíóúÁÉÍÓÚñÑ ].*")) throw new IllegalArgumentException("El texto ingresado no puede contener caracteres especiales");
        // esta expresion devuelve true si el string contiene un numero
        if(string.matches(".*//d*.")) throw new IllegalArgumentException("El texto ingresado no puede contener numeros");
        if(string.length() < 2) throw new IllegalArgumentException("El texto ingresado debe tener al menos 2 caracteres");
    }

        //Métodos para agregar a listas.
    public static void agregar(int opcion) throws ElementoRepetidoException, ListaNoCargadaException {
        char control ='s';
        while(control=='s') {
            switch (opcion) {
                case 0:
                    control = 'n';
                    break;
                case 1:
                    agregarEmpleado();
                    System.out.println("- Empleado cargado correctamente!");
                    break;
                case 2:
                    agregarCliente();
                    System.out.println("- Cliente cargado correctamente!");
                    break;
                case 3:
                    agregarProveedor();
                    System.out.println("- Proveedor cargado correctamente!");
                    break;
                case 4:
                    agregarProducto();
                    System.out.println("- Producto cargado correctamente!");
                    break;
                case 5:
                    agregarPedido();
                    System.out.println("- Pedido cargado correctamente!");
                    break;
                case 6:
                    agregarMarca();
                    System.out.println("- Marca cargada correctamente!");
                    break;
                case 7:
                    agregarCategoria();
                    System.out.println("- Categoría cargada correctamente!");
                    break;
                default:
                    System.out.println("- Opción inválida.");
                    break;
            }
            try{
                if(control=='s') {
                    control = continuar("agregando");
                }
            }catch(IllegalArgumentException e){
                System.out.println(e.getMessage());
                sc.nextLine();
            }
        }
    }

    public static void agregarEmpleado() throws ElementoRepetidoException{
        Empleado e = new Empleado();
        while(true) {
            try {
                System.out.println("- Ingrese nombre del empleado: ");
                e.setNombre(sc.nextLine());
                validarString(e.getNombre());
                break;
            }catch(IllegalArgumentException ex){
                System.out.println("- Error: " + ex.getMessage());
            }
        }

        while(true){
            try{
                System.out.println("- Ingrese apellido del empleado: ");
                e.setApellido(sc.nextLine());
                validarString(e.getApellido());
                break;
            }catch(IllegalArgumentException ex){
                System.out.println("- Error: " + ex.getMessage());
            }
        }

        while (true) {
            try{
                System.out.println("- Ingrese fecha de nacimiento del empleado (YYYY-MM-DD) en números: ");
                String fecha = sc.nextLine();
                LocalDate temp = LocalDate.parse(fecha);
                if(temp.isAfter(LocalDate.now())) throw new IllegalArgumentException("- La fecha no debe ser posterior al dia de hoy");
                e.setFechaNacimiento(temp);
                int edad = e.calcularEdad();
                e.setEdad(edad);
                if(edad < 16) throw new IllegalArgumentException("- La edad debe ser mayor o igual a 16 años.");
                break;
            } catch(DateTimeParseException x){
                System.out.println("- El formato de la fecha es erroneo, debe ser YYYY-MM-DD en números.");
            } catch(IllegalArgumentException ex){
                System.out.println(ex.getMessage());
            }
        }

        while(true){
            try {
                System.out.println("- Ingrese dni del empleado: ");
                int dni = sc.nextInt();
                if(dni < 10000000) throw new IllegalArgumentException("- El dni debe tener 8 dígitos.");
                e.setDni(dni);
                sc.nextLine();
                break;
            }catch(InputMismatchException x){
                System.out.println("Error: -El dni debe ser numérico.");
                sc.nextLine();
            }catch(IllegalArgumentException x){
                System.out.println("Error: " + x.getMessage());
            }
        }

        while(true){
            try {
                System.out.println("- Ingrese teléfono del empleado: ");
                String telefono = sc.nextLine();
                e.setTelefono(telefono);
                if(!e.validarTelefono()) throw new IllegalArgumentException("El telefono ingresado no es valido");
                break;
            }catch(IllegalArgumentException x){
                System.out.println("- Error: " + x.getMessage());
            }
        }

        while(true) {
            try {
                System.out.println("- Ingrese sueldo del empleado: ");
                double sueldo = sc.nextDouble();
                if(sueldo <= 0) throw new IllegalArgumentException("El sueldo debe ser mayor que 0.");
                e.setSueldo(sueldo);
                break;
            }catch(IllegalArgumentException x){
                System.out.println("- Error: " + x.getMessage());
            }catch(InputMismatchException x){
                System.out.println("- Error: El sueldo debe ser numerico ");
            }
        }

        Mudy.listaEmpleados.agregar((long) e.getDni(), e);
    }

    public static void agregarCliente() throws ElementoRepetidoException{
        Cliente c = new Cliente();
        while(true) {
            try {
                System.out.println("- Ingrese nombre del cliente: ");
                c.setNombre(sc.nextLine());
                //Si hay una secuencia de caracteres que contiene un numero tira una excepcion.
                validarString(c.getNombre());
                break;
            }catch(IllegalArgumentException ex){
                System.out.println("- Error: " + ex.getMessage());
            }
        }

        while(true){
            try{
                System.out.println("- Ingrese apellido del cliente: ");
                c.setApellido(sc.nextLine());
                validarString(c.getApellido());
                break;
            }catch(IllegalArgumentException ex){
                System.out.println("- Error: " + ex.getMessage());
            }
        }

        while (true) {
            try{
                System.out.println("- Ingrese fecha de nacimiento del cliente (YYYY-MM-DD) en números: ");
                String fecha = sc.nextLine();
                LocalDate temp = LocalDate.parse(fecha);
                if(temp.isAfter(LocalDate.now())) throw new IllegalArgumentException("La fecha no debe ser posterior al dia de hoy");
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
                System.out.println("- Ingrese dni del cliente: ");
                int dni = sc.nextInt();
                if(dni < 10000000) throw new IllegalArgumentException("El dni debe tener 8 dígitos.");
                c.setDni(dni);
                sc.nextLine();
                break;
            }catch(InputMismatchException x){
                System.out.println("- Error: El dni debe ser numérico.");
                sc.nextLine();
            }catch(IllegalArgumentException x){
                System.out.println("- Error: " + x.getMessage());
            }
        }

        while(true){
            try {
                System.out.println("- Ingrese teléfono del cliente: ");
                String telefono = sc.nextLine();
                c.setTelefono(telefono);
                if(!c.validarTelefono()) throw new IllegalArgumentException("El telefono ingresado no es valido");
                break;
            }catch(IllegalArgumentException x){
                System.out.println("- Error: " + x.getMessage());
            }
        }

        Mudy.listaClientes.agregar((long)c.getDni(), c);
    }

    public static void agregarProveedor() throws ElementoRepetidoException{
        Proveedor p = new Proveedor();
        while(true) {
            try {
                System.out.println("- Ingrese nombre del proveedor: ");
                p.setNombre(sc.nextLine());
                validarString(p.getNombre());
                break;
            }catch(IllegalArgumentException ex){
                System.out.println("- Error: " + ex.getMessage());
            }
        }

        while(true){
            try {
                System.out.println("- Ingrese cuil del proveedor: ");
                long cuil = sc.nextLong();
                if(cuil < 10000000000L) throw new IllegalArgumentException("El cuil debe tener 11 dígitos.");
                p.setCuil(cuil);
                sc.nextLine();
                break;
            }catch(InputMismatchException | IllegalArgumentException x){
                System.out.println("- Error: " + x.getMessage());
                sc.nextLine();
            }
        }

        while(true){
            try {
                System.out.println("- Ingrese teléfono del proveedor: ");
                String telefono = sc.nextLine();
                p.setTelefono(telefono);
                if(!p.validarTelefono()) throw new IllegalArgumentException("El telefono ingresado no es valido");
                break;
            }catch(IllegalArgumentException x){
                System.out.println("- Error: " + x.getMessage());
            }
        }

        Mudy.listaProveedores.agregar(p.getCuil(), p);
    }

    public static void agregarProducto() throws ElementoRepetidoException,ListaNoCargadaException{
        if(Mudy.listaMarcas.getMap().isEmpty()) throw new ListaNoCargadaException("No hay Marcas de productos");
        if(Mudy.listaCategorias.getMap().isEmpty()) throw new ListaNoCargadaException("No hay Categorias");
        if(Mudy.listaProveedores.getMap().isEmpty()) throw new ListaNoCargadaException("No hay Proveedores ingresados ");
        Producto p = new Producto();
        while(true) {
            try {
                System.out.println("- Ingrese nombre del producto: ");
                String nombre = sc.nextLine();
                validarString(nombre);
                p.setNombre(nombre);
                break;
            }catch(IllegalArgumentException x){
                System.out.println("- Error: " + x.getMessage());
            }
        }

        while(true) {
            try {
                System.out.println("- Ingrese upc del producto: ");
                long upc = sc.nextLong();
                sc.nextLine();
                if(upc < 100000000000L) throw new IllegalArgumentException("- El upc debe tener al menos 12 dígitos.");
                p.setUpc(upc);
                break;
            }catch(IllegalArgumentException | InputMismatchException x){
                System.out.println("- Error: " + x.getMessage());
                sc.nextLine();
            }
        }

        while(true) {
            try {
                System.out.println("- Seleccione marca del producto: ");
                System.out.println(Mudy.listaMarcas.mostrar());
                String marca = sc.nextLine();
                if(!Mudy.listaMarcas.buscar(marca)) throw new ElementoNoEncontradoException();
                p.setMarca(marca);
                break;
            }catch(ElementoNoEncontradoException e){
                System.out.println("- Error: " + e.getMessage());
            }
        }

        while(true) {
            try {
                System.out.println("- Ingrese precio del producto: ");
                double precio = sc.nextDouble();
                if(precio<=0) throw new IllegalArgumentException("Un producto no puede ser gratis.");
                p.setPrecio(precio);
                break;
            }catch(InputMismatchException | IllegalArgumentException e){
                System.out.println("- Error: " + e.getMessage());
                sc.nextLine();
            }
        }

        while(true) {
            try {
                System.out.println("- Seleccione el proveedor del producto por su CUIL: ");
                System.out.println(Mudy.listaProveedores.mostrarLista());
                long cuilABuscar =  sc.nextLong();
                sc.nextLine();
                Proveedor proveedor = Mudy.listaProveedores.buscarPorId(cuilABuscar);
                if(proveedor==null) throw new  ElementoNoEncontradoException();
                p.setProveedor(proveedor);
                break;
            } catch (InputMismatchException | ElementoNoEncontradoException e) {
                System.out.println("- Error: " + e.getMessage());
                sc.nextLine();
            }
        }

        while(true){
            try {
                System.out.println("- Seleccione una categoría para el producto: ");
                System.out.println(Mudy.listaCategorias.mostrar());
                String categoria = sc.nextLine();
                if(!Mudy.listaCategorias.buscar(categoria)) throw new ElementoNoEncontradoException();
                p.setCategoria(categoria);
                break;
            }catch(ElementoNoEncontradoException e){
                System.out.println("- Error: " + e.getMessage());
            }
        }
        ETipoProducto et = Mudy.listaCategorias.getMap().get(p.getCategoria());
        p.setTipoProducto(et);
        Mudy.listaProductos.agregar(p.getUpc(),p);
    }

    public static void agregarPedido() throws ElementoRepetidoException, ListaNoCargadaException {
        if(Mudy.listaProductos.getMap().isEmpty())throw new ListaNoCargadaException("No hay productos para agregar al pedido");
        if(Mudy.listaClientes.getMap().isEmpty())throw new ListaNoCargadaException("No hay clientes a quienes asignarles los pedidos");
        Pedido p = new Pedido();
        long upc;
        int cantidad;
        while(true){
            try {
                do {
                    System.out.println("- Seleccione el producto (ingrese su UPC, ingrese 0 para detener el proceso): ");
                    System.out.println(Mudy.listaProductos.mostrarLista());
                    upc =  sc.nextLong();
                    Producto pr = Mudy.listaProductos.buscarPorId(upc);
                    if(pr==null) throw new ElementoNoEncontradoException();
                    System.out.println("- Ingrese la cantidad");
                    cantidad = sc.nextInt();
                    if(cantidad<=0) throw new IllegalArgumentException("La cantidad debe ser mayor que 0");
                    p.agregar(pr,cantidad);
                }while(upc!=0);
                break;
            }catch(ElementoNoEncontradoException | IllegalArgumentException e){
                System.out.println("- Error: " + e.getMessage());
            }
        }
        p.setTotal(p.calcularTotal());
        p.setFecha(LocalDateTime.now());
        int op;
        while(true){
            try{
                System.out.println("- Seleccione el tipo de pago");
                System.out.println("- 1 EFECTIVO, 2 TRANSFERENCIA, 3 CREDITO");
                op = sc.nextInt();
                if(op == 1) p.setTipoPago(ETipoPago.EFECTIVO);
                else if(op == 2) p.setTipoPago(ETipoPago.TRANSFERENCIA);
                else if(op == 3) p.setTipoPago(ETipoPago.CREDITO);
                else throw new IllegalArgumentException("Valor invalido");
                break;
            }catch(IllegalArgumentException | InputMismatchException e ){
                System.out.println("- Error: " + e.getMessage());
            }
        }
        Mudy.listaPedidos.agregar((long)p.getId(),p);
    }

    public static void agregarMarca(){
        while(true) {
            try {
                System.out.println("- Ingrese nombre de la marca: ");
                String marca = sc.nextLine();
                validarString(marca);
                Mudy.listaMarcas.agregar(marca,ETipoProducto.VACIO);
                break;
            }catch(IllegalArgumentException ex){
                System.out.println("- Error: " + ex.getMessage());
                sc.nextLine();
            }
        }
    }

    public static void agregarCategoria(){
        while(true) {
            try {
                System.out.println("- Ingrese nombre de la categoría: ");
                String categoria = sc.nextLine();
                validarString(categoria);
                System.out.println("- Seleccione el tipo de producto que tiene esta categoria: ");
                System.out.println("COMESTIBLE / BEBIBLE ");
                String tipo = sc.next().toUpperCase();
                sc.nextLine();
                ETipoProducto et;
                if(tipo.equals("COMESTIBLE")) et = ETipoProducto.COMESTIBLE;
                else if(tipo.equals("BEBIBLE")) et = ETipoProducto.BEBIBLE;
                else throw new IllegalArgumentException("Valor inválido.");
                Mudy.listaCategorias.agregar(categoria,et);
                break;
            }catch(IllegalArgumentException ex){
                System.out.println("- Error: " + ex.getMessage());
                sc.nextLine();
            }
        }
    }

        //Métodos eliminar de la lista.
    public static void eliminar(int opcion) throws ElementoNoEncontradoException,ListaNoCargadaException{
        char control='s';
        while (control == 's') {
            switch (opcion) {
                case 0:
                    control='n';
                    break;
                case 1:
                    if (Mudy.listaEmpleados.getMap().isEmpty())
                        throw new ListaNoCargadaException("No hay empleados para eliminar");
                    eliminarEmpleado();
                    System.out.println("- Empleado eliminado correctamente!");
                    break;
                case 2:
                    if (Mudy.listaClientes.getMap().isEmpty())
                        throw new ListaNoCargadaException("No hay clientes para eliminar");
                    eliminarCliente();
                    System.out.println("- Cliente eliminado correctamente!");
                    break;
                case 3:
                    if (Mudy.listaProveedores.getMap().isEmpty())
                        throw new ListaNoCargadaException("No hay proveedores para eliminar");
                    eliminarProveedor();
                    System.out.println("- Proveedor eliminado correctamente!");
                    break;
                case 4:
                    if (Mudy.listaProductos.getMap().isEmpty())
                        throw new ListaNoCargadaException("No hay productos para eliminar");
                    eliminarProducto();
                    System.out.println("- Producto eliminado correctamente!");
                    break;
                case 5:
                    if (Mudy.listaPedidos.getMap().isEmpty())
                        throw new ListaNoCargadaException("No hay pedidos para eliminar");
                    System.out.println("- Pedido eliminado correctamente!");
                    break;
                case 6:
                    if (Mudy.listaMarcas.getMap().isEmpty())
                        throw new ListaNoCargadaException("No hay marcas para eliminar");
                    eliminarMarca();
                    System.out.println("- Marca eliminada correctamente!");
                    break;
                case 7:
                    if (Mudy.listaCategorias.getMap().isEmpty())
                        throw new ListaNoCargadaException("No hay categorias para eliminar");
                    eliminarCategoria();
                    System.out.println("- Categoría eliminada correctamente!");
                    break;
                default:
                    System.out.println("- Opción inválida.");
                    break;
            }
            try{
                if(control=='s') {
                    control = continuar("eliminando");
                }
            }catch(IllegalArgumentException e){
                System.out.println(e.getMessage());
                sc.nextLine();
            }
        }
    }

    public static void eliminarEmpleado() throws ElementoNoEncontradoException{
        while(true){
            try {
                System.out.println("- Ingrese dni del empleado para eliminarlo: ");
                int dni = sc.nextInt();
                if(dni < 10000000) throw new IllegalArgumentException("El dni debe tener 8 dígitos.");
                Mudy.listaEmpleados.eliminar((long)dni);
                break;
            }catch(InputMismatchException x){
                System.out.println("- Error: El dni debe ser numérico.");
                sc.nextLine();
            }catch(IllegalArgumentException x){
                System.out.println("- Error: " + x.getMessage());
            }
        }
    }

    public static void eliminarCliente() throws ElementoNoEncontradoException{
        while(true){
            try {
                System.out.println("- Ingrese dni del cliente para eliminarlo: ");
                int dni = sc.nextInt();
                if(dni < 10000000) throw new IllegalArgumentException("El dni debe tener 8 dígitos.");
                Mudy.listaClientes.eliminar((long)dni);
                break;
            }catch(InputMismatchException x){
                System.out.println("- Error: El dni debe ser numérico.");
                sc.nextLine();
            }catch(IllegalArgumentException x){
                System.out.println("- Error: " + x.getMessage());
            }
        }
    }

    public static void eliminarProveedor() throws ElementoNoEncontradoException{
        while(true){
            try {
                System.out.println("- Ingrese cuil del proveedor para eliminarlo: ");
                long cuil = sc.nextLong();
                if(cuil < 10000000000L) throw new IllegalArgumentException("El cuil debe tener 11 dígitos.");
                sc.nextLine();
                Mudy.listaProveedores.eliminar(cuil);
                break;
            }catch(InputMismatchException x){
                System.out.println("- Error: el cuil debe ser numerico");
            }catch(IllegalArgumentException x){
                System.out.println("- Error: " + x.getMessage());
            }
        }
    }

    public static void eliminarProducto() throws ElementoNoEncontradoException{
        while(true) {
            try {
                System.out.println("- Ingrese upc del producto para eliminarlo: ");
                long upc = sc.nextLong();
                if(upc < 100000000000L) throw new IllegalArgumentException("El upc debe tener al menos 12 dígitos.");
                Mudy.listaProductos.eliminar(upc);
                break;
            }catch(InputMismatchException x){
                System.out.println("- Error: el upc debe ser numerico ");
                sc.nextLine();
            }catch(IllegalArgumentException x){
                System.out.println("- Error: " + x.getMessage());
            }
        }
    }

    public static void eliminarMarca() throws ElementoNoEncontradoException{
        while(true) {
            try {
                System.out.println("- Ingrese nombre de la marca para eliminarla: ");
                String marca = sc.nextLine();
                if(marca.length() < 2) throw new IllegalArgumentException("El nombre debe tener al menos 2 caracteres.");
                Mudy.listaMarcas.eliminar(marca);
                break;
            }catch(IllegalArgumentException ex){
                System.out.println("- Error: " + ex.getMessage());
            }
        }
    }

    public static void eliminarCategoria() throws ElementoNoEncontradoException{
        while(true) {
            try {
                System.out.println("- Ingrese nombre de la categoría para eliminarla: ");
                String categoria = sc.nextLine();
                if(categoria.length() < 2) throw new IllegalArgumentException("El nombre debe tener al menos 2 caracteres.");
                Mudy.listaCategorias.eliminar(categoria);
                break;
            }catch(IllegalArgumentException ex){
                System.out.println("- Error: " + ex.getMessage());
            }
        }
    }

        //  Métodos buscar en las listas.
    public static void buscar(int opcion) throws ElementoNoEncontradoException,ListaNoCargadaException{
        char control = 's';
        while(control=='s') {
            switch (opcion) {
                case 0:
                    control='n';
                    break;
                case 1:
                    if (Mudy.listaEmpleados.getMap().isEmpty())
                        throw new ListaNoCargadaException("No hay empleados para buscar");
                    Empleado e = buscarEmpleado();
                    System.out.println(e);
                    break;
                case 2:
                    if (Mudy.listaClientes.getMap().isEmpty())
                        throw new ListaNoCargadaException("No hay clientes para buscar");
                    Cliente c = buscarCliente();
                    System.out.println(c);
                    break;
                case 3:
                    if (Mudy.listaProveedores.getMap().isEmpty())
                        throw new ListaNoCargadaException("No hay proveedores para buscar");
                    Proveedor p = buscarProveedor();
                    System.out.println(p);
                    break;
                case 4:
                    if (Mudy.listaProductos.getMap().isEmpty())
                        throw new ListaNoCargadaException("No hay productos para buscar");
                    Producto pr = buscarProducto();
                    System.out.println(pr);
                    break;
                case 5:
                    if (Mudy.listaPedidos.getMap().isEmpty())
                        throw new ListaNoCargadaException("No hay pedidos para buscar");
                    Pedido pe = buscarPedido();
                    System.out.println(pe);
                    break;
                case 6:
                    if (Mudy.listaMarcas.getMap().isEmpty())
                        throw new ListaNoCargadaException("No hay marcas para buscar");
                    String marca = buscarMarca();
                    System.out.println("- La marca " + marca + " se encuntra en la lista de marcas.");
                    break;
                case 7:
                    if (Mudy.listaCategorias.getMap().isEmpty())
                        throw new ListaNoCargadaException("No hay categorias para buscar");
                    String categoria = buscarCategoria();
                    System.out.println("- La categoría " + categoria + " se encuntra en la lista de categorias.");
                    break;
                default:
                    System.out.println("Opcion invalida");
                    break;
            }
            try{
                if(control=='s') {
                    control = continuar("buscando");
                }
            }catch(IllegalArgumentException e){
                System.out.println(e.getMessage());
                sc.nextLine();
            }
        }
    }

    public static Empleado buscarEmpleado() throws ElementoNoEncontradoException{
        while(true){
            try {
                System.out.println("- Ingrese dni del empleado para buscarlo: ");
                int dni = sc.nextInt();
                if(dni < 10000000) throw new IllegalArgumentException("El dni debe tener 8 dígitos.");
                return Mudy.listaEmpleados.buscarPorId((long)dni);
            }catch(InputMismatchException x){
                System.out.println("- Error: El dni debe ser numérico.");
                sc.nextLine();
            }catch(IllegalArgumentException x){
                System.out.println("Error: " + x.getMessage());
            }
        }
    }

    public static Cliente buscarCliente() throws ElementoNoEncontradoException{
        while(true){
            try {
                System.out.println("- Ingrese dni del cliente para buscarlo: ");
                int dni = sc.nextInt();
                if(dni < 10000000) throw new IllegalArgumentException("El dni debe tener 8 dígitos.");
                return Mudy.listaClientes.buscarPorId((long)dni);
            }catch(InputMismatchException x){
                System.out.println("- Error: El dni debe ser numérico.");
                sc.nextLine();
            }catch(IllegalArgumentException x){
                System.out.println("- Error:" + x.getMessage());
            }
        }
    }

    public static Proveedor buscarProveedor() throws ElementoNoEncontradoException{
        while(true){
            try {
                System.out.println("- Ingrese cuil del proveedor para buscarlo: ");
                long cuil = sc.nextLong();
                if(cuil < 10000000000L) throw new IllegalArgumentException("El cuil debe tener 11 dígitos.");
                sc.nextLine();
                return Mudy.listaProveedores.buscarPorId(cuil);
            }catch(InputMismatchException x){
                System.out.println("- Error: el cuil debe ser numerico");
                sc.nextLine();
            }catch(IllegalArgumentException x){
                System.out.println("- Error:" + x.getMessage());
            }
        }
    }

    public static Producto buscarProducto() throws ElementoNoEncontradoException{
        while(true) {
            try {
                System.out.println("- Ingrese upc del producto para buscarlo: ");
                long upc = sc.nextLong();
                if(upc < 100000000000L) throw new IllegalArgumentException("El upc debe tener al menos 12 dígitos.");
                return Mudy.listaProductos.buscarPorId(upc);
            }catch(InputMismatchException x){
                System.out.println("- Error: el upc debe ser numerico");
                sc.nextLine();
            }catch(IllegalArgumentException x){
                System.out.println("- Error: " + x.getMessage());
            }
        }
    }

    public static Pedido buscarPedido() throws ElementoNoEncontradoException{
        while(true) {
            try {
                System.out.println("- Ingrese id del pedido para buscarlo: ");
                int id = sc.nextInt();
                if(id <= 0) throw new IllegalArgumentException("No hay pedidos con ID menor o igual a 0");
                return Mudy.listaPedidos.buscarPorId((long)id);
            }catch(InputMismatchException x){
                System.out.println("- Error: el id del pedido debe ser numerico");
                sc.nextLine();
            }catch(IllegalArgumentException x){
                System.out.println("- Error: " + x.getMessage());
            }
        }
    }

    public static String buscarMarca() throws ElementoNoEncontradoException{
        while(true) {
            try {
                System.out.println("- Ingrese nombre de la marca para buscarla: ");
                String marca = sc.nextLine();
                if(marca.length() < 2) throw new IllegalArgumentException("El nombre debe tener al menos 2 caracteres.");
                if(Mudy.listaMarcas.buscar(marca)) return marca;
            }catch(IllegalArgumentException ex){
                System.out.println("- Error: " + ex.getMessage());
            }
        }
    }

    public static String buscarCategoria() throws ElementoNoEncontradoException{
        while(true) {
            try {
                System.out.println("- Ingrese nombre de la categoría para buscarla: ");
                String categoria = sc.nextLine();
                if(categoria.length() < 2) throw new IllegalArgumentException("El nombre debe tener al menos 2 caracteres.");
                if(Mudy.listaCategorias.buscar(categoria)) return categoria;
            }catch(IllegalArgumentException ex){
                System.out.println("- Error: " + ex.getMessage());
            }
        }
    }

        // Métodos modificar listas.
    public static void modificar(int opcion) throws ElementoNoEncontradoException, ListaNoCargadaException{
        char control = 's';
        while(control == 's'){
            switch(opcion){
                case 0:
                    control = 'n';
                    break;
                case 1:
                    if(Mudy.listaEmpleados.getMap().isEmpty()) throw new ListaNoCargadaException("- No hay empleados para modificar.");
                    modificarEmpleado();
                    System.out.println("- Se actualizó el empleado correctamente!");
                    break;
                case 2:
                    if (Mudy.listaClientes.getMap().isEmpty()) throw new ListaNoCargadaException("- No hay clientes para modificar.");
                    modificarCliente();
                    System.out.println("- Se actualizó el cliente correctamente!");
                    break;
                case 3:
                    if (Mudy.listaPedidos.getMap().isEmpty()) throw new ListaNoCargadaException("- No hay pedidos para modificar.");
                    modificarPedido();
                    System.out.println("- Se actualizó el pedido correctamente!");
                    break;
                case 4:
                    if (Mudy.listaProveedores.getMap().isEmpty()) throw new ListaNoCargadaException("- No hay proveedores para modificar.");
                    modificarProveedor();
                    System.out.println("- Se actualizó el proveedor correctamente!");
                    break;
                case 5:
                    if (Mudy.listaProductos.getMap().isEmpty()) throw new ListaNoCargadaException("- No hay productos para modificar.");
                    modificarProducto();
                    System.out.println("- Se actualizó el producto correctamente!");
                    break;
                case 6:
                    if (Mudy.listaMarcas.getMap().isEmpty()) throw new ListaNoCargadaException("- No hay marcas para modificar.");
                    modificarMarca();
                    System.out.println("- Se actualizó la marca correctamente!");
                    break;
                case 7:
                    if (Mudy.listaCategorias.getMap().isEmpty()) throw new ListaNoCargadaException("- No hay categorias para modificar.");
                    modificarCategoria();
                    System.out.println("- Se actualizó la categoria correctamente!");
                    break;
                default:
                    System.out.println("- Opcion invalida.");
                    break;
            }
            try{
                if(control=='s') {
                    control = continuar("modificando");
                }
            }catch(IllegalArgumentException e){
                System.out.println(e.getMessage());
                sc.nextLine();
            }
        }
    }

    public static void modificarEmpleado() throws ElementoNoEncontradoException {
        char control = 's';
        Empleado e = buscarEmpleado();
        System.out.println(e);
        long dni = e.getDni();
        while(control == 's') {
            System.out.println("- Seleccione el campo a modificar: ");
            System.out.println("- 1. DNI. ");
            System.out.println("- 2. Nombre. ");
            System.out.println("- 3. Apellido. ");
            System.out.println("- 4. Fecha de nacimiento. ");
            System.out.println("- 5. Teléfono. ");
            System.out.println("- 6. Sueldo. ");
            System.out.println("- 0. Salir. ");
            int opcion = sc.nextInt();
            switch (opcion) {
                case 0:
                    control = 'n';
                    break;
                case 1:
                    while (true) {
                        try {
                            System.out.println("- Ingrese el DNI nuevo del empleado: ");
                            int dniNuevo = sc.nextInt();
                            sc.nextLine();
                            if (dniNuevo < 10000000L) throw new IllegalArgumentException("El DNI debe tener 8 dígitos");
                            if(Mudy.listaEmpleados.getMap().containsKey((long)dniNuevo)) throw new ElementoRepetidoException();
                            Mudy.listaEmpleados.getMap().get(dni).setDni(dniNuevo);
                            break;
                        } catch (InputMismatchException x) {
                            System.out.println("- Error: el dni debe ser numerico");
                            sc.nextLine();
                        } catch (IllegalArgumentException x) {
                            System.out.println("- Error: " + x.getMessage());
                            sc.nextLine();
                        } catch (ElementoRepetidoException x){
                            System.out.println(x.getMessage());
                        }
                    }
                    break;
                case 2:
                    while (true) {
                        try {
                            System.out.println("- Ingrese nuevo nombre del empleado: ");
                            Mudy.listaEmpleados.getMap().get(dni).setNombre(sc.nextLine());
                            //Si hay una secuencia de caracteres que contiene un número tira una excepción.
                            if (Mudy.listaEmpleados.getMap().get(dni).getNombre().matches(".*\\d.*"))
                                throw new IllegalArgumentException("- El nombre no puede contener números.");
                            if (Mudy.listaEmpleados.getMap().get(dni).getNombre().length() < 2)
                                throw new IllegalArgumentException("- El nombre debe tener al menos 2 caracteres.");
                            break;
                        } catch (IllegalArgumentException ex) {
                            System.out.println("- Error: " + ex.getMessage());
                        }
                    }
                    break;
                case 3:
                    while (true) {
                        try {
                            System.out.println("- Ingrese nuevo apellido del empleado: ");
                            Mudy.listaEmpleados.getMap().get(dni).setApellido(sc.nextLine());
                            //Si hay una secuencia de caracteres que contiene un número tira una excepción.
                            if (Mudy.listaEmpleados.getMap().get(dni).getApellido().matches(".*\\d.*"))
                                throw new IllegalArgumentException("El apellido no puede contener números.");
                            if (Mudy.listaEmpleados.getMap().get(dni).getApellido().length() < 2)
                                throw new IllegalArgumentException("El apellido debe tener al menos 2 caracteres.");
                            break;
                        } catch (IllegalArgumentException ex) {
                            System.out.println("- Error: " + ex.getMessage());
                        }
                    }
                    break;
                case 4:
                    while (true) {
                        try {
                            System.out.println("- Ingrese la nueva fecha de nacimiento del empleado (YYYY-MM-DD) en números: ");
                            String fecha = sc.nextLine();
                            LocalDate temp = LocalDate.parse(fecha);
                            if (temp.isAfter(LocalDate.now()))
                                throw new IllegalArgumentException("La fecha no debe ser posterior al dia de hoy");
                            Mudy.listaEmpleados.getMap().get(dni).setFechaNacimiento(temp);
                            int edad = e.calcularEdad();
                            Mudy.listaEmpleados.getMap().get(dni).setEdad(edad);
                            if (edad < 16)
                                throw new IllegalArgumentException("La edad debe ser mayor o igual a 16 años.");
                            break;
                        } catch (DateTimeParseException x) {
                            System.out.println("- El formato de la fecha es erroneo, debe ser YYYY-MM-DD en números.");
                        } catch (IllegalArgumentException ex) {
                            System.out.println("- Error: " + ex.getMessage());
                        }
                    }
                    break;
                case 5:
                    while (true) {
                        try {
                            System.out.println("- Ingrese el nuevo teléfono del empleado: ");
                            String telefono = sc.nextLine();
                            Mudy.listaEmpleados.getMap().get(dni).setTelefono(telefono);
                            if (!Mudy.listaEmpleados.getMap().get(dni).validarTelefono())
                                throw new IllegalArgumentException("El teléfono ingresado no es válido");
                            break;
                        } catch (IllegalArgumentException x) {
                            System.out.println("- Error: " + x.getMessage());
                        }
                    }
                    break;
                case 6:
                    while (true) {

                        try {
                            System.out.println("- Ingrese nuevo sueldo del empleado: ");
                            double sueldo = sc.nextDouble();
                            if (sueldo <= 0) throw new IllegalArgumentException("El sueldo debe ser mayor que 0.");
                            Mudy.listaEmpleados.getMap().get(dni).setSueldo(sueldo);
                            break;
                        } catch (IllegalArgumentException x) {
                            System.out.println("- Error: " + x.getMessage());
                        } catch (InputMismatchException x) {
                            System.out.println("- Error: el sueldo debe ser numérico");
                        }
                    }
                    break;
                default:
                    System.out.println("- Opción inválida.");
                    break;
            }
            try{
                if(control=='s') {
                    control=continuar("modificando datos del empleado");
                }
            }catch(IllegalArgumentException x){
                System.out.println(x.getMessage());
            }
        }
    }

    public static void modificarCliente() throws ElementoNoEncontradoException {
        char control = 's';
        Cliente c = buscarCliente();
        System.out.println(c);
        long dni = c.getDni();

        while (control == 's') {
            System.out.println("- Seleccione el campo a modificar: ");
            System.out.println("- 1. DNI");
            System.out.println("- 2. Nombre");
            System.out.println("- 3. Apellido");
            System.out.println("- 4. Fecha de nacimiento");
            System.out.println("- 5. Teléfono");
            System.out.println("- 6. Recalcular Gastos totales");
            System.out.println("- 0. Salir");

            int opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 0:
                    control = 'n';
                    break;

                case 1:
                    while (true) {
                        try {
                            System.out.println("- Ingrese el DNI nuevo del cliente: ");
                            int dniNuevo = sc.nextInt();
                            sc.nextLine();
                            if (dniNuevo < 10000000L) throw new IllegalArgumentException("El DNI debe tener 8 dígitos.");
                            if (Mudy.listaClientes.getMap().containsKey((long)dniNuevo)) throw new ElementoRepetidoException();
                            Mudy.listaClientes.getMap().get(dni).setDni(dniNuevo);
                            break;
                        } catch (InputMismatchException x) {
                            System.out.println("- Error: el DNI debe ser numérico");
                            sc.nextLine();
                        } catch (IllegalArgumentException x) {
                            System.out.println("- Error: " + x.getMessage());
                        } catch (ElementoRepetidoException x) {
                            System.out.println(x.getMessage());
                        }
                    }
                    break;

                case 2:
                    while (true) {
                        try {
                            System.out.println("- Ingrese nuevo nombre del cliente: ");
                            String nombre = sc.nextLine();
                            validarString(nombre);
                            Mudy.listaClientes.getMap().get(dni).setNombre(sc.nextLine());
                            break;
                        } catch (IllegalArgumentException ex) {
                            System.out.println("- Error: " + ex.getMessage());
                        }
                    }

                    break;

                case 3:
                    while (true) {
                        try {
                            System.out.println("- Ingrese nuevo apellido del cliente: ");
                            String apellido = sc.nextLine();
                            validarString(apellido);
                            Mudy.listaClientes.getMap().get(dni).setApellido(sc.nextLine());
                            break;
                        } catch (IllegalArgumentException ex) {
                            System.out.println("- Error: " + ex.getMessage());
                        }
                    }
                    break;

                case 4:
                    while (true) {
                        try {
                            System.out.println("- Ingrese la nueva fecha de nacimiento del cliente (YYYY-MM-DD): ");
                            String fecha = sc.nextLine();
                            LocalDate temp = LocalDate.parse(fecha);
                            if (temp.isAfter(LocalDate.now())) throw new IllegalArgumentException("La fecha no debe ser posterior al día de hoy.");
                            Mudy.listaClientes.getMap().get(dni).setFechaNacimiento(temp);
                            break;

                        } catch (DateTimeParseException x) {
                            System.out.println("- El formato de fecha es incorrecto. Debe ser YYYY-MM-DD.");
                        } catch (IllegalArgumentException ex) {
                            System.out.println("- Error: " + ex.getMessage());
                        }
                    }
                    break;

                case 5:
                    while (true) {
                        try {
                            System.out.println("- Ingrese nuevo teléfono del cliente: ");
                            String telefono = sc.nextLine();
                            Mudy.listaClientes.getMap().get(dni).setTelefono(telefono);
                            if (!Mudy.listaClientes.getMap().get(dni).validarTelefono()) throw new IllegalArgumentException("El teléfono ingresado no es válido.");
                            break;
                        } catch (IllegalArgumentException ex) {
                            System.out.println("- Error: " + ex.getMessage());
                        }
                    }
                    break;

                case 6:
                    Mudy.calcularGastoTotalDeCliente(c.getDni());
                    break;

                default:
                    System.out.println("- Opción inválida.");
            }

            try {
                if (control == 's')
                    control = continuar("modificando datos del cliente");
            } catch (IllegalArgumentException x) {
                System.out.println(x.getMessage());
            }
        }
    }

    public static void modificarPedido() throws ElementoNoEncontradoException{
        char control = 's';
        Pedido p = buscarPedido();
        System.out.println(p);

        int id = p.getId();

        while (control == 's') {

            System.out.println("- Seleccione el campo a modificar:");
            System.out.println("1. Tipo de pago");
            System.out.println("2. DNI del cliente");
            System.out.println("3. Productos");
            System.out.println("0. Salir");

            int opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {

                case 0:
                    control = 'n';
                    break;

                case 1:
                    while (true) {
                        try {
                            System.out.println("- Ingrese el nuevo tipo de pago (EFECTIVO / TARJETA / TRANSFERENCIA):");
                            String forma = sc.nextLine().toUpperCase();

                            ETipoPago tipo = ETipoPago.valueOf(forma);

                            Mudy.listaPedidos.getMap().get((long)id).setTipoPago(tipo);
                            break;

                        } catch (IllegalArgumentException e) {
                            System.out.println("- Error: tipo de pago inválido.");
                        }
                    }
                    break;

                case 2: //Lo pense que como vas a modificar un dni de un pedido y necesitas el dni del cliente, se supone que deberia de estar cargado, asi que recorres y seleccionas el dni que queres.
                    //no tiene sentido cargar un dni desde cero en modificar pedidos.
                    while (true) {
                        try {
                            // si hay un pedido hay un cliente
                            if(Mudy.listaClientes.getMap().size()==1) throw new IllegalArgumentException("No hay otros clientes para asignar");
                            System.out.println("- Lista de clientes: ");

                            for (Cliente cli : Mudy.listaClientes.getMap().values()) {
                                System.out.println("  DNI: " + cli.getDni() + " - " +
                                        cli.getNombre() + " " + cli.getApellido());
                            }

                            System.out.println("- Ingrese el DNI del cliente que desea asignar al pedido:");
                            long dniNuevo = sc.nextLong();
                            sc.nextLine();

                            if (!Mudy.listaClientes.getMap().containsKey(dniNuevo)) throw new ElementoNoEncontradoException();

                            Mudy.listaPedidos.getMap().get((long)id).setDniCliente((int) dniNuevo);
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
                            System.out.println("- Seleccione el producto a cambiar, por su UPC");
                            System.out.println(Mudy.listaPedidos.getMap().get((long)id).mostrarListaDeProductos());
                            long upc =  sc.nextLong();
                            Producto pr = Mudy.listaProductos.getMap().get(upc);
                            // busca el producto en la lista de productos de la cafeteria
                            if(pr==null) throw new ElementoNoEncontradoException();

                            // busca el producto en la lista de productos del pedido
                            if(!Mudy.listaPedidos.getMap().get((long)id).existe(pr.getNombre())) throw new ElementoNoEncontradoException();

                            Mudy.listaPedidos.getMap().get((long)id).eliminar(pr.getNombre());
                            System.out.println("- Seleccione el producto que reemplazara el producto ya elegido, por su UPC");
                            System.out.println(Mudy.listaProductos.mostrarLista());
                            upc = sc.nextLong();
                            pr = Mudy.listaProductos.getMap().get(upc);
                            if(pr==null) throw new ElementoNoEncontradoException();
                            System.out.println("- Ingrese la cantidad de este producto");
                            int cantidad = sc.nextInt();
                            if(cantidad<=0) throw new IllegalArgumentException("La cantidad no puede ser menor o igual a 0");
                            Mudy.listaPedidos.getMap().get((long)id).agregar(pr,cantidad);
                            break;
                        }catch(IllegalArgumentException | InputMismatchException x){
                            System.out.println("Error: " + x.getMessage());
                        } catch (ElementoRepetidoException | ElementoNoEncontradoException e) {
                            System.out.println(e.getMessage());;
                        }
                    }
                    break;

                default:
                    System.out.println("- Opción inválida.");
                    break;
            }

            try {
                if (control == 's') {
                    control = continuar("modificando datos del pedido");
                }
            } catch (IllegalArgumentException x) {
                System.out.println(x.getMessage());
            }
        }
    }

    public static void modificarProveedor() throws ElementoNoEncontradoException {
        char control = 's';
        Proveedor p = buscarProveedor();
        System.out.println(p);
        long cuil = p.getCuil();
        int opcion;
        while (control == 's') {
            while(true) {
                System.out.println("- Seleccione el campo a modificar:");
                System.out.println("1. Nombre");
                System.out.println("2. CUIL");
                System.out.println("3. telefono");
                System.out.println("0. Salir");
                try {
                    opcion = sc.nextInt();
                    break;
                }catch(InputMismatchException e){
                    System.out.println("- La opcion debe ser numerica");
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
                            System.out.println("- Ingrese el nuevo nombre: ");
                            String nombre = sc.nextLine();
                            sc.nextLine();
                            validarString(nombre);
                            Mudy.listaProveedores.getMap().get(cuil).setNombre(nombre);
                            break;
                        }catch(IllegalArgumentException e){
                            System.out.println("- Error: " + e.getMessage());
                        }
                    }
                    break;
                case 2:
                    while(true){
                        try{
                            System.out.println("- Ingrese el nuevo cuil (solo numeros)");
                            long cuilNuevo = sc.nextLong();
                            if(cuilNuevo < 10000000000L) throw new IllegalArgumentException("El cuil debe tener 11 digitos");
                            Mudy.listaProveedores.getMap().get(cuil).setCuil(cuilNuevo);
                            break;
                        }catch(IllegalArgumentException e){
                            System.out.println("- Error: " + e.getMessage());
                        }catch(InputMismatchException e){
                            System.out.println("- Error: El cuil debe ser numerico");
                            sc.nextLine();
                        }
                    }
                    break;
                case 3:
                    while(true){
                        try {
                            System.out.println("- Ingrese el nuevo telefono");
                            Mudy.listaProveedores.getMap().get(p.getCuil()).setTelefono(sc.nextLine());
                            if(!Mudy.listaProveedores.getMap().get(p.getCuil()).validarTelefono()) throw new IllegalArgumentException("El telefono ingresado no es valido");
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
                    control = continuar("modificando datos del proveedor");
                }
            } catch (IllegalArgumentException x) {
                System.out.println(x.getMessage());
            }
        }
    }

    public static void modificarProducto() throws ElementoNoEncontradoException {
        char control = 's';
        Producto p = buscarProducto();
        System.out.println(p);
        long upc = p.getUpc();

        while (control == 's') {
            System.out.println("- Seleccione el campo a modificar: ");
            System.out.println("- 1. UPC");
            System.out.println("- 2. Nombre");
            System.out.println("- 3. Marca");
            System.out.println("- 4. Precio");
            System.out.println("- 5. Proveedor");
            System.out.println("- 6. Categoria");
            System.out.println("- 0. Salir");

            int opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 0:
                    control = 'n';
                    break;

                case 1:
                    while (true) {
                        try {
                            System.out.println("- Ingrese el UPC nuevo del producto: ");
                            long upcNuevo = sc.nextLong();
                            sc.nextLine();
                            if (upcNuevo < 100000000000L) throw new IllegalArgumentException("El UPC debe tener 12 dígitos.");
                            if (Mudy.listaProductos.getMap().containsKey(upcNuevo)) throw new ElementoRepetidoException();
                            Mudy.listaProductos.getMap().get(upc).setUpc(upcNuevo);
                            break;
                        } catch (InputMismatchException x) {
                            System.out.println("- Error: el UPC debe ser numérico");
                            sc.nextLine();
                        } catch (IllegalArgumentException x) {
                            System.out.println("- Error: " + x.getMessage());
                        } catch (ElementoRepetidoException x) {
                            System.out.println(x.getMessage());
                        }
                    }
                    break;

                case 2:
                    while(true) {
                        try {
                            System.out.println("- Ingrese nombre nuevo del producto: ");
                            String nombre = sc.nextLine();
                            validarString(nombre);
                            Mudy.listaProductos.getMap().get(upc).setNombre(nombre);
                            break;
                        }catch(IllegalArgumentException x){
                            System.out.println("- Error: " + x.getMessage());
                        }
                    }
                    break;

                case 3:
                    while(true) {
                        try {
                            System.out.println("- Seleccione marca nueva del producto: ");
                            System.out.println(Mudy.listaMarcas.mostrar());
                            String marca = sc.nextLine();
                            if(!Mudy.listaMarcas.buscar(marca)) throw new ElementoNoEncontradoException();
                            Mudy.listaProductos.getMap().get(upc).setMarca(marca);
                            break;
                        }catch(ElementoNoEncontradoException e){
                            System.out.println("- Error: " + e.getMessage());
                        }
                    }
                    break;

                case 4:
                    while(true) {
                        try {
                            System.out.println("- Ingrese precio nuevo del producto: ");
                            double precio = sc.nextDouble();
                            if(precio<=0) throw new IllegalArgumentException("Un producto no puede ser gratis.");
                            Mudy.listaProductos.getMap().get(upc).setPrecio(precio);
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
                            System.out.println("- Seleccione el proveedor del producto por su CUIL: ");
                            System.out.println(Mudy.listaProveedores.mostrarLista());
                            long cuilABuscar =  sc.nextLong();
                            sc.nextLine();
                            Proveedor proveedor = Mudy.listaProveedores.buscarPorId(cuilABuscar);
                            if(proveedor==null) throw new ElementoNoEncontradoException();
                            Mudy.listaProductos.getMap().get(upc).setProveedor(proveedor);
                            break;
                        } catch (InputMismatchException | ElementoNoEncontradoException e) {
                            System.out.println("- Error: " + e.getMessage());
                            sc.nextLine();
                        }
                    }
                    break;

                case 6:
                    while(true){
                        try {
                            System.out.println("- Seleccione una categoría nueva para el producto: ");
                            System.out.println(Mudy.listaCategorias.mostrar());
                            String categoria = sc.nextLine();
                            if(!Mudy.listaCategorias.buscar(categoria)) throw new ElementoNoEncontradoException();
                            Mudy.listaProductos.getMap().get(upc).setCategoria(categoria);
                            break;
                        }catch(ElementoNoEncontradoException e){
                            System.out.println("- Error: " + e.getMessage());
                        }
                    }
                    ETipoProducto et = Mudy.listaCategorias.getMap().get(p.getCategoria());
                    Mudy.listaProductos.getMap().get(upc).setTipoProducto(et);
                    break;

                default:
                    System.out.println("- Opción inválida.");
            }

            try {
                if (control == 's')
                    control = continuar("modificando datos del producto");
            } catch (IllegalArgumentException x) {
                System.out.println(x.getMessage());
            }
        }
    }

    public static void modificarMarca() throws ElementoNoEncontradoException{
        String marca = buscarMarca();
        while(true) {
            try {
                System.out.println("- Ingrese el nombre nuevo de la marca");
                String marcaNueva = sc.nextLine();
                validarString(marcaNueva);
                Mudy.listaMarcas.eliminar(marca);
                Mudy.listaMarcas.agregar(marcaNueva, ETipoProducto.VACIO);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("- Error: " + e.getMessage());
            }
        }
    }

    public static void modificarCategoria() throws ElementoNoEncontradoException{
        String categoria = buscarCategoria();
        while(true) {
            try {
                System.out.println("- Ingrese el nombre nuevo de la categoria");
                String categoriaNueva = sc.nextLine();
                validarString(categoriaNueva);
                System.out.println("- Ingrese el tipo de producto de la categoria");
                System.out.println("- COMESTIBLE / BEBIBLE");
                String tipoString = sc.next().toUpperCase();
                ETipoProducto tipoEnum;
                if(tipoString.equals("COMESTIBLE")) tipoEnum = ETipoProducto.COMESTIBLE;
                else if(tipoString.equals("BEBIBLE")) tipoEnum = ETipoProducto.BEBIBLE;
                else throw new IllegalArgumentException("Valor invalido");
                Mudy.listaCategorias.eliminar(categoria);
                Mudy.listaCategorias.agregar(categoriaNueva, tipoEnum);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("- Error: " + e.getMessage());
            }
        }
    }
        // Métodos mostrar listas cargadas.
    public static void mostrar(int opcion) throws ListaNoCargadaException{
        char control = 's';
        while(control=='s') {
            switch (opcion) {
                case 0:
                    control='n';
                    break;
                case 1:
                    if (Mudy.listaEmpleados.getMap().isEmpty())
                        throw new ListaNoCargadaException("No hay empleados para mostrar");
                    System.out.println(Mudy.listaEmpleados.mostrarLista());
                    break;
                case 2:
                    if (Mudy.listaClientes.getMap().isEmpty())
                        throw new ListaNoCargadaException("No hay clientes para mostrar");
                    System.out.println(Mudy.listaClientes.mostrarLista());
                    break;
                case 3:
                    if (Mudy.listaProveedores.getMap().isEmpty())
                        throw new ListaNoCargadaException("No hay proveedores para mostrar");
                    System.out.println(Mudy.listaProveedores.mostrarLista());
                    break;
                case 4:
                    if (Mudy.listaProductos.getMap().isEmpty())
                        throw new ListaNoCargadaException("No hay productos para mostrar");
                    System.out.println(Mudy.listaProductos.mostrarLista());
                    break;
                case 5:
                    if (Mudy.listaPedidos.getMap().isEmpty())
                        throw new ListaNoCargadaException("No hay pedidos para mostrar");
                    System.out.println(Mudy.listaPedidos.mostrarLista());
                    break;
                case 6:
                    if (Mudy.listaMarcas.getMap().isEmpty())
                        throw new ListaNoCargadaException("No hay marcas para mostrar");
                    System.out.println(Mudy.listaMarcas.mostrar());
                    break;
                case 7:
                    if (Mudy.listaCategorias.getMap().isEmpty())
                        throw new ListaNoCargadaException("No hay categorias para mostrar");
                    System.out.println(Mudy.listaCategorias.mostrar());
                    break;
                default:
                    System.out.println("- Opción inválida.");
                    break;
            }
            try{
                if(control=='s') {
                    control = continuar("mostrando");
                }
            }catch(IllegalArgumentException e){
                System.out.println(e.getMessage());
                sc.nextLine();
            }
        }
    }

}