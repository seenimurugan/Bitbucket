package sis.tv.exception;

public class SISException extends RuntimeException {

    public static final String DEFAULT_MESSAGE = "Exception Occured.";

    public SISException() {
        super(DEFAULT_MESSAGE);
    }

    public SISException(String message) {
        super(message);
    }

    public SISException(String message, Throwable cause) {
        super(message, cause);
    }
}
