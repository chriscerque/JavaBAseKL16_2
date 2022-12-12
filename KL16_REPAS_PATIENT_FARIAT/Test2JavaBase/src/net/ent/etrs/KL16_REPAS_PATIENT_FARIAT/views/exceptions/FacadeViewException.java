package net.ent.etrs.KL16_REPAS_PATIENT_FARIAT.views.exceptions;

public class FacadeViewException extends Exception {
    public FacadeViewException(final String message) {
        super(message);
    }

    public FacadeViewException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public FacadeViewException(final Throwable cause) {
        super(cause);
    }

    public FacadeViewException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
