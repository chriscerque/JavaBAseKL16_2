package net.ent.etrs.repaspatient.model.entities.exceptions;

public class NumSecuException extends Throwable {
    public NumSecuException(final String message) {
        super(message);
    }

    public NumSecuException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public NumSecuException(final Throwable cause) {
        super(cause);
    }

    public NumSecuException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
