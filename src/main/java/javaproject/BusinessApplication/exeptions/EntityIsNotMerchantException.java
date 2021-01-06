package javaproject.BusinessApplication.exeptions;

public class EntityIsNotMerchantException extends RuntimeException{

    public EntityIsNotMerchantException() {
    }

    public EntityIsNotMerchantException(String message) {
        super(message);
    }

    public EntityIsNotMerchantException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityIsNotMerchantException(Throwable cause) {
        super(cause);
    }
}
