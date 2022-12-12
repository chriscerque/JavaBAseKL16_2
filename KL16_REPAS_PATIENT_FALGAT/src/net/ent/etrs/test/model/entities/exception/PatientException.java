package net.ent.etrs.test.model.entities.exception;

public class PatientException extends Exception {

    public PatientException(String message) {
        super(message);
    }

    public PatientException(String message, Throwable cause) {
        super(message, cause);
    }

    public PatientException(Throwable cause) {
        super(cause);
    }

    public PatientException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
