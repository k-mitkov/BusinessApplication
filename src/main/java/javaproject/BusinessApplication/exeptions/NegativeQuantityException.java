package javaproject.BusinessApplication.exeptions;

public class NegativeQuantityException extends RuntimeException{
    public NegativeQuantityException() {
    }

    public NegativeQuantityException(String message) {
        super(message);
    }

    public NegativeQuantityException(String message, Throwable cause) {
        super(message, cause);
    }

    public NegativeQuantityException(Throwable cause) {
        super(cause);
    }
}
