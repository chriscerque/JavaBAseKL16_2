package net.ent.etrs.KL16_REPAS_PATIENT_FARIAT.views.exceptions;

public class ConstructionPatientException extends Exception {
    public ConstructionPatientException(final String message) {
        super(message);
    }

    public ConstructionPatientException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ConstructionPatientException(final Throwable cause) {
        super(cause);
    }

    public ConstructionPatientException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
