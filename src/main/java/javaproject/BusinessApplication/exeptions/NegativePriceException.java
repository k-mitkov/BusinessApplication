package javaproject.BusinessApplication.exeptions;

public class NegativePriceException extends RuntimeException{

    public NegativePriceException() {
    }

    public NegativePriceException(String message) {
        super(message);
    }

    public NegativePriceException(String message, Throwable cause) {
        super(message, cause);
    }

    public NegativePriceException(Throwable cause) {
        super(cause);
    }
}
