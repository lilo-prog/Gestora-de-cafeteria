import Controllers.*;
import Exceptions.*;
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

public class Main {
    static Cafeteria Mudy = new Cafeteria();

    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        int opcion;
        char control = 's';

        while(control == 's') {
                menuPrincipal();
                opcion = sc.nextInt();
                sc.nextLine();
                switch (opcion) {
                    case 0:
                        control = 'n';
                        break;
                    case 1:
                        System.out.println("- Agregar -");
                        mostrarListas();
                        opcion = sc.nextInt();
                        sc.nextLine();

                        try {
                            agregar(opcion);
                        } catch (ElementoRepetidoException | ListaNoCargadaException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 2:
                        System.out.println("- Eliminar -");
                        mostrarListas();
                        opcion = sc.nextInt();
                        sc.nextLine();

                        try {
                            eliminar(opcion);
                        }catch (ElementoNoEncontradoException | ListaNoCargadaException e){
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 3:
                        System.out.println("- Buscar -");
                        mostrarListas();
                        opcion = sc.nextInt();
                        sc.nextLine();

                        try{
                            buscar(opcion);
                        } catch(ElementoNoEncontradoException | ListaNoCargadaException e){
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 4:
                        System.out.println("- Mostrar -");
                        mostrarListas();
                        opcion = sc.nextInt();
                        sc.nextLine();
                        try {
                            mostrar(opcion);
                        }catch(ListaNoCargadaException e){
                            System.out.println(e.getMessage());
                        }
                        break;
                    default:
                        System.out.println("- Opción inválida. Debe elegir entre 1 y 4.");
                        break;
                }

            if (control == 's') {
                while(true) {
                    try {
                        System.out.println("- Continuar?? (s/n): ");
                        control = sc.next().charAt(0);
                        if (control != 'n' && control != 's')
                            throw new IllegalArgumentException("- La opción debe ser 's' o 'n'. ");
                        break;
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());

                    }
                }
            }
        }
        System.out.println("- Saliendo de programa...");
    }

        // Método menú principal.
    public static void menuPrincipal(){
        System.out.println("- Menú Principal -");
        System.out.println("1 - Agregar.");
        System.out.println("2 - Eliminar.");
        System.out.println("3 - Buscar.");
        System.out.println("4 - Mostrar.");
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

        //Métodos para agregar a listas.
    public static void agregar(int opcion) throws ElementoRepetidoException, InputMismatchException, ListaNoCargadaException {
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
            if(control=='s'){
                System.out.println("Quiere continuar? (s/n)");
                control = sc.next().charAt(0);
            }
            if(control=='n'){
                System.out.println("Dejando de agregar...");
                break;
            }
        }
    }

    public static void agregarEmpleado() throws ElementoRepetidoException{
        Empleado e = new Empleado();
        while(true) {
            try {
                System.out.println("- Ingrese nombre del empleado: ");
                e.setNombre(sc.nextLine());
                //Si hay una secuencia de caracteres que contiene un número tira una excepción.
                if(e.getNombre().matches(".*\\d.*")) throw new IllegalArgumentException("- El nombre no puede contener números.");
                if(e.getNombre().length() < 2) throw new IllegalArgumentException("- El nombre debe tener al menos 2 caracteres.");
                break;
            }catch(IllegalArgumentException ex){
                System.out.println("- Error: " + ex.getMessage());
            }
        }

        while(true){
            try{
                System.out.println("- Ingrese apellido del empleado: ");
                e.setApellido(sc.nextLine());
                if(e.getApellido().matches(".*\\d.*")) throw new IllegalArgumentException("- El apellido no puede contener números.");
                if(e.getApellido().length() < 2) throw new IllegalArgumentException("- El apellido debe tener al menos 2 caracteres.");
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
                if(sueldo <= 0) throw new IllegalArgumentException("- El sueldo debe ser mayor que 0.");
                e.setSueldo(sueldo);
                break;
            }catch(IllegalArgumentException | InputMismatchException x){
                System.out.println("- Error: " + x.getMessage());
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
                if(c.getNombre().matches(".*\\d.*")) throw new IllegalArgumentException("El nombre no puede contener números.");
                if(c.getNombre().length() < 2) throw new IllegalArgumentException("El nombre debe tener al menos 2 caracteres.");
                break;
            }catch(IllegalArgumentException ex){
                System.out.println("- Error: " + ex.getMessage());
            }
        }

        while(true){
            try{
                System.out.println("- Ingrese apellido del cliente: ");
                c.setApellido(sc.nextLine());
                if(c.getApellido().matches(".*\\d.*")) throw new IllegalArgumentException("El apellido no puede contener números.");
                if(c.getApellido().length() < 2) throw new IllegalArgumentException("El apellido debe tener al menos 2 caracteres.");
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
                //Si hay una secuencia de caracteres que contiene un numero tira una excepcion.
                if(p.getNombre().matches(".*\\d.*")) throw new IllegalArgumentException("El nombre no puede contener números.");
                if(p.getNombre().length() < 2) throw new IllegalArgumentException("El nombre debe tener al menos 2 caracteres.");
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
        if(Mudy.listaMarcas.getLista().isEmpty()) throw new ListaNoCargadaException("No hay Marcas de productos");
        if(Mudy.listaCategorias.getLista().isEmpty()) throw new ListaNoCargadaException("No hay Categorias");
        if(Mudy.listaProveedores.getMap().isEmpty()) throw new ListaNoCargadaException("No hay Proveedores ingresados ");
        Producto p = new Producto();
        while(true) {
            try {
                System.out.println("- Ingrese nombre del producto: ");
                String nombre = sc.nextLine();
                if(nombre.matches(".*\\d.*")) throw new IllegalArgumentException("El nombre no puede contener números.");
                if(nombre.length()<2) throw new IllegalArgumentException("El nombre debe tener al menos 2 caracteres.");
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

        // DESCRIPCION PENDIENTE POR DISEÑO (ABSTRACCION).

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

        while(true){
            try {
                System.out.println("- Seleccione el tipo de producto: ");
                System.out.println("1 - COMESTIBLE , 2 - BEBIBLE ");
                int op = sc.nextInt();
                sc.nextLine();
                if(op == 1) p.setTipoProducto(ETipoProducto.COMESTIBLE);
                if(op == 2) p.setTipoProducto(ETipoProducto.BEBIBLE);
                else throw new IllegalArgumentException("- Valor inválido.");
                break;
            }catch(InputMismatchException | IllegalArgumentException e){
                System.out.println("- Error: " + e.getMessage());
                sc.nextLine();
            }
        }

        Mudy.listaProductos.agregar(p.getUpc(),p);
    }

    public static void agregarPedido() throws ElementoRepetidoException {
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
        int op = 0;
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
                if(marca.length() < 2) throw new IllegalArgumentException("El nombre debe tener al menos 2 caracteres.");
                Mudy.listaMarcas.agregar(marca);
                break;
            }catch(IllegalArgumentException ex){
                System.out.println("- Error: " + ex.getMessage());
            }
        }
    }

    public static void agregarCategoria(){
        while(true) {
            try {
                System.out.println("- Ingrese nombre de la categoría: ");
                String categoria = sc.nextLine();
                //Si hay una secuencia de caracteres que contiene un número tira una excepción.
                if(categoria.matches(".*\\d.*")) throw new IllegalArgumentException("El nombre no puede contener números.");
                if(categoria.length() < 2) throw new IllegalArgumentException("El nombre debe tener al menos 2 caracteres.");
                Mudy.listaCategorias.agregar(categoria);
                break;
            }catch(IllegalArgumentException ex){
                System.out.println("- Error: " + ex.getMessage());
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
                    if (Mudy.listaMarcas.getLista().isEmpty())
                        throw new ListaNoCargadaException("No hay marcas para eliminar");
                    eliminarMarca();
                    System.out.println("- Marca eliminada correctamente!");
                    break;
                case 7:
                    if (Mudy.listaCategorias.getLista().isEmpty())
                        throw new ListaNoCargadaException("No hay categorias para eliminar");
                    eliminarCategoria();
                    System.out.println("- Categoría eliminada correctamente!");
                    break;
                default:
                    System.out.println("- Opción inválida.");
                    break;
            }
            if(control=='s'){
                System.out.println("Quiere seguir eliminando? (s/n)");
                control = sc.next().charAt(0);
            }
            if(control=='n'){
                System.out.println("Dejando de eliminar");
                break;
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
                    if (Mudy.listaMarcas.getLista().isEmpty())
                        throw new ListaNoCargadaException("No hay marcas para buscar");
                    String marca = buscarMarca();
                    System.out.println("- La marca " + marca + " se encuntra en la lista de marcas.");
                    break;
                case 7:
                    if (Mudy.listaCategorias.getLista().isEmpty())
                        throw new ListaNoCargadaException("No hay categorias para buscar");
                    String categoria = buscarCategoria();
                    System.out.println("- La categoría " + categoria + " se encuntra en la lista de categorias.");
                    break;
                default:
                    System.out.println("Opcion invalida");
                    break;
            }
            if(control=='s'){
                System.out.println("Quiere seguir buscando? (s/n)");
                control=sc.next().charAt(0);
            }
            if(control=='n'){
                System.out.println("Dejando de buscar...");
                break;
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
                    if (Mudy.listaMarcas.getLista().isEmpty())
                        throw new ListaNoCargadaException("No hay marcas para mostrar");
                    System.out.println(Mudy.listaMarcas.mostrar());
                    break;
                case 7:
                    if (Mudy.listaCategorias.getLista().isEmpty())
                        throw new ListaNoCargadaException("No hay categorias para mostrar");
                    System.out.println(Mudy.listaCategorias.mostrar());
                    break;
                default:
                    System.out.println("- Opción inválida.");
                    break;
            }
            if(control == 's'){
                System.out.println("Quiere continuar mostrando? (s/n)");
                control=sc.next().charAt(0);
            }
            if(control == 'n'){
                System.out.println("Dejando de mostrar...");
                break;
            }
        }
    }

}