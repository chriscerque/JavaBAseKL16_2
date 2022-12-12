package net.ent.etrs.KL16_REPAS_PATIENT_FARIAT.views.facades;

public final class FacadeViewFactory {
    private FacadeViewFactory() {
    }

    public static FacadeView fabriquerFacadeView() {
        return new FacadeViewImpl();
    }
}

