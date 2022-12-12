package net.ent.etrs.repaspatient.model.facades;

public final class FacadeMetierFactory {

    private FacadeMetierFactory() {
    }

    /**
     * Instancie une FacadeMetierImpl.
     **/
    public static FacadeMetier fabriquerFacadeMetier() {
        return new FacadeMetierImpl();
    }
}