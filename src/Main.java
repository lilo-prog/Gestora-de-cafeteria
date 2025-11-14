import Controllers.*;
import Exceptions.*;
import Models.Personas.Cliente;
import Models.Personas.Empleado;
import Models.Productos.Producto;
import Models.Proveedores.Proveedor;
import Enum.ETipoProducto;
import java.time.LocalDate;
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
                        } catch (ElementoRepetidoException e) {
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
                        }catch (ElementoNoEncontradoException e){
                            e.getMessage();
                        }
                        break;
                    case 3:
                        System.out.println("- Buscar -");
                        mostrarListas();

                        break;
                    case 4:
                        System.out.println("- Mostrar -");
                        mostrarListas();
                        opcion = sc.nextInt();
                        sc.nextLine();
                        mostrar(opcion);
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
                            throw new CaracteresException("- La opción debe ser 's' o 'n'. ");
                        break;
                    } catch (CaracteresException e) {
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
        System.out.println("- Ingrese opción: ");
    }

        //Métodos para agregar a listas.
    public static void agregar(int opcion) throws ElementoRepetidoException, InputMismatchException {
        switch(opcion){
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
                break;
            case 6:
                break;
            case 7:
                break;
            default:
                System.out.println("- Opción inválida.");
                break;
        }
    }

    public static void agregarEmpleado() throws ElementoRepetidoException{
        Empleado e = new Empleado();
        while(true) {
            try {
                System.out.println("- Ingrese nombre del empleado: ");
                e.setNombre(sc.nextLine());
                //Si hay una secuencia de caracteres que contiene un número tira una excepción.
                if(e.getNombre().matches(".*\\d.*")) throw new CaracteresException("- El nombre no puede contener números.");
                if(e.getNombre().length() < 2) throw new CaracteresException("- El nombre debe tener al menos 2 caracteres.");
                break;
            }catch(CaracteresException ex){
                System.out.println("Error: " + ex.getMessage());
            }
        }

        while(true){
            try{
                System.out.println("- Ingrese apellido del empleado: ");
                e.setApellido(sc.nextLine());
                if(e.getApellido().matches(".*\\d.*")) throw new CaracteresException("- El apellido no puede contener números.");
                if(e.getApellido().length() < 2) throw new CaracteresException("- El apellido debe tener al menos 2 caracteres.");
                break;
            }catch(CaracteresException ex){
                System.out.println("Error: " + ex.getMessage());
            }
        }

        while (true) {
            try{
                System.out.println("- Ingrese fecha de nacimiento del empleado (YYYY-MM-DD) en números: ");
                String fecha = sc.nextLine();
                LocalDate temp = LocalDate.parse(fecha);
                if(temp.isAfter(LocalDate.now())) throw new FechaPosteriorException();
                e.setFechaNacimiento(temp);
                int edad = e.calcularEdad();
                e.setEdad(edad);
                if(edad < 16) throw new RequisitoDeEdadException();
                break;
            } catch(DateTimeParseException x){
                System.out.println("- El formato de la fecha es erroneo, debe ser YYYY-MM-DD en números.");
            } catch(FechaPosteriorException | RequisitoDeEdadException ex){
                System.out.println(ex.getMessage());
            }
        }

        while(true){
            try {
                System.out.println("- Ingrese dni del empleado: ");
                int dni = sc.nextInt();
                if(dni < 10000000) throw new CaracteresException("- El dni debe tener 8 dígitos.");
                e.setDni(dni);
                sc.nextLine();
                break;
            }catch(InputMismatchException x){
                System.out.println(x.getMessage() + "- El dni debe ser numérico.");
                sc.nextLine();
            }catch(CaracteresException x){
                System.out.println(x.getMessage());
            }
        }

        while(true){
            try {
                System.out.println("- Ingrese teléfono del empleado: ");
                String telefono = sc.nextLine();
                e.setTelefono(telefono);
                if(!e.validarTelefono()) throw new TelefonoInvalidoException();
                break;
            }catch(TelefonoInvalidoException x){
                System.out.println("Error: " + x.getMessage());
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
                System.out.println("Error: " + x.getMessage());
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
                if(c.getNombre().matches(".*\\d.*")) throw new CaracteresException("- El nombre no puede contener números.");
                if(c.getNombre().length() < 2) throw new CaracteresException("- El nombre debe tener al menos 2 caracteres.");
                break;
            }catch(CaracteresException ex){
                System.out.println("Error: " + ex.getMessage());
            }
        }

        while(true){
            try{
                System.out.println("- Ingrese apellido del cliente: ");
                c.setApellido(sc.nextLine());
                if(c.getApellido().matches(".*\\d.*")) throw new CaracteresException("- El apellido no puede contener números.");
                if(c.getApellido().length() < 2) throw new CaracteresException("- El apellido debe tener al menos 2 caracteres.");
                break;
            }catch(CaracteresException ex){
                System.out.println("Error: " + ex.getMessage());
            }
        }

        while (true) {
            try{
                System.out.println("- Ingrese fecha de nacimiento del cliente (YYYY-MM-DD) en números: ");
                String fecha = sc.nextLine();
                LocalDate temp = LocalDate.parse(fecha);
                if(temp.isAfter(LocalDate.now())) throw new FechaPosteriorException();
                c.setFechaNacimiento(temp);
                int edad = c.calcularEdad();
                c.setEdad(edad);
                if(edad < 16) throw new RequisitoDeEdadException();
                break;
            } catch(DateTimeParseException x){
                System.out.println("- El formato de la fecha es erroneo, debe ser YYYY-MM-DD en números.");
            } catch(FechaPosteriorException | RequisitoDeEdadException ex){
                System.out.println(ex.getMessage());
            }
        }

        while(true){
            try {
                System.out.println("- Ingrese dni del cliente: ");
                int dni = sc.nextInt();
                if(dni < 10000000) throw new CaracteresException("- El dni debe tener 8 dígitos.");
                c.setDni(dni);
                sc.nextLine();
                break;
            }catch(InputMismatchException x){
                System.out.println(x.getMessage() + "- El dni debe ser numérico.");
                sc.nextLine();
            }catch(CaracteresException x){
                System.out.println(x.getMessage());
            }
        }

        while(true){
            try {
                System.out.println("- Ingrese teléfono del cliente: ");
                String telefono = sc.nextLine();
                c.setTelefono(telefono);
                if(!c.validarTelefono()) throw new TelefonoInvalidoException();
                break;
            }catch(TelefonoInvalidoException x){
                System.out.println("Error: " + x.getMessage());
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
                if(p.getNombre().matches(".*\\d.*")) throw new CaracteresException("- El nombre no puede contener números.");
                if(p.getNombre().length() < 2) throw new CaracteresException("- El nombre debe tener al menos 2 caracteres.");
                break;
            }catch(CaracteresException ex){
                System.out.println("Error: " + ex.getMessage());
            }
        }

        while(true){
            try {
                System.out.println("- Ingrese cuil del proveedor: ");
                long cuil = sc.nextLong();
                if(cuil < 10000000000L) throw new IllegalArgumentException("- El cuil debe tener 11 dígitos.");
                p.setCuil(cuil);
                sc.nextLine();
                break;
            }catch(InputMismatchException | IllegalArgumentException x){
                System.out.println("Error: " + x.getMessage());
            }
        }

        while(true){
            try {
                System.out.println("- Ingrese teléfono del proveedor: ");
                String telefono = sc.nextLine();
                p.setTelefono(telefono);
                if(!p.validarTelefono()) throw new TelefonoInvalidoException();
                break;
            }catch(TelefonoInvalidoException x){
                System.out.println("Error: " + x.getMessage());
            }
        }

        Mudy.listaProveedores.agregar(p.getCuil(), p);
    }

    public static void agregarProducto() throws ElementoRepetidoException{
        Producto p = new Producto();
        while(true) {
            try {
                System.out.println("- Ingrese nombre del producto: ");
                String nombre = sc.nextLine();
                if(nombre.matches(".*\\d.*")) throw new CaracteresException("El nombre no puede contener números.");
                if(nombre.length()<2) throw new CaracteresException("- El nombre debe tener al menos 2 caracteres.");
                p.setNombre(nombre);
                break;
            }catch(CaracteresException x){
                System.out.println("Error: " + x.getMessage());
            }
        }

        while(true) {
            try {
                System.out.println("- Ingrese upc del producto: ");
                long upc = sc.nextLong();
                if(upc < 100000000000L) throw new IllegalArgumentException("- El upc debe tener al menos 12 dígitos.");
                p.setUpc(upc);
                break;
            }catch(IllegalArgumentException | InputMismatchException x){
                System.out.println("- Error: " + x.getMessage());
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
                if(precio<=0) throw new IllegalArgumentException("- Un producto no puede ser gratis.");
                p.setPrecio(precio);
                break;
            }catch(InputMismatchException | IllegalArgumentException e){
                System.out.println("- Error: " + e.getMessage());
            }
        }

        while(true) {
            try {
                System.out.println("- Seleccione el proveedor del producto por su CUIL: ");
                System.out.println(Mudy.listaProveedores.mostrarLista());
                long cuilABuscar =  sc.nextLong();
                Proveedor proveedor = Mudy.listaProveedores.buscarPorId(cuilABuscar);
                if(proveedor==null) throw new  ElementoNoEncontradoException();
                p.setProveedor(proveedor);
                break;
            } catch (InputMismatchException | ElementoNoEncontradoException e) {
                System.out.println("- Error: " + e.getMessage());
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
                if(op == 1) p.setTipoProducto(ETipoProducto.COMESTIBLE);
                if(op == 2) p.setTipoProducto(ETipoProducto.BEBIBLE);
                else throw new IllegalArgumentException("- Valor inválido.");
                break;
            }catch(InputMismatchException | IllegalArgumentException e){
                System.out.println("- Error: " + e.getMessage());
            }
        }

        Mudy.listaProductos.agregar(p.getUpc(),p);
    }



        //Métodos eliminar de la lista.
    public static void eliminar(int opcion) throws ElementoNoEncontradoException{
        switch (opcion){
            case 1:
                eliminarEmpleado();
                System.out.println("- Empleado eliminado correctamente!");
                break;
            case 2:
                eliminarCliente();
                System.out.println("- Cliente eliminado correctamente!");
                break;
            case 3:
                eliminarProveedor();
                System.out.println("- Proveedor eliminado correctamente!");
                break;
            case 4:
                eliminarProducto();
                System.out.println("- Producto eliminado correctamente!");
                break;
            case 5:
                System.out.println("- Pedido eliminado correctamente!");
                break;
            case 6:
                System.out.println("- Marca eliminada correctamente!");
                break;
            case 7:
                System.out.println("- Categoría eliminada correctamente!");
                break;
            default:
                System.out.println("- Opción inválida.");
                break;
        }
    }

    public static void eliminarEmpleado() throws ElementoNoEncontradoException{
        while(true){
            try {
                System.out.println("- Ingrese dni del empleado para eliminarlo: ");
                int dni = sc.nextInt();
                if(dni < 10000000) throw new CaracteresException("- El dni debe tener 8 dígitos.");
                Mudy.listaEmpleados.eliminar((long)dni);
                break;
            }catch(InputMismatchException x){
                System.out.println(x.getMessage() + "- El dni debe ser numérico.");
                sc.nextLine();
            }catch(CaracteresException x){
                System.out.println(x.getMessage());
            }
        }
    }

    public static void eliminarCliente() throws ElementoNoEncontradoException{
        while(true){
            try {
                System.out.println("- Ingrese dni del cliente para eliminarlo: ");
                int dni = sc.nextInt();
                if(dni < 10000000) throw new CaracteresException("- El dni debe tener 8 dígitos.");
                Mudy.listaClientes.eliminar((long)dni);
                break;
            }catch(InputMismatchException x){
                System.out.println(x.getMessage() + "- El dni debe ser numérico.");
                sc.nextLine();
            }catch(CaracteresException x){
                System.out.println(x.getMessage());
            }
        }
    }

    public static void eliminarProveedor() throws ElementoNoEncontradoException{
        while(true){
            try {
                System.out.println("- Ingrese cuil del proveedor para eliminarlo: ");
                long cuil = sc.nextLong();
                if(cuil < 10000000000L) throw new IllegalArgumentException("- El cuil debe tener 11 dígitos.");
                sc.nextLine();
                Mudy.listaProveedores.eliminar(cuil);
                break;
            }catch(InputMismatchException | IllegalArgumentException x){
                System.out.println("Error: " + x.getMessage());
            }
        }
    }

    public static void eliminarProducto() throws ElementoNoEncontradoException{
        while(true) {
            try {
                System.out.println("- Ingrese upc del producto para eliminarlo: ");
                long upc = sc.nextLong();
                if(upc < 100000000000L) throw new IllegalArgumentException("- El upc debe tener al menos 12 dígitos.");
                Mudy.listaProductos.eliminar(upc);
                break;
            }catch(IllegalArgumentException | InputMismatchException x){
                System.out.println("- Error: " + x.getMessage());
            }
        }
    }

        // Método mostrar listas cargadas.
    public static void mostrar(int opcion){
        switch (opcion){
            case 1:
                System.out.println(Mudy.listaEmpleados.mostrarLista());
                break;
            case 2:
                System.out.println(Mudy.listaClientes.mostrarLista());
                break;
            case 3:
                System.out.println(Mudy.listaProveedores.mostrarLista());
                break;
            case 4:
                System.out.println(Mudy.listaProductos.mostrarLista());
                break;
            case 5:
                System.out.println(Mudy.listaPedidos.mostrarLista());
                break;
            case 6:
                System.out.println(Mudy.listaMarcas.mostrar());
                break;
            case 7:
                System.out.println(Mudy.listaCategorias.mostrar());
                break;
            default:
                System.out.println("- Opción inválida.");
                break;
        }
    }

}