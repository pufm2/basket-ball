package puf.m2.basket.exception;

public class BasketException extends Exception {

    private static final long serialVersionUID = 1199072650805830161L;

    private String message;
    
    public BasketException(Throwable cause) {
        super(cause);
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
