package Controllers.FuncionesMenu;

import Controllers.Cafeteria;
import Exceptions.ElementoNoEncontradoException;
import Exceptions.ListaNoCargadaException;
import Exceptions.SalirDelIngresoDeDatosException;
import Models.Pedidos.Pedido;
import Models.Personas.Cliente;
import Models.Personas.Empleado;
import Models.Productos.Producto;
import Models.Proveedores.Proveedor;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Buscar {
    static Scanner sc = new Scanner(System.in);
    //  Métodos buscar en las listas.
    public static void buscar(Cafeteria cafe) throws ElementoNoEncontradoException, ListaNoCargadaException {
        char control = 's';
        int opcion = 0;
        while(control == 's') {
            try {
                System.out.println("-----------------");
                System.out.println("- Buscar -");
                Utilidades.mostrarListas();
                opcion = sc.nextInt();
                sc.nextLine();
                System.out.println("-----------------");
            } catch (InputMismatchException e) {
                System.out.println("- Error: La opción debe ser numérica.");
                sc.nextLine();
            }
            switch (opcion) {
                case 0:
                    control = 'n';
                    break;
                case 1:
                    if (cafe.listaEmpleados.getMap().isEmpty()) throw new ListaNoCargadaException("- No hay empleados para buscar.");
                    try {
                        Empleado e = buscarEmpleado(cafe);
                        System.out.println(e);
                    } catch (SalirDelIngresoDeDatosException ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 2:
                    if (cafe.listaClientes.getMap().isEmpty()) throw new ListaNoCargadaException("- No hay clientes para buscar.");
                    try {
                        Cliente c = buscarCliente(cafe);
                        System.out.println(c);
                    } catch (SalirDelIngresoDeDatosException ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 3:
                    if (cafe.listaProveedores.getMap().isEmpty()) throw new ListaNoCargadaException("- No hay proveedores para buscar.");
                    try {
                        Proveedor p = buscarProveedor(cafe);
                        System.out.println(p);
                    } catch (SalirDelIngresoDeDatosException ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 4:
                    if (cafe.listaProductos.getMap().isEmpty()) throw new ListaNoCargadaException("- No hay productos para buscar.");
                    try {
                        Producto pr = buscarProducto(cafe);
                        System.out.println(pr);
                    } catch (SalirDelIngresoDeDatosException ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 5:
                    if (cafe.listaPedidos.getMap().isEmpty()) throw new ListaNoCargadaException("- No hay pedidos para buscar.");
                    try {
                        Pedido pe = buscarPedido(cafe);
                        System.out.println(pe);
                    } catch (SalirDelIngresoDeDatosException ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 6:
                    if (cafe.listaMarcas.getMap().isEmpty()) throw new ListaNoCargadaException("- No hay marcas para buscar. ");
                    try {
                        String marca = buscarMarca(cafe);
                        System.out.println("- La marca " + marca + " se encuntra en la lista de marcas.");
                    } catch (SalirDelIngresoDeDatosException ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 7:
                    if (cafe.listaCategorias.getMap().isEmpty()) throw new ListaNoCargadaException("- No hay categorías para buscar.");
                    try {
                        String categoria = buscarCategoria(cafe);
                        System.out.println("- La categoría " + categoria + " se encuntra en la lista de categorias.");

                    } catch (SalirDelIngresoDeDatosException ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                default:
                    System.out.println("- Opción inválida.");
                    break;
            }
            try{
                if(control == 's') {
                    control = Utilidades.continuar("buscando");
                }
            }catch(IllegalArgumentException e){
                System.out.println(e.getMessage());
                sc.nextLine();
            }
        }
    }

    public static Empleado buscarEmpleado(Cafeteria cafe) throws ElementoNoEncontradoException, SalirDelIngresoDeDatosException {
        while(true){
            try {
                System.out.println("- Ingrese DNI del empleado para buscarlo (ingrese 0 para salir): ");
                int dni = sc.nextInt();
                if(dni == 0) throw new SalirDelIngresoDeDatosException();
                if(dni < 10000000) throw new IllegalArgumentException("El DNI debe tener 8 dígitos.");
                return cafe.listaEmpleados.buscarPorId((long)dni);
            }catch(InputMismatchException x){
                System.out.println("- Error: El DNI debe ser numérico.");
                sc.nextLine();
            }catch(IllegalArgumentException x){
                System.out.println("- Error: " + x.getMessage());
            }
        }
    }

    public static Cliente buscarCliente(Cafeteria cafe) throws ElementoNoEncontradoException, SalirDelIngresoDeDatosException{
        while(true){
            try {
                System.out.println("- Ingrese DNI del cliente para buscarlo (ingrese 0 para salir): ");
                int dni = sc.nextInt();
                if(dni == 0) throw new SalirDelIngresoDeDatosException();
                if(dni < 10000000) throw new IllegalArgumentException("El DNI debe tener 8 dígitos.");
                return cafe.listaClientes.buscarPorId((long)dni);
            }catch(InputMismatchException x){
                System.out.println("- Error: El DNI debe ser numérico.");
                sc.nextLine();
            }catch(IllegalArgumentException x){
                System.out.println("- Error: " + x.getMessage());
            }
        }
    }

    public static Proveedor buscarProveedor(Cafeteria cafe) throws ElementoNoEncontradoException, SalirDelIngresoDeDatosException{
        while(true){
            try {
                System.out.println("- Ingrese CUIL del proveedor para buscarlo (ingrese 0 para salir): ");
                long cuil = sc.nextLong();
                if(cuil == 0) throw new SalirDelIngresoDeDatosException();
                if(cuil < 10000000000L) throw new IllegalArgumentException("El CUIL debe tener 11 dígitos.");
                sc.nextLine();
                return cafe.listaProveedores.buscarPorId(cuil);
            }catch(InputMismatchException x){
                System.out.println("- Error: el CUIL debe ser numérico");
                sc.nextLine();
            }catch(IllegalArgumentException x){
                System.out.println("- Error: " + x.getMessage());
            }
        }
    }

    public static Producto buscarProducto(Cafeteria cafe) throws ElementoNoEncontradoException, SalirDelIngresoDeDatosException{
        while(true) {
            try {
                System.out.println("- Ingrese UPC del producto para buscarlo (ingrese 0 para salir): ");
                long upc = sc.nextLong();
                if(upc == 0) throw new SalirDelIngresoDeDatosException();
                if(upc < 100000000000L) throw new IllegalArgumentException("El UPC debe tener al menos 12 dígitos.");
                return cafe.listaProductos.buscarPorId(upc);
            }catch(InputMismatchException x){
                System.out.println("- Error: el UPC debe ser numérico");
                sc.nextLine();
            }catch(IllegalArgumentException x){
                System.out.println("- Error: " + x.getMessage());
            }
        }
    }

    public static Pedido buscarPedido(Cafeteria cafe) throws ElementoNoEncontradoException, SalirDelIngresoDeDatosException{
        while(true) {
            try {
                System.out.println("- Ingrese ID del pedido para buscarlo (ingrese 0 para salir): ");
                int id = sc.nextInt();
                if(id == 0) throw new SalirDelIngresoDeDatosException();
                if(id < 0) throw new IllegalArgumentException("No hay pedidos con ID menor a 0");
                return cafe.listaPedidos.buscarPorId((long)id);
            }catch(InputMismatchException x){
                System.out.println("- Error: el ID del pedido debe ser numérico");
                sc.nextLine();
            }catch(IllegalArgumentException x){
                System.out.println("- Error: " + x.getMessage());
            }
        }
    }

    public static String buscarMarca(Cafeteria cafe) throws ElementoNoEncontradoException, SalirDelIngresoDeDatosException{
        while(true) {
            try {
                System.out.println("- Ingrese nombre de la marca para buscarla (ingrese 0 para salir): ");
                String marca = sc.nextLine();
                if(marca.equals("0")) throw new SalirDelIngresoDeDatosException();
                if(marca.length() < 2) throw new IllegalArgumentException("El nombre debe tener al menos 2 caracteres.");
                if(cafe.listaMarcas.buscar(marca)) return marca;
            }catch(IllegalArgumentException ex){
                System.out.println("- Error: " + ex.getMessage());
            }
        }
    }

    public static String buscarCategoria(Cafeteria cafe) throws ElementoNoEncontradoException, SalirDelIngresoDeDatosException{
        while(true) {
            try {
                System.out.println("- Ingrese nombre de la categoría para buscarla (ingrese 0 para salir): ");
                String categoria = sc.nextLine();
                if(categoria.equals("0")) throw new SalirDelIngresoDeDatosException();
                Utilidades.validarString(categoria);
                if(cafe.listaCategorias.buscar(categoria)) return categoria;
            }catch(IllegalArgumentException ex){
                System.out.println("- Error: " + ex.getMessage());
            }
        }
    }

}
