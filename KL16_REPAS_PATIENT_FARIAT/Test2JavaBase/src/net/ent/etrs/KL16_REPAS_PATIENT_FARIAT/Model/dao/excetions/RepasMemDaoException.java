package net.ent.etrs.KL16_REPAS_PATIENT_FARIAT.Model.dao.excetions;

public class RepasMemDaoException extends Exception {
    public RepasMemDaoException(final String message) {
        super(message);
    }

    public RepasMemDaoException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public RepasMemDaoException(final Throwable cause) {
        super(cause);
    }

    public RepasMemDaoException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
