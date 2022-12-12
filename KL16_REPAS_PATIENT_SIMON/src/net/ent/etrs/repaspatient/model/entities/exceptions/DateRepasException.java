package net.ent.etrs.repaspatient.model.entities.exceptions;

public class DateRepasException extends Exception {
    public DateRepasException(final String message) {
        super(message);
    }

    public DateRepasException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public DateRepasException(final Throwable cause) {
        super(cause);
    }

    public DateRepasException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
