package net.ent.etrs.repaspatient.model.facades;

public class FacadeMetierFactory {

    protected FacadeMetierFactory() {
    }
    public static FacadeMetier fabriquerFacadeMetier(){
        return new FacadeMetierImpl();
    }
}
