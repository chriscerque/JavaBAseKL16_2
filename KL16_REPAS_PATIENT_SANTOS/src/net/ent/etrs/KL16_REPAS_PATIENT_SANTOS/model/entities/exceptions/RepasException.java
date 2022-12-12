package net.ent.etrs.KL16_REPAS_PATIENT_SANTOS.model.entities.exceptions;

public class RepasException extends Exception {

    public RepasException(String message) {
        super(message);
    }

    public RepasException(String message, Throwable cause) {
        super(message, cause);
    }

    public RepasException(Throwable cause) {
        super(cause);
    }

    public RepasException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
