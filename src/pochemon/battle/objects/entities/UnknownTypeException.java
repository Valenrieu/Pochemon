package pochemon.battle.objects.entities;

public class UnknownTypeException extends Exception {
    public UnknownTypeException(String message) {
        super(message);
    }

    public UnknownTypeException() {
        super();
    }
}
