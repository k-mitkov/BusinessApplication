package javaproject.BusinessApplication.exeptions;

public class NotEnoughProductsExceptions extends RuntimeException{

    public NotEnoughProductsExceptions() {
    }

    public NotEnoughProductsExceptions(String message) {
        super(message);
    }

    public NotEnoughProductsExceptions(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEnoughProductsExceptions(Throwable cause) {
        super(cause);
    }
}
