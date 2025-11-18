package Exceptions;

public class SalirDelIngresoDeDatosException extends RuntimeException {
    public SalirDelIngresoDeDatosException() {
        super("- Saliendo del ingreso de datos...");
    }
}
