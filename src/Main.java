import Class.Gestores.*;
import Class.Personas.Empleado;
import Exceptions.ElementoRepetidoException;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static Cafeteria Mudy = new Cafeteria();

    static Scanner sc = new Scanner(System.in);
    static Boolean bandera = false;
    public static void main(String[] args) {
        int opcion;

        do{
            menu();
            opcion = sc.nextInt();
            sc.nextLine();
            switch(opcion){
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

                    break;
                case 3:
                    System.out.println("- Buscar -");
                    mostrarListas();

                    break;
                case 4:
                    System.out.println("- Mostrar -");
                    mostrarListas();

                    break;
                default:
                    break;
            }
        }while(opcion > 0 && opcion <= 4);


    }

    public static void menu(){
        System.out.println("- Menú Principal -");
        System.out.println("1 - Agregar.");
        System.out.println("2 - Eliminar.");
        System.out.println("3 - Buscar.");
        System.out.println("4 - Mostrar.");
        System.out.println("- Ingrese opción: ");
    }

    public static void mostrarListas(){
        System.out.println("1 - Empleados.");
        System.out.println("2 - Clientes.");
        System.out.println("3 - Proveedores.");
        System.out.println("4 - Productos.");
        System.out.println("5 - Pedidos.");
        System.out.println("6 - Marcas.");
        System.out.println("7 - Categorias.");
        System.out.println("- Ingrese opción: ");
    }
    public static void agregar(int opcion) throws ElementoRepetidoException, InputMismatchException {
        switch(opcion){
            case 1:
                agregarEmpleado();
                System.out.println("- Empleado cargado correctamente!");
                break;


        }
    }
    public static Boolean agregarEmpleado() throws ElementoRepetidoException{
        Empleado e = new Empleado();
        System.out.println("- Ingrese nombre del empleado: ");
        e.setNombre(sc.nextLine());

        System.out.println("- Ingrese apellido del empleado: ");
        e.setApellido(sc.nextLine());

        System.out.println("- Ingrese edad del empleado: ");
        e.setEdad(sc.nextInt());
        sc.nextLine();

        System.out.println("- Ingrese dni del empleado: ");
        e.setDni(sc.nextInt());
        sc.nextLine();

        System.out.println("- Ingrese teléfono del empleado: ");
        e.setTelefono(sc.nextInt());
        sc.nextLine();

        System.out.println("- Ingrese sueldo del empleado: ");
        e.setSueldo(sc.nextDouble());
        sc.nextLine();

        return Mudy.listaEmpleados.agregar(e.getDni(), e);
    }

}