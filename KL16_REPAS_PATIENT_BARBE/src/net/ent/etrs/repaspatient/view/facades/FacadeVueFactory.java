package net.ent.etrs.repaspatient.view.facades;

public final class FacadeVueFactory {


    // CONSTRUCTOR(S)
    public FacadeVueFactory() {
    }

    public static FacadeVue fabriquerFacadeVue() {
        return new FacadeVueImpl();
    }
}
