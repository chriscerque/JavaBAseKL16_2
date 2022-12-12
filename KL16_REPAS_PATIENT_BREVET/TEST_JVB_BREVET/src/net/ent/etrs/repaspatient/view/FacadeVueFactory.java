package net.ent.etrs.repaspatient.view;

/**
 * Fabrique de la {@link FacadeVueImpl}
 */
public class FacadeVueFactory {
    //region CONSTRUCTEUR(S)

    private FacadeVueFactory() {
    }

    //endregion
    //region MÉTHODES
    public static FacadeVue fabriquerFacadeVue() {
        return new FacadeVueImpl();
    }
    //endregion
}
