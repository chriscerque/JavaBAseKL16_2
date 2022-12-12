package net.ent.etrs.repaspatient.view;

public class ViewFactory {
    private ViewFactory() {}

    public static FacadeVue fabriquerFacadeVue() {
        return new FacadeVueImpl();
    }
}
