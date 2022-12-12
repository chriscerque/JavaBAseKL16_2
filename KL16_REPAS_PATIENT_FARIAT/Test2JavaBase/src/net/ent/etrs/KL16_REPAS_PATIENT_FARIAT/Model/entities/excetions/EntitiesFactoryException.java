package net.ent.etrs.KL16_REPAS_PATIENT_FARIAT.Model.entities.excetions;

public class EntitiesFactoryException extends Exception {
    public EntitiesFactoryException(final String message) {
        super(message);
    }

    public EntitiesFactoryException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public EntitiesFactoryException(final Throwable cause) {
        super(cause);
    }

    public EntitiesFactoryException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
