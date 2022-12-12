package net.ent.etrs.kl16repaspatientgouin.model.facades;

import net.ent.etrs.kl16repaspatientgouin.model.facades.exceptions.BusinessException;

public class FacadeMetierFactory {
    private FacadeMetierFactory() {
    }

    public static FacadeMetier fabriquerFacadeMetier() throws BusinessException {
        return new FacadeMetierImpl();
    }
}
