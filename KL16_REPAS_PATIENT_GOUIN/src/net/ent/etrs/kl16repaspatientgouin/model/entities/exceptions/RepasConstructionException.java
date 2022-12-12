package net.ent.etrs.kl16repaspatientgouin.model.entities.exceptions;

public class RepasConstructionException extends Exception {

    public RepasConstructionException(String message) {
        super(message);
    }

    public RepasConstructionException(String message, Throwable cause) {
        super(message, cause);
    }

    public RepasConstructionException(Throwable cause) {
        super(cause);
    }

    public RepasConstructionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
