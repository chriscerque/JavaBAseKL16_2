package net.ent.etrs.repaspatient.view;

public class ViewFactory {
    public ViewFactory() {
    }
    public static FacadeVue fabriquerFacadeVue() {
        return new FacadeVueImpl();
    }
}
