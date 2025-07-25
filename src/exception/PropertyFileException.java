package exception;

public class PropertyFileException extends RuntimeException {
    public PropertyFileException(String fileName) {
        super("No property file! Property file " + fileName + " doesn't exists or broken.");
    }
}
