package Exceptions;

public class TelefonoInvalidoException extends Exception {
    public TelefonoInvalidoException() {
        super("El telefono ingresado no es valido");
    }
}
