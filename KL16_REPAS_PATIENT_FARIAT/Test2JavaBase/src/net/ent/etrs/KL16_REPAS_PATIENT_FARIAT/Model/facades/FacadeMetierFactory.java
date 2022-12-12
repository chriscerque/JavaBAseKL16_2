package net.ent.etrs.KL16_REPAS_PATIENT_FARIAT.Model.facades;

public final class FacadeMetierFactory {
    private FacadeMetierFactory() {
    }

    public static FacadeMetier fabriquerFacadeMetier() {
        return new FacadeMetierImpl();
    }
}
