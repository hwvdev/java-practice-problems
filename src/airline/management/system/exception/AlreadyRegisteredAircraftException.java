package airline.management.system.exception;

public class AlreadyRegisteredAircraftException extends RuntimeException {
    public AlreadyRegisteredAircraftException(String message) {
        super(message);
    }
}
