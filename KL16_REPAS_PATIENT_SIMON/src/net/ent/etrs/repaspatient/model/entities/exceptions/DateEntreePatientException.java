package net.ent.etrs.repaspatient.model.entities.exceptions;

public class DateEntreePatientException extends Exception {
    public DateEntreePatientException(final String message) {
        super(message);
    }

    public DateEntreePatientException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public DateEntreePatientException(final Throwable cause) {
        super(cause);
    }

    public DateEntreePatientException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
