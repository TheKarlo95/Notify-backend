package hr.karlovrbic.notify.v1.dao;

/**
 * Created by Karlo Vrbic on 10/30/16.
 */
public class DAOException extends RuntimeException {

    private static final long serialVersionUID = -1947821720423967522L;

    public DAOException() {
        super();
    }

    public DAOException(String message) {
        super(message);
    }

    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public DAOException(Throwable cause) {
        super(cause);
    }

    public DAOException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}