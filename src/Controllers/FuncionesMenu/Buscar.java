package Controllers.FuncionesMenu;

import Controllers.Cafeteria;
import Exceptions.ElementoNoEncontradoException;
import Exceptions.ListaNoCargadaException;
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
                System.out.println("- Buscar -");
                Utilidades.mostrarListasModificar();
                opcion = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("- Error: La opcion debe ser numerica");
                sc.nextLine();
            }

            switch (opcion) {
                case 0:
                    control='n';
                    break;
                case 1:
                    if (cafe.listaEmpleados.getMap().isEmpty())
                        throw new ListaNoCargadaException("- No hay empleados para buscar.");
                    Empleado e = buscarEmpleado(cafe);
                    System.out.println(e);
                    break;
                case 2:
                    if (cafe.listaClientes.getMap().isEmpty())
                        throw new ListaNoCargadaException("- No hay clientes para buscar.");
                    Cliente c = buscarCliente(cafe);
                    System.out.println(c);
                    break;
                case 3:
                    if (cafe.listaProveedores.getMap().isEmpty())
                        throw new ListaNoCargadaException("- No hay proveedores para buscar.");
                    Proveedor p = buscarProveedor(cafe);
                    System.out.println(p);
                    break;
                case 4:
                    if (cafe.listaProductos.getMap().isEmpty())
                        throw new ListaNoCargadaException("- No hay productos para buscar.");
                    Producto pr = buscarProducto(cafe);
                    System.out.println(pr);
                    break;
                case 5:
                    if (cafe.listaPedidos.getMap().isEmpty())
                        throw new ListaNoCargadaException("- No hay pedidos para buscar.");
                    Pedido pe = buscarPedido(cafe);
                    System.out.println(pe);
                    break;
                case 6:
                    if (cafe.listaMarcas.getMap().isEmpty())
                        throw new ListaNoCargadaException("- No hay marcas para buscar. ");
                    String marca = buscarMarca(cafe);
                    System.out.println("- La marca " + marca + " se encuntra en la lista de marcas.");
                    break;
                case 7:
                    if (cafe.listaCategorias.getMap().isEmpty())
                        throw new ListaNoCargadaException("- No hay categorías para buscar.");
                    String categoria = buscarCategoria(cafe);
                    System.out.println("- La categoría " + categoria + " se encuntra en la lista de categorias.");
                    break;
                default:
                    System.out.println("- Opción inválida.");
                    break;
            }
            try{
                if(control=='s') {
                    control = Utilidades.continuar("buscando");
                }
            }catch(IllegalArgumentException e){
                System.out.println(e.getMessage());
                sc.nextLine();
            }
        }
    }

    public static Empleado buscarEmpleado(Cafeteria cafe) throws ElementoNoEncontradoException{
        while(true){
            try {
                System.out.println("- Ingrese dni del empleado para buscarlo: ");
                int dni = sc.nextInt();
                if(dni < 10000000) throw new IllegalArgumentException("El dni debe tener 8 dígitos.");
                return cafe.listaEmpleados.buscarPorId((long)dni);
            }catch(InputMismatchException x){
                System.out.println("- Error: El dni debe ser numérico.");
                sc.nextLine();
            }catch(IllegalArgumentException x){
                System.out.println("Error: " + x.getMessage());
            }
        }
    }

    public static Cliente buscarCliente(Cafeteria cafe) throws ElementoNoEncontradoException{
        while(true){
            try {
                System.out.println("- Ingrese dni del cliente para buscarlo: ");
                int dni = sc.nextInt();
                if(dni < 10000000) throw new IllegalArgumentException("El dni debe tener 8 dígitos.");
                return cafe.listaClientes.buscarPorId((long)dni);
            }catch(InputMismatchException x){
                System.out.println("- Error: El dni debe ser numérico.");
                sc.nextLine();
            }catch(IllegalArgumentException x){
                System.out.println("- Error:" + x.getMessage());
            }
        }
    }

    public static Proveedor buscarProveedor(Cafeteria cafe) throws ElementoNoEncontradoException{
        while(true){
            try {
                System.out.println("- Ingrese cuil del proveedor para buscarlo: ");
                long cuil = sc.nextLong();
                if(cuil < 10000000000L) throw new IllegalArgumentException("El cuil debe tener 11 dígitos.");
                sc.nextLine();
                return cafe.listaProveedores.buscarPorId(cuil);
            }catch(InputMismatchException x){
                System.out.println("- Error: el cuil debe ser numerico");
                sc.nextLine();
            }catch(IllegalArgumentException x){
                System.out.println("- Error:" + x.getMessage());
            }
        }
    }

    public static Producto buscarProducto(Cafeteria cafe) throws ElementoNoEncontradoException{
        while(true) {
            try {
                System.out.println("- Ingrese upc del producto para buscarlo: ");
                long upc = sc.nextLong();
                if(upc < 100000000000L) throw new IllegalArgumentException("El upc debe tener al menos 12 dígitos.");
                return cafe.listaProductos.buscarPorId(upc);
            }catch(InputMismatchException x){
                System.out.println("- Error: el upc debe ser numerico");
                sc.nextLine();
            }catch(IllegalArgumentException x){
                System.out.println("- Error: " + x.getMessage());
            }
        }
    }

    public static Pedido buscarPedido(Cafeteria cafe) throws ElementoNoEncontradoException{
        while(true) {
            try {
                System.out.println("- Ingrese id del pedido para buscarlo: ");
                int id = sc.nextInt();
                if(id <= 0) throw new IllegalArgumentException("No hay pedidos con ID menor o igual a 0");
                return cafe.listaPedidos.buscarPorId((long)id);
            }catch(InputMismatchException x){
                System.out.println("- Error: el id del pedido debe ser numérico");
                sc.nextLine();
            }catch(IllegalArgumentException x){
                System.out.println("- Error: " + x.getMessage());
            }
        }
    }

    public static String buscarMarca(Cafeteria cafe) throws ElementoNoEncontradoException{
        while(true) {
            try {
                System.out.println("- Ingrese nombre de la marca para buscarla: ");
                String marca = sc.nextLine();
                if(marca.length() < 2) throw new IllegalArgumentException("El nombre debe tener al menos 2 caracteres.");
                if(cafe.listaMarcas.buscar(marca)) return marca;
            }catch(IllegalArgumentException ex){
                System.out.println("- Error: " + ex.getMessage());
            }
        }
    }

    public static String buscarCategoria(Cafeteria cafe) throws ElementoNoEncontradoException{
        while(true) {
            try {
                System.out.println("- Ingrese nombre de la categoría para buscarla: ");
                String categoria = sc.nextLine();
                Utilidades.validarString(categoria);
                if(cafe.listaCategorias.buscar(categoria)) return categoria;
            }catch(IllegalArgumentException ex){
                System.out.println("- Error: " + ex.getMessage());
            }
        }
    }
}
