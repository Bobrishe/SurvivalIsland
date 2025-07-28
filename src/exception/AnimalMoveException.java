package exception;

public class AnimalMoveException extends RuntimeException {
    public AnimalMoveException(String animalName) {
        super(String.format("Animal %s can't move", animalName));
    }
}
