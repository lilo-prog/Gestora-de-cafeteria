package Class.Exceptions;

public class EElementoRepetido extends RuntimeException {
    public EElementoRepetido() {
        super("- Elemento cargado anteriormente.");
    }
}
