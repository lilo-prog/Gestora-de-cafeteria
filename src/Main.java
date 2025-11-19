import Controllers.*;
import Controllers.FuncionesMenu.*;
import Exceptions.*;
import JSON.JsonUtiles;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Cafeteria Mudy = new Cafeteria();
        Scanner sc = new Scanner(System.in);
        // Trae los datos de los JSON,si es que existen.
        JSONTokener tok1 = JsonUtiles.leerUnJson("Empleado.json");
        JSONTokener tok2 = JsonUtiles.leerUnJson("Cliente.json");
        JSONTokener tok3 = JsonUtiles.leerUnJson("Proveedor.json");
        JSONTokener tok4 = JsonUtiles.leerUnJson("Producto.json");
        JSONTokener tok5 = JsonUtiles.leerUnJson("Pedido.json");
        JSONTokener tok6 = JsonUtiles.leerUnJson("marcas.json");
        JSONTokener tok7 = JsonUtiles.leerUnJson("categorias.json");
        try {
            System.out.println("- Cargando datos...");
            Thread.sleep(500);
            System.out.println(".");
            Thread.sleep(500);
            System.out.println("..");
            Thread.sleep(500);
            System.out.println("...");
            Thread.sleep(500);
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
            System.out.print("- Error: Hubo un error en la carga de datos.");
        } catch (InterruptedException | ElementoRepetidoException e) {
            System.out.println("- Error: " + e.getMessage());
        }
        int opcion;
        char control = 's';
        boolean flagInvalido = false;
        while(control == 's') {
                while(true) {
                    System.out.println("-----------------------------------");
                    menuPrincipal();
                    try {
                        opcion = sc.nextInt();
                        sc.nextLine();
                        System.out.println("-----------------------------------");
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("- Error: La opción debe ser numérica.");
                        sc.nextLine();
                    }
                }
                switch (opcion) {
                    case 0:
                        control = 'n';
                        break;
                    case 1:
                        flagInvalido = false;
                        while (true) {
                            try {
                                Agregar.agregar(Mudy);
                                break;
                            } catch (ElementoRepetidoException | ListaNoCargadaException | IllegalArgumentException | ElementoNoEncontradoException e) {
                                System.out.println("- Error: " + e.getMessage());
                            }
                        }
                        break;
                    case 2:
                        flagInvalido = false;
                        while(true){
                            try {
                                Eliminar.eliminar(Mudy);
                                break;
                            } catch (ElementoNoEncontradoException | ListaNoCargadaException | IllegalArgumentException e) {
                                System.out.println("- Error: " + e.getMessage());
                            }
                        }
                        break;
                    case 3:
                        flagInvalido = false;
                        while(true) {
                            try {
                                Buscar.buscar(Mudy);
                                break;
                            } catch (ElementoNoEncontradoException | ListaNoCargadaException | IllegalArgumentException e) {
                                System.out.println("- Error: " + e.getMessage());
                            }
                        }
                        break;
                    case 4:
                        flagInvalido = false;
                        while(true) {
                            try {
                                Modificar.modificar(Mudy);
                                break;
                            } catch (ElementoNoEncontradoException | ListaNoCargadaException | IllegalArgumentException e) {
                                System.out.println("- Error: " + e.getMessage());
                            } catch (InputMismatchException e) {
                                System.out.println("- Error: La opción debe ser numérica.");
                                sc.nextLine();
                            }
                        }
                        break;
                    case 5:
                        flagInvalido = false;
                        while(true) {
                            try {
                                Mostrar.mostrar(Mudy);
                                break;
                            } catch (ListaNoCargadaException | IllegalArgumentException e) {
                                System.out.println("- Error: " + e.getMessage());
                            }
                        }
                        break;
                    default:
                        System.out.println("- Error: Opción inválida.");
                        flagInvalido = true;
                        break;
                    }
                    if (control == 's' && !flagInvalido) {
                        control = Utilidades.continuar("en el menú principal");
                    }
        }
        sc.close();
        // Lleva los datos a JSON, si es que existen.
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
        System.out.println("- Menú Principal Mudy Cafetería -");
        System.out.println("1 - Agregar.");
        System.out.println("2 - Eliminar.");
        System.out.println("3 - Buscar.");
        System.out.println("4 - Modificar.");
        System.out.println("5 - Mostrar.");
        System.out.println("0 - Salir.");
        System.out.println("- Ingrese opción numérica: ");
    }

}