package net.ent.etrs.repaspatient.model.entities.exceptions;

public class NomPatientException extends Exception {
    public NomPatientException(final String message) {
        super(message);
    }

    public NomPatientException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public NomPatientException(final Throwable cause) {
        super(cause);
    }

    public NomPatientException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
