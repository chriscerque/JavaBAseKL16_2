package net.ent.etrs.repaspatient.model.facades;

public class FacadeMetierFactory {
    private FacadeMetierFactory(){}

    public static FacadeMetier fabriquerFacaMetier() {
        return new FacadeMetierImpl();
    }
}
