package exception;

public class PropertyFileException extends RuntimeException {
    public PropertyFileException() {
        super("No property file!");
    }
}
