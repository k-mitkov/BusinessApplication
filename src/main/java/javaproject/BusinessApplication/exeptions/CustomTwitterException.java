package javaproject.BusinessApplication.exeptions;

public class CustomTwitterException extends RuntimeException{

    public CustomTwitterException() {
    }

    public CustomTwitterException(String message) {
        super(message);
    }

    public CustomTwitterException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomTwitterException(Throwable cause) {
        super(cause);
    }
}
