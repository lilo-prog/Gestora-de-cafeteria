import Controllers.*;
import Controllers.FuncionesMenu.*;
import Exceptions.*;
import Models.Personas.Cliente;
import Models.Personas.Empleado;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static Cafeteria Mudy = new Cafeteria();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws ElementoRepetidoException {
        // Trae los datos de los JSON,si es que existen
        JSONTokener tok1 = JsonUtiles.leerUnJson("Empleado.json");
        JSONTokener tok2 = JsonUtiles.leerUnJson("Cliente.json");
        JSONTokener tok3 = JsonUtiles.leerUnJson("Proveedor.json");
        JSONTokener tok4 = JsonUtiles.leerUnJson("Producto.json");
        JSONTokener tok5 = JsonUtiles.leerUnJson("Pedido.json");
        JSONTokener tok6 = JsonUtiles.leerUnJson("marcas.json");
        JSONTokener tok7 = JsonUtiles.leerUnJson("categorias.json");
        try {
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
            System.out.print("");
        }

        int opcion;
        char control = 's';
        Empleado empleado1 = new Empleado("Lilo", "Trola", LocalDate.parse("2004-06-24"), 45993056, "2236182257", 230000.0);
        Mudy.listaEmpleados.agregar((long)empleado1.getDni(),empleado1);
        Empleado empleado2 = new Empleado("Bruno", "Trola", LocalDate.parse("2004-06-24"), 47089846, "2236182257", 230000.0);
        Mudy.listaEmpleados.agregar((long)empleado2.getDni(),empleado2);

        Cliente cliente1 = new Cliente("Abril", "Derdoy", LocalDate.parse("2004-11-02"), 46277898, "2236695548", 10000.50);
        Mudy.listaClientes.agregar((long)cliente1.getDni(),cliente1);
        Cliente cliente2 = new Cliente("Pichicho", "Derdoy", LocalDate.parse("2000-03-11"), 464544432, "2236969696", 6000.50);
        Mudy.listaClientes.agregar((long)cliente2.getDni(),cliente2);

        boolean flagInvalido = false;

        while(control == 's') {
                while(true) {
                    menuPrincipal();
                    try {
                        opcion = sc.nextInt();
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("- Error: La opcion debe ser numerica");
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
                            System.out.println("- Agregar -");
                            Utilidades.mostrarListas();
                            try {
                                opcion = sc.nextInt();
                                sc.nextLine();
                                if(opcion < 0 || opcion > 7) throw new IllegalArgumentException("Opcion invalida");
                                Agregar.agregar(opcion,Mudy);
                                break;
                            } catch (InputMismatchException e) {
                                System.out.println("- Error: La opcion debe ser numerica");
                                sc.nextLine();
                            } catch (ElementoRepetidoException | ListaNoCargadaException | IllegalArgumentException e) {
                                System.out.println("- Error: " + e.getMessage());
                            }
                        }
                        break;
                    case 2:
                        flagInvalido = false;
                        while(true){
                            System.out.println("- Eliminar -");
                            Utilidades.mostrarListas();
                            try {
                                opcion = sc.nextInt();
                                sc.nextLine();
                                if(opcion < 0 || opcion > 7) throw new IllegalArgumentException("Opcion invalida");
                                Eliminar.eliminar(opcion,Mudy);
                                break;
                            } catch (ElementoNoEncontradoException | ListaNoCargadaException | IllegalArgumentException e) {
                                System.out.println("- Error: " + e.getMessage());
                            } catch (InputMismatchException e) {
                                System.out.println("- Error: La opcion debe ser numerica");
                                sc.nextLine();
                            }
                        }
                        break;
                    case 3:
                        flagInvalido = false;
                        while(true) {
                            System.out.println("- Buscar -");
                            Utilidades.mostrarListas();
                            try {
                                opcion = sc.nextInt();
                                sc.nextLine();
                                if(opcion < 0 || opcion > 7) throw new IllegalArgumentException("Opcion invalida");
                                Buscar.buscar(opcion,Mudy);
                                break;
                            } catch (ElementoNoEncontradoException | ListaNoCargadaException | IllegalArgumentException e) {
                                System.out.println("- Error: " + e.getMessage());
                            } catch (InputMismatchException e) {
                                System.out.println("- Error: La opcion debe ser numerica");
                                sc.nextLine();
                            }
                        }
                        break;
                    case 4:
                        flagInvalido = false;
                        while(true) {
                            System.out.println("- Modificar -");
                            Utilidades.mostrarListas();
                            try {
                                opcion = sc.nextInt();
                                sc.nextLine();
                                if(opcion < 0 || opcion > 7) throw new IllegalArgumentException("Opcion invalida");
                                Modificar.modificar(opcion,Mudy);
                                break;
                            } catch (ElementoNoEncontradoException | ListaNoCargadaException | IllegalArgumentException e) {
                                System.out.println("- Error: " + e.getMessage());
                            } catch (InputMismatchException e) {
                                System.out.println("- Error: La opcion debe ser numerica");
                                sc.nextLine();
                            }
                        }
                        break;
                    case 5:
                        flagInvalido = false;
                        while(true) {
                            System.out.println("- Mostrar -");
                            Utilidades.mostrarListas();
                            try {
                                opcion = sc.nextInt();
                                sc.nextLine();
                                if(opcion < 0 || opcion > 7) throw new IllegalArgumentException("Opcion invalida");
                                Mostrar.mostrar(opcion,Mudy);
                                break;
                            } catch (ListaNoCargadaException | IllegalArgumentException e) {
                                System.out.println("- Error: " + e.getMessage());
                            } catch (InputMismatchException e) {
                                System.out.println("- Error: La opcion debe ser numerica");
                                sc.nextLine();
                            }
                        }
                        break;
                    default:
                        System.out.println("- Error: Opción inválida.");
                        flagInvalido = true;
                        break;
                    }
                try {
                    if (control == 's' && !flagInvalido) {
                        control = Utilidades.continuar("en el menu principal");
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    sc.nextLine();
                }
        }
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
        System.out.println("- Menú Principal -");
        System.out.println("1 - Agregar.");
        System.out.println("2 - Eliminar.");
        System.out.println("3 - Buscar.");
        System.out.println("4 - Modificar.");
        System.out.println("5 - Mostrar.");
        System.out.println("0 - Salir.");
        System.out.println("- Ingrese opción: ");
    }

}