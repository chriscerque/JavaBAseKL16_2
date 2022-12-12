package net.ent.etrs.KL16_REPAS_PATIENT_SANTOS.model.entities.exceptions;

public class PatientConstructionException extends Exception {

    public PatientConstructionException(String message) {
        super(message);
    }

    public PatientConstructionException(String message, Throwable cause) {
        super(message, cause);
    }

    public PatientConstructionException(Throwable cause) {
        super(cause);
    }

    public PatientConstructionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
