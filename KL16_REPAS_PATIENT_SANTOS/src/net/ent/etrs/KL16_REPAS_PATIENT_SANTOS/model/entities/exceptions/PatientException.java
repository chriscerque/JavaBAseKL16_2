package net.ent.etrs.KL16_REPAS_PATIENT_SANTOS.model.entities.exceptions;

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
