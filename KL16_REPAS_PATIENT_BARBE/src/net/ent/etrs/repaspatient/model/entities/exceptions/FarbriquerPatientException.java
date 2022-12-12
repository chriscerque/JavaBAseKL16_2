package net.ent.etrs.repaspatient.model.entities.exceptions;

public class FarbriquerPatientException extends Exception {
    public FarbriquerPatientException(String message) {
        super(message);
    }

    public FarbriquerPatientException(String message, Throwable cause) {
        super(message, cause);
    }

    public FarbriquerPatientException(Throwable cause) {
        super(cause);
    }

    public FarbriquerPatientException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
