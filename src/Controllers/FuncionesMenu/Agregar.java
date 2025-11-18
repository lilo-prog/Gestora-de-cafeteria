package Controllers.FuncionesMenu;

import Controllers.Cafeteria;
import Exceptions.ElementoNoEncontradoException;
import Exceptions.ElementoRepetidoException;
import Exceptions.ListaNoCargadaException;
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
    public static void agregar(int opcion, Cafeteria cafe) throws ElementoRepetidoException, ListaNoCargadaException, ElementoNoEncontradoException {
        char control ='s';
        while(control=='s') {
            switch (opcion) {
                case 0:
                    control = 'n';
                    break;
                case 1:
                    agregarEmpleado(cafe);
                    System.out.println("- Empleado cargado correctamente!");
                    break;
                case 2:
                    agregarCliente(cafe);
                    System.out.println("- Cliente cargado correctamente!");
                    break;
                case 3:
                    agregarProveedor(cafe);
                    System.out.println("- Proveedor cargado correctamente!");
                    break;
                case 4:
                    agregarProducto(cafe);
                    System.out.println("- Producto cargado correctamente!");
                    break;
                case 5:
                    agregarPedido(cafe);
                    System.out.println("- Pedido cargado correctamente!");
                    break;
                case 6:
                    agregarMarca(cafe);
                    System.out.println("- Marca cargada correctamente!");
                    break;
                case 7:
                    agregarCategoria(cafe);
                    System.out.println("- Categoría cargada correctamente!");
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
                sc.nextLine();
            }
        }
    }

    public static void agregarEmpleado(Cafeteria cafe) throws ElementoRepetidoException{
        Empleado e = new Empleado();
        while(true) {
            try {
                System.out.println("- Ingrese nombre del empleado: ");
                e.setNombre(sc.nextLine());
                Utilidades.validarString(e.getNombre());
                break;
            }catch(IllegalArgumentException ex){
                System.out.println("- Error: " + ex.getMessage());
            }
        }

        while(true){
            try{
                System.out.println("- Ingrese apellido del empleado: ");
                e.setApellido(sc.nextLine());
                Utilidades.validarString(e.getApellido());
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

        cafe.listaEmpleados.agregar((long) e.getDni(), e);
    }

    public static void agregarCliente(Cafeteria cafe) throws ElementoRepetidoException{
        Cliente c = new Cliente();
        while(true) {
            try {
                System.out.println("- Ingrese nombre del cliente: ");
                c.setNombre(sc.nextLine());
                //Si hay una secuencia de caracteres que contiene un numero tira una excepcion.
                Utilidades.validarString(c.getNombre());
                break;
            }catch(IllegalArgumentException ex){
                System.out.println("- Error: " + ex.getMessage());
            }
        }

        while(true){
            try{
                System.out.println("- Ingrese apellido del cliente: ");
                c.setApellido(sc.nextLine());
                Utilidades.validarString(c.getApellido());
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

        cafe.listaClientes.agregar((long)c.getDni(), c);
    }

    public static void agregarProveedor(Cafeteria cafe) throws ElementoRepetidoException{
        Proveedor p = new Proveedor();
        while(true) {
            try {
                System.out.println("- Ingrese nombre del proveedor: ");
                p.setNombre(sc.nextLine());
                Utilidades.validarString(p.getNombre());
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

        cafe.listaProveedores.agregar(p.getCuil(), p);
    }

    public static void agregarProducto(Cafeteria cafe) throws ElementoRepetidoException,ListaNoCargadaException{
        if(cafe.listaMarcas.getMap().isEmpty()) throw new ListaNoCargadaException("No hay Marcas de productos");
        if(cafe.listaCategorias.getMap().isEmpty()) throw new ListaNoCargadaException("No hay Categorias");
        if(cafe.listaProveedores.getMap().isEmpty()) throw new ListaNoCargadaException("No hay Proveedores ingresados ");
        Producto p = new Producto();
        while(true) {
            try {
                System.out.println("- Ingrese nombre del producto: ");
                String nombre = sc.nextLine();
                Utilidades.validarString(nombre);
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
                System.out.println(cafe.listaMarcas.mostrar());
                String marca = sc.nextLine();
                if(!cafe.listaMarcas.buscar(marca)) throw new ElementoNoEncontradoException();
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
                System.out.println(cafe.listaProveedores.mostrarLista());
                long cuilABuscar =  sc.nextLong();
                sc.nextLine();
                Proveedor proveedor = cafe.listaProveedores.buscarPorId(cuilABuscar);
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
                System.out.println(cafe.listaCategorias.mostrar());
                String categoria = sc.nextLine();
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

    public static void agregarPedido(Cafeteria cafe) throws ElementoRepetidoException, ListaNoCargadaException, ElementoNoEncontradoException {
        if(cafe.listaProductos.getMap().isEmpty())throw new ListaNoCargadaException("No hay productos para agregar al pedido.");
        if(cafe.listaClientes.getMap().isEmpty())throw new ListaNoCargadaException("No hay clientes a quienes asignarles los pedidos.");
        Pedido p = new Pedido();
        long upc;
        int cantidad;
        while(true){
            try {
                do {
                    System.out.println("- Seleccione el producto (ingrese su UPC, ingrese 0 para detener el proceso): ");
                    System.out.println(cafe.listaProductos.mostrarLista());
                    upc =  sc.nextLong();
                    Producto pr = cafe.listaProductos.buscarPorId(upc);

                    System.out.println("- Ingrese la cantidad: ");
                    cantidad = sc.nextInt();
                    if(cantidad <= 0) throw new IllegalArgumentException("La cantidad debe ser mayor que 0.");
                    p.agregar(pr,cantidad);
                }while(upc == 0);
                break;
            }catch(ElementoNoEncontradoException | IllegalArgumentException e){
                System.out.println("- Error: " + e.getMessage());
            }
        }
        p.setTotal(p.calcularTotal());
        p.setFecha(LocalDateTime.now());
        while(true){
            try{
                System.out.println("- Seleccione el tipo de pago: ");
                System.out.println("- EFECTIVO / TRANSFERENCIA / CREDITO -");
                String tipo = sc.next().toUpperCase();
                if(tipo.equals("EFECTIVO")) p.setTipoPago(ETipoPago.EFECTIVO);
                else if(tipo.equals("TRANSFERENCIA")) p.setTipoPago(ETipoPago.TRANSFERENCIA);
                else if(tipo.equals("CREDITO")) p.setTipoPago(ETipoPago.CREDITO);
                else throw new IllegalArgumentException("Valor inválido.");
                break;
            }catch(IllegalArgumentException | InputMismatchException e ){
                System.out.println("- Error: " + e.getMessage());
            }
        }
        while(true){
            try{
                System.out.println("- Ingrese DNI del cliente: ");
                int dni = sc.nextInt();
                if(dni < 10000000) throw new IllegalArgumentException("El dni debe tener 8 dígitos.");
                Cliente c = cafe.listaClientes.buscarPorId((long)dni);
                p.setDniCliente(dni);

                break;
            }catch(ElementoNoEncontradoException e){
                System.out.println(e.getMessage());
            }catch(InputMismatchException x){
                System.out.println("- Error: El dni debe ser numérico.");
                sc.nextLine();
            }catch(IllegalArgumentException x){
                System.out.println("- Error: " + x.getMessage());
            }
        }

        p.calcularTotal();
        cafe.listaPedidos.agregar((long)p.getId(),p);

        cafe.calcularGastoTotalDeCliente(p.getDniCliente());

        cafe.calcularDescuento(p.getDniCliente(), Pedido.getGastoMinimo(), Pedido.getDescuentoAAplicar());
        Cliente cliente = cafe.listaClientes.buscarPorId((long)p.getDniCliente());
        cafe.listaPedidos.getMap().get(upc).setDescuentoAAplicar(cliente.getDescuento());

    }

    public static void agregarMarca(Cafeteria cafe){
        while(true) {
            try {
                System.out.println("- Ingrese nombre de la marca: ");
                String marca = sc.nextLine();
                Utilidades.validarString(marca);
                cafe.listaMarcas.agregar(marca, ETipoProducto.VACIO);
                break;
            }catch(IllegalArgumentException ex){
                System.out.println("- Error: " + ex.getMessage());
            }
        }
    }

    public static void agregarCategoria(Cafeteria cafe){
        while(true) {
            try {
                System.out.println("- Ingrese nombre de la categoría: ");
                String categoria = sc.nextLine();
                Utilidades.validarString(categoria);
                System.out.println("- Seleccione el tipo de producto que tiene esta categoria: ");
                System.out.println("COMESTIBLE / BEBIBLE ");
                String tipo = sc.next().toUpperCase();
                sc.nextLine();
                ETipoProducto et;
                if(tipo.equals("COMESTIBLE")) et = ETipoProducto.COMESTIBLE;
                else if(tipo.equals("BEBIBLE")) et = ETipoProducto.BEBIBLE;
                else throw new IllegalArgumentException("Valor inválido.");
                cafe.listaCategorias.agregar(categoria,et);
                break;
            }catch(IllegalArgumentException ex){
                System.out.println("- Error: " + ex.getMessage());
                sc.nextLine();
            }
        }
    }
}
