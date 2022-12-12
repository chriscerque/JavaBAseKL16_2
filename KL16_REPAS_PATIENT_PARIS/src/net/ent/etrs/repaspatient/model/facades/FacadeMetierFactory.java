package net.ent.etrs.repaspatient.model.facades;

public final class FacadeMetierFactory {

    private FacadeMetierFactory(){

    }

    public static FacadeMetier fabriquerFacadeMetier(){
        return new FacadeMetierImpl();
    }
}
