package Exceptions;

public class FechaPosteriorException extends Exception {
    public FechaPosteriorException() {
        super("- La fecha no debe ser posterior al dia de hoy");
    }
}
