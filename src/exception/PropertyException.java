package exception;

public class PropertyException extends RuntimeException {
    public PropertyException() {
        super("Wrong property value!");
    }
}
