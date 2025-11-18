package Controllers.FuncionesMenu;
import Controllers.Cafeteria;
import Exceptions.ListaNoCargadaException;
import Models.Pedidos.Pedido;

import java.util.Scanner;

public class Mostrar {
    static Scanner sc = new Scanner(System.in);
    // Métodos mostrar listas cargadas.
    public static void mostrar(int opcion , Cafeteria cafe) throws ListaNoCargadaException {
        char control = 's';
        while(control == 's') {
            switch (opcion) {
                case 0:
                    control = 'n';
                    break;
                case 1:
                    if (cafe.listaEmpleados.getMap().isEmpty())
                        throw new ListaNoCargadaException("No hay empleados para mostrar.");
                    System.out.println(cafe.listaEmpleados.mostrarLista());
                    break;
                case 2:
                    if (cafe.listaClientes.getMap().isEmpty())
                        throw new ListaNoCargadaException("No hay clientes para mostrar.");
                    System.out.println(cafe.listaClientes.mostrarLista());
                    break;
                case 3:
                    if (cafe.listaProveedores.getMap().isEmpty())
                        throw new ListaNoCargadaException("No hay proveedores para mostrar.");
                    System.out.println(cafe.listaProveedores.mostrarLista());
                    break;
                case 4:
                    if (cafe.listaProductos.getMap().isEmpty())
                        throw new ListaNoCargadaException("No hay productos para mostrar.");
                    System.out.println(cafe.listaProductos.mostrarLista());
                    break;
                case 5:
                    if (cafe.listaPedidos.getMap().isEmpty())
                        throw new ListaNoCargadaException("No hay pedidos para mostrar.");
                    System.out.println(cafe.listaPedidos.mostrarLista());
                    break;
                case 6:
                    if (cafe.listaMarcas.getMap().isEmpty())
                        throw new ListaNoCargadaException("No hay marcas para mostrar.");
                    System.out.println(cafe.listaMarcas.mostrar());
                    break;
                case 7:
                    if (cafe.listaCategorias.getMap().isEmpty())
                        throw new ListaNoCargadaException("No hay categorías para mostrar.");
                    System.out.println(cafe.listaCategorias.mostrar());
                    break;
                case 8:
                    System.out.println("- El gasto mínimo para aplicar descuentos es: " + Pedido.gastoMinimo + ".");
                    break;
                case 9:
                    System.out.println("- El descuento a aplicar es: " + Pedido.descuentoAAplicar + ".");
                    break;
                default:
                    System.out.println("- Opción inválida.");
                    break;
            }
            try{
                if(control=='s') {
                    control = Utilidades.continuar("mostrando");
                }
            }catch(IllegalArgumentException e){
                System.out.println(e.getMessage());
                sc.nextLine();
            }
        }
    }
}
