package net.ent.etrs.repaspatient.model.facades;

public class FacadeFactory {

    private FacadeFactory() {
    }

    /**
     * Permet de fabriquer la facade metier.
     * @return
     */
    public static FacadeMetier fabriquerFacadeMetier(){
        return new FacadeMetierImpl();
    }




}
