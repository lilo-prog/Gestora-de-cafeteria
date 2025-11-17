package Controllers.FuncionesMenu;

import Controllers.Cafeteria;
import Exceptions.ElementoNoEncontradoException;
import Exceptions.ListaNoCargadaException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Eliminar {

    static Scanner sc = new Scanner(System.in);
    public static void eliminar(int opcion, Cafeteria cafe) throws ElementoNoEncontradoException, ListaNoCargadaException {
        char control='s';
        while (control == 's') {
            switch (opcion) {
                case 0:
                    control='n';
                    break;
                case 1:
                    if (cafe.listaEmpleados.getMap().isEmpty())
                        throw new ListaNoCargadaException("No hay empleados para eliminar");
                    eliminarEmpleado(cafe);
                    System.out.println("- Empleado eliminado correctamente!");
                    break;
                case 2:
                    if (cafe.listaClientes.getMap().isEmpty())
                        throw new ListaNoCargadaException("No hay clientes para eliminar");
                    eliminarCliente(cafe);
                    System.out.println("- Cliente eliminado correctamente!");
                    break;
                case 3:
                    if (cafe.listaProveedores.getMap().isEmpty())
                        throw new ListaNoCargadaException("No hay proveedores para eliminar");
                    eliminarProveedor(cafe);
                    System.out.println("- Proveedor eliminado correctamente!");
                    break;
                case 4:
                    if (cafe.listaProductos.getMap().isEmpty())
                        throw new ListaNoCargadaException("No hay productos para eliminar");
                    eliminarProducto(cafe);
                    System.out.println("- Producto eliminado correctamente!");
                    break;
                case 5:
                    if (cafe.listaPedidos.getMap().isEmpty())
                        throw new ListaNoCargadaException("No hay pedidos para eliminar");
                    eliminarPedido(cafe);
                    System.out.println("- Pedido eliminado correctamente!");
                    break;
                case 6:
                    if (cafe.listaMarcas.getMap().isEmpty())
                        throw new ListaNoCargadaException("No hay marcas para eliminar");
                    eliminarMarca(cafe);
                    System.out.println("- Marca eliminada correctamente!");
                    break;
                case 7:
                    if (cafe.listaCategorias.getMap().isEmpty())
                        throw new ListaNoCargadaException("No hay categorias para eliminar");
                    eliminarCategoria(cafe);
                    System.out.println("- Categoría eliminada correctamente!");
                    break;
                default:
                    System.out.println("- Opción inválida.");
                    break;
            }
            try{
                if(control=='s') {
                    control = Utilidades.continuar("eliminando");
                }
            }catch(IllegalArgumentException e){
                System.out.println(e.getMessage());
                sc.nextLine();
            }
        }
    }
    //Métodos eliminar de la lista.
    public static void eliminarEmpleado(Cafeteria cafe) throws ElementoNoEncontradoException{
        while(true){
            try {
                System.out.println("- Ingrese dni del empleado para eliminarlo: ");
                int dni = sc.nextInt();
                if(dni < 10000000) throw new IllegalArgumentException("El dni debe tener 8 dígitos.");
                cafe.listaEmpleados.eliminar((long)dni);
                break;
            }catch(InputMismatchException x){
                System.out.println("- Error: El dni debe ser numérico.");
                sc.nextLine();
            }catch(IllegalArgumentException x){
                System.out.println("- Error: " + x.getMessage());
            }
        }
    }

    public static void eliminarCliente(Cafeteria cafe) throws ElementoNoEncontradoException{
        while(true){
            try {
                System.out.println("- Ingrese dni del cliente para eliminarlo: ");
                int dni = sc.nextInt();
                if(dni < 10000000) throw new IllegalArgumentException("El dni debe tener 8 dígitos.");
                cafe.listaClientes.eliminar((long)dni);
                break;
            }catch(InputMismatchException x){
                System.out.println("- Error: El dni debe ser numérico.");
                sc.nextLine();
            }catch(IllegalArgumentException x){
                System.out.println("- Error: " + x.getMessage());
            }
        }
    }

    public static void eliminarProveedor(Cafeteria cafe) throws ElementoNoEncontradoException{
        while(true){
            try {
                System.out.println("- Ingrese cuil del proveedor para eliminarlo: ");
                long cuil = sc.nextLong();
                if(cuil < 10000000000L) throw new IllegalArgumentException("El cuil debe tener 11 dígitos.");
                sc.nextLine();
                cafe.listaProveedores.eliminar(cuil);
                break;
            }catch(InputMismatchException x){
                System.out.println("- Error: el cuil debe ser numerico");
            }catch(IllegalArgumentException x){
                System.out.println("- Error: " + x.getMessage());
            }
        }
    }
    public static void eliminarPedido(Cafeteria cafe) throws ElementoNoEncontradoException{
        while(true){
            try {
                System.out.println("- Ingrese ID del pedido para eliminarlo: ");
                long id = sc.nextLong();
                sc.nextLine();
                cafe.listaPedidos.eliminar(id);
                break;
            }catch(InputMismatchException x){
                System.out.println("- Error: el ID debe ser numerico");
            }
        }
    }

    public static void eliminarProducto(Cafeteria cafe) throws ElementoNoEncontradoException{
        while(true) {
            try {
                System.out.println("- Ingrese upc del producto para eliminarlo: ");
                long upc = sc.nextLong();
                if(upc < 100000000000L) throw new IllegalArgumentException("El upc debe tener al menos 12 dígitos.");
                cafe.listaProductos.eliminar(upc);
                break;
            }catch(InputMismatchException x){
                System.out.println("- Error: el upc debe ser numerico ");
                sc.nextLine();
            }catch(IllegalArgumentException x){
                System.out.println("- Error: " + x.getMessage());
            }
        }
    }

    public static void eliminarMarca(Cafeteria cafe) throws ElementoNoEncontradoException{
        while(true) {
            try {
                System.out.println("- Ingrese nombre de la marca para eliminarla: ");
                String marca = sc.nextLine();
                if(marca.length() < 2) throw new IllegalArgumentException("El nombre debe tener al menos 2 caracteres.");
                cafe.listaMarcas.eliminar(marca);
                break;
            }catch(IllegalArgumentException ex){
                System.out.println("- Error: " + ex.getMessage());
            }
        }
    }

    public static void eliminarCategoria(Cafeteria cafe) throws ElementoNoEncontradoException{
        while(true) {
            try {
                System.out.println("- Ingrese nombre de la categoría para eliminarla: ");
                String categoria = sc.nextLine();
                if(categoria.length() < 2) throw new IllegalArgumentException("El nombre debe tener al menos 2 caracteres.");
                cafe.listaCategorias.eliminar(categoria);
                break;
            }catch(IllegalArgumentException ex){
                System.out.println("- Error: " + ex.getMessage());
            }
        }
    }
}
