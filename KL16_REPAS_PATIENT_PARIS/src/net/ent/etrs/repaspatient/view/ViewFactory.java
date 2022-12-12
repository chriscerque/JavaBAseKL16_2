package net.ent.etrs.repaspatient.view;

public final class ViewFactory {


    private ViewFactory(){}

    public static FacadeVue fabriquerFacadeVue(){
        return new FacadeVueImpl();
    }
}
