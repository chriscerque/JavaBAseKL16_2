package net.ent.etrs.repaspatient.model.facade;

public final class FacadeMetierFactory {

    private FacadeMetierFactory() {
    }

    public static FacadeMetier fabriquerFacadeMetier(){
        return new FacadeMetierImpl();
    }
}
