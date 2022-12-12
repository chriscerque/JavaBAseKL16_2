package net.ent.etrs.KL16_REPAS_PATIENT_SANTOS.model.facades;

import net.ent.etrs.KL16_REPAS_PATIENT_SANTOS.model.facades.exceptions.FacadeMetier;

public class FacadeMetierFactory {

    private FacadeMetierFactory(){

    }

    public static FacadeMetier fabriquerFacadeMetier(){
        return new FacadeMetierImpl();
    }


}
