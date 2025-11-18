package Controllers.FuncionesMenu;
import java.util.Scanner;

public class Utilidades {
    static Scanner sc = new Scanner(System.in);
    // Método mostrar listas para que el usuario elija qué clase manipular.
    public static void mostrarListas(){
        System.out.println("1 - Empleados.");
        System.out.println("2 - Clientes.");
        System.out.println("3 - Proveedores.");
        System.out.println("4 - Productos.");
        System.out.println("5 - Pedidos.");
        System.out.println("6 - Marcas.");
        System.out.println("7 - Categorías.");
        System.out.println("0 - Salir.");
        System.out.println("- Ingrese opción numérica: ");
    }
    public static void mostrarListasModificar(){
        System.out.println("1 - Empleados.");
        System.out.println("2 - Clientes.");
        System.out.println("3 - Proveedores.");
        System.out.println("4 - Productos.");
        System.out.println("5 - Pedidos.");
        System.out.println("6 - Marcas.");
        System.out.println("7 - Categorías.");
        System.out.println("8 - Monto mínimo de descuento.");
        System.out.println("9 - Descuento.");
        System.out.println("0 - Salir.");
        System.out.println("- Ingrese opción numérica: ");
    }

    public static char continuar(String nombreMenu){
        char c;
        System.out.println("- Continuar " + nombreMenu + "?? (s/n): ");
        c = sc.next().toLowerCase().charAt(0);
        sc.nextLine();
        if (c != 'n' && c != 's') throw new IllegalArgumentException("- La opción debe ser 's' o 'n'. ");
        return c;
    }

    public static void validarString(String string) throws IllegalArgumentException {
        // esta expresion devuelve true si el string contiene un numero
        if(string.matches(".*//d*."))
            throw new IllegalArgumentException("El texto ingresado no puede contener números.");

        // esa expresion da true si tiene algo que NO es una letra de la a-z,A-Z,
        // sus variantes con acentos y espacios.
        if(string.matches(".*[^a-zA-ZáéíóúÁÉÍÓÚñÑ ].*"))
            throw new IllegalArgumentException("El texto ingresado no puede contener caracteres especiales.");

        if(string.length() < 2)
            throw new IllegalArgumentException("El texto ingresado debe tener al menos 2 caracteres.");
    }
}
