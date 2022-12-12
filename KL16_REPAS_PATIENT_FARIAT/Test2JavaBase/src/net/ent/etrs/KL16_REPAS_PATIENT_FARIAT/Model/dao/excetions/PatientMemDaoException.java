package net.ent.etrs.KL16_REPAS_PATIENT_FARIAT.Model.dao.excetions;

public class PatientMemDaoException extends Exception {
    public PatientMemDaoException(final String message) {
        super(message);
    }

    public PatientMemDaoException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public PatientMemDaoException(final Throwable cause) {
        super(cause);
    }

    public PatientMemDaoException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
