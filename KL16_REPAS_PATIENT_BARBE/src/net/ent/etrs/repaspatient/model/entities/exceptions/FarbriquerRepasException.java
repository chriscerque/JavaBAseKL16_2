package net.ent.etrs.repaspatient.model.entities.exceptions;

public class FarbriquerRepasException extends Exception {
    public FarbriquerRepasException(String message) {
        super(message);
    }

    public FarbriquerRepasException(String message, Throwable cause) {
        super(message, cause);
    }

    public FarbriquerRepasException(Throwable cause) {
        super(cause);
    }

    public FarbriquerRepasException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
