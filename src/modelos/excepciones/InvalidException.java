package modelos.excepciones;

public class InvalidException extends RuntimeException {
    public InvalidException(String message) {
        super(message);
    }
}
