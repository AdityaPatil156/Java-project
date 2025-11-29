package restaurant.exception;

public class TableNotAvailableException extends Exception {
    public TableNotAvailableException(String msg) {
        super(msg);
    }
}
