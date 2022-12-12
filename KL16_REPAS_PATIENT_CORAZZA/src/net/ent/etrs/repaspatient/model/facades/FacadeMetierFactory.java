package net.ent.etrs.repaspatient.model.facades;


import net.ent.etrs.repaspatient.model.entities.references.ConstantesMetier;
import net.ent.etrs.repaspatient.model.facades.exceptions.BusinessException;

import java.time.LocalDate;

public final class FacadeMetierFactory {

    private FacadeMetierFactory() { }

    public static FacadeMetier fabriquerFacadeMetier() {
            return new FacadeMetierImpl();
    }
}
