package Controllers.FuncionesMenu;

import Controllers.Cafeteria;
import Exceptions.ElementoNoEncontradoException;
import Exceptions.ListaNoCargadaException;
import Exceptions.SalirDelIngresoDeDatosException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Eliminar {
    static Scanner sc = new Scanner(System.in);
    public static void eliminar(Cafeteria cafe) throws ElementoNoEncontradoException, ListaNoCargadaException {
        char control = 's';
        int opcion = 0;
        while (control == 's') {
            try{
                System.out.println("-----------------");
                System.out.println("- Eliminar -");
                Utilidades.mostrarListas();
                opcion = sc.nextInt();
                sc.nextLine();
                System.out.println("-----------------");
            }catch(InputMismatchException e){
                System.out.println("- Error: la opción debe ser numérica.");
                sc.nextLine();
            }
            switch (opcion) {
                case 0:
                    control = 'n';
                    break;
                case 1:
                    if (cafe.listaEmpleados.getMap().isEmpty()) throw new ListaNoCargadaException("- No hay empleados para eliminar.");
                    try {
                        eliminarEmpleado(cafe);
                        System.out.println("- Empleado eliminado correctamente!");
                    } catch (SalirDelIngresoDeDatosException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    if (cafe.listaClientes.getMap().isEmpty()) throw new ListaNoCargadaException("- No hay clientes para eliminar.");
                    try {
                        eliminarCliente(cafe);
                        System.out.println("- Cliente eliminado correctamente!");
                    } catch (SalirDelIngresoDeDatosException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    if (cafe.listaProveedores.getMap().isEmpty()) throw new ListaNoCargadaException("- No hay proveedores para eliminar.");
                    try {
                        eliminarProveedor(cafe);
                        System.out.println("- Proveedor eliminado correctamente!");
                    } catch (SalirDelIngresoDeDatosException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    if (cafe.listaProductos.getMap().isEmpty()) throw new ListaNoCargadaException("- No hay productos para eliminar.");
                    try {
                        eliminarProducto(cafe);
                        System.out.println("- Producto eliminado correctamente!");
                    } catch (SalirDelIngresoDeDatosException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 5:
                    if (cafe.listaPedidos.getMap().isEmpty()) throw new ListaNoCargadaException("- No hay pedidos para eliminar.");
                    try {
                        eliminarPedido(cafe);
                        System.out.println("- Pedido eliminado correctamente!");
                    } catch (SalirDelIngresoDeDatosException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 6:
                    if (cafe.listaMarcas.getMap().isEmpty()) throw new ListaNoCargadaException("- No hay marcas para eliminar.");
                    try {
                        eliminarMarca(cafe);
                        System.out.println("- Marca eliminada correctamente!");
                    } catch (SalirDelIngresoDeDatosException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 7:
                    if (cafe.listaCategorias.getMap().isEmpty()) throw new ListaNoCargadaException("- No hay categorías para eliminar.");
                    try {
                        eliminarCategoria(cafe);
                        System.out.println("- Categoría eliminada correctamente!");
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
                    control = Utilidades.continuar("eliminando");
                }
            }catch(IllegalArgumentException e){
                System.out.println(e.getMessage());
                sc.nextLine();
            }
        }
    }
    //Métodos eliminar de la lista.
    public static void eliminarEmpleado(Cafeteria cafe) throws ElementoNoEncontradoException, SalirDelIngresoDeDatosException {
        while(true){
            try {
                System.out.println("- Ingrese DNI del empleado para eliminarlo (ingrese 0 para salir): ");
                int dni = sc.nextInt();
                if(dni == 0){
                    sc.nextLine();
                    throw new SalirDelIngresoDeDatosException();
                }
                if(dni < 10000000) throw new IllegalArgumentException("El DNI debe tener 8 dígitos.");
                cafe.listaEmpleados.eliminar((long)dni);
                break;
            }catch(InputMismatchException x){
                System.out.println("- Error: El DNI debe ser numérico.");
                sc.nextLine();
            }catch(IllegalArgumentException x){
                System.out.println("- Error: " + x.getMessage());
            }
        }
    }

    public static void eliminarCliente(Cafeteria cafe) throws ElementoNoEncontradoException, SalirDelIngresoDeDatosException {
        while(true){
            try {
                System.out.println("- Ingrese DNI del cliente para eliminarlo (ingrese 0 para salir): ");
                int dni = sc.nextInt();
                if(dni == 0){
                    sc.nextLine();
                    throw new SalirDelIngresoDeDatosException();
                }
                if(dni < 10000000) throw new IllegalArgumentException("El DNI debe tener 8 dígitos.");
                cafe.listaClientes.eliminar((long)dni);
                break;
            }catch(InputMismatchException x){
                System.out.println("- Error: El DNI debe ser numérico.");
                sc.nextLine();
            }catch(IllegalArgumentException x){
                System.out.println("- Error: " + x.getMessage());
            }
        }
    }

    public static void eliminarProveedor(Cafeteria cafe) throws ElementoNoEncontradoException, SalirDelIngresoDeDatosException {
        while(true){
            try {
                System.out.println("- Ingrese CUIL del proveedor para eliminarlo (ingrese 0 para salir): ");
                long cuil = sc.nextLong();
                if(cuil == 0){
                    sc.nextLine();
                    throw new SalirDelIngresoDeDatosException();
                }
                if(cuil < 10000000000L) throw new IllegalArgumentException("El CUIL debe tener 11 dígitos.");
                sc.nextLine();
                cafe.listaProveedores.eliminar(cuil);
                break;
            }catch(InputMismatchException x){
                System.out.println("- Error: el CUIL debe ser numérico.");
            }catch(IllegalArgumentException x){
                System.out.println("- Error: " + x.getMessage());
            }
        }
    }

    public static void eliminarPedido(Cafeteria cafe) throws ElementoNoEncontradoException, SalirDelIngresoDeDatosException {
        while(true){
            try {
                System.out.println("- Ingrese ID del pedido para eliminarlo (ingrese 0 para salir): ");
                long id = sc.nextLong();
                if(id == 0){
                    sc.nextLine();
                    throw new SalirDelIngresoDeDatosException();
                }
                cafe.listaPedidos.eliminar(id);
                break;
            }catch(InputMismatchException x){
                System.out.println("- Error: el ID debe ser numérico.");
            }
        }
    }

    public static void eliminarProducto(Cafeteria cafe) throws ElementoNoEncontradoException, SalirDelIngresoDeDatosException {
        while(true) {
            try {
                System.out.println("- Ingrese UPC del producto para eliminarlo (ingrese 0 para salir): ");
                long upc = sc.nextLong();
                if(upc == 0){
                    sc.nextLine();
                    throw new SalirDelIngresoDeDatosException();
                }
                if(upc < 100000000000L) throw new IllegalArgumentException("El UPC debe tener al menos 12 dígitos.");
                cafe.listaProductos.eliminar(upc);
                break;
            }catch(InputMismatchException x){
                System.out.println("- Error: el UPC debe ser numérico. ");
                sc.nextLine();
            }catch(IllegalArgumentException x){
                System.out.println("- Error: " + x.getMessage());
            }
        }
    }

    public static void eliminarMarca(Cafeteria cafe) throws ElementoNoEncontradoException, SalirDelIngresoDeDatosException {
        while(true) {
            try {
                System.out.println("- Ingrese nombre de la marca para eliminarla (ingrese 0 para salir): ");
                String marca = sc.nextLine();
                if(marca.equals("0")) throw new SalirDelIngresoDeDatosException();
                if(marca.length() < 2) throw new IllegalArgumentException("El nombre de la marca debe tener al menos 2 caracteres.");
                cafe.listaMarcas.eliminar(marca);
                break;
            }catch(IllegalArgumentException ex){
                System.out.println("- Error: " + ex.getMessage());
            }
        }
    }

    public static void eliminarCategoria(Cafeteria cafe) throws ElementoNoEncontradoException, SalirDelIngresoDeDatosException {
        while(true) {
            try {
                System.out.println("- Ingrese nombre de la categoría para eliminarla (ingrese 0 para salir): ");
                String categoria = sc.nextLine();
                if(categoria.equals("0")) throw new SalirDelIngresoDeDatosException();
                Utilidades.validarString(categoria);
                cafe.listaCategorias.eliminar(categoria);
                break;
            }catch(IllegalArgumentException ex){
                System.out.println("- Error: " + ex.getMessage());
            }
        }
    }
}
