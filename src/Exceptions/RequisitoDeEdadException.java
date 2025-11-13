package Exceptions;

public class RequisitoDeEdadException extends Exception {
    public RequisitoDeEdadException() {
        super("- La edad debe ser mayor o igual a 16 años.");
    }
}
