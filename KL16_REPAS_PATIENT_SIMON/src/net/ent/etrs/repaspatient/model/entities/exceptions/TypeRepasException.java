package net.ent.etrs.repaspatient.model.entities.exceptions;

public class TypeRepasException extends Exception {
    public TypeRepasException(final String message) {
        super(message);
    }

    public TypeRepasException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public TypeRepasException(final Throwable cause) {
        super(cause);
    }

    public TypeRepasException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
