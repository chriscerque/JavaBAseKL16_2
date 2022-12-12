package net.ent.etrs.repaspatient.model.entities.exceptions;

public class PrenomPatientException extends Exception {
    public PrenomPatientException(final String message) {
        super(message);
    }

    public PrenomPatientException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public PrenomPatientException(final Throwable cause) {
        super(cause);
    }

    public PrenomPatientException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
