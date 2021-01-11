package javaproject.BusinessApplication.exeptions;

public class DateException extends RuntimeException{

    public DateException() {
    }

    public DateException(String message) {
        super(message);
    }

    public DateException(String message, Throwable cause) {
        super(message, cause);
    }

    public DateException(Throwable cause) {
        super(cause);
    }
}
