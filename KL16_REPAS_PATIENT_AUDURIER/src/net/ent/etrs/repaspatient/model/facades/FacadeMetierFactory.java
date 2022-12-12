package net.ent.etrs.repaspatient.model.facades;

import java.util.Objects;

public final class FacadeMetierFactory {
    private static FacadeMetierImpl fm;

    private FacadeMetierFactory() {
    }

    public static FacadeMetierImpl fabriquerFacadeMetier() {
        return FacadeMetierFactory.getInstance();
    }

    private static FacadeMetierImpl getInstance() {
        return Objects.isNull(FacadeMetierFactory.fm) ? fm = new FacadeMetierImpl() : FacadeMetierFactory.fm;
    }

}
