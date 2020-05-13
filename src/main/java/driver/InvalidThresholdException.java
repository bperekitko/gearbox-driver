package driver;

public class InvalidThresholdException extends RuntimeException {
    private static final String MESSAGE = "Provided threshold is invalid. Acceptable values are between 0 and 1";

    public InvalidThresholdException() {
        super(MESSAGE);
    }
}
