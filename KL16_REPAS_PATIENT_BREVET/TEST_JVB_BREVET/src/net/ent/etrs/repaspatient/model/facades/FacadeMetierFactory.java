package net.ent.etrs.repaspatient.model.facades;

public class FacadeMetierFactory {
    //region CONSTRUCTEUR(S)

    private FacadeMetierFactory() {
    }

    //endregion
    //region MÉTHODES
    public static FacadeMetier fabriquerFacadeMetier() {
        return new FacadeMetierImpl();
    }
    //endregion
}
