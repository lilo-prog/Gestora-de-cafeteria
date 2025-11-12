package Exceptions;

public class ListaNoCargadaException extends Exception {
    public ListaNoCargadaException() {
        super(" - La lista no está cargada.");
    }
}
