import Controllers.*;
import Exceptions.CaracteresException;
import Exceptions.FechaPosteriorException;
import Exceptions.RequisitoDeEdadException;
import Models.Personas.Empleado;
import Exceptions.ElementoRepetidoException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static Cafeteria Mudy = new Cafeteria();

    static Scanner sc = new Scanner(System.in);

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

    public static void agregarEmpleado() throws ElementoRepetidoException{
        Empleado e = new Empleado();
        while(true) {
            try {
                System.out.println("- Ingrese nombre del empleado: ");
                e.setNombre(sc.nextLine());
                //Si hay una secuencia de caracteres que contiene un numero tira una excepcion.
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
                sc.nextLine();
                break;
            }catch(){

            }
        }

        System.out.println("- Ingrese sueldo del empleado: ");
        e.setSueldo(sc.nextDouble());
        sc.nextLine();

        Mudy.listaEmpleados.agregar(e.getDni(), e);
    }

}