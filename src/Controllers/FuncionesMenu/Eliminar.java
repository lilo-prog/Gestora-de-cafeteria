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
                if(control=='s') {
                    control = Utilidades.continuar("eliminando");
                }
        }
    }
    //Métodos eliminar de la lista.
    public static void eliminarEmpleado(Cafeteria cafe) throws ElementoNoEncontradoException, SalirDelIngresoDeDatosException {
        Empleado e = Buscar.buscarEmpleado(cafe);
        cafe.listaEmpleados.eliminar(e.getDni());
    }

    public static void eliminarCliente(Cafeteria cafe) throws ElementoNoEncontradoException, SalirDelIngresoDeDatosException {
        Cliente c = Buscar.buscarCliente(cafe);
        cafe.listaClientes.eliminar(c.getDni());
    }

    public static void eliminarProveedor(Cafeteria cafe) throws ElementoNoEncontradoException, SalirDelIngresoDeDatosException {
        Proveedor p = Buscar.buscarProveedor(cafe);
        cafe.listaProveedores.eliminar(p.getCuil());
    }

    public static void eliminarPedido(Cafeteria cafe) throws ElementoNoEncontradoException, SalirDelIngresoDeDatosException {
        Pedido p =  Buscar.buscarPedido(cafe);
        cafe.listaPedidos.eliminar(String.valueOf(p.getId()));
    }

    public static void eliminarProducto(Cafeteria cafe) throws ElementoNoEncontradoException, SalirDelIngresoDeDatosException {
        Producto p = Buscar.buscarProducto(cafe);
        cafe.listaProductos.eliminar(p.getUpc());
    }

    public static void eliminarMarca(Cafeteria cafe) throws ElementoNoEncontradoException, SalirDelIngresoDeDatosException {
        String marca = Buscar.buscarMarca(cafe);
        cafe.listaMarcas.eliminar(marca);
    }

    public static void eliminarCategoria(Cafeteria cafe) throws ElementoNoEncontradoException, SalirDelIngresoDeDatosException {
        String categoria = Buscar.buscarCategoria(cafe);
        cafe.listaCategorias.eliminar(categoria);
    }
}
