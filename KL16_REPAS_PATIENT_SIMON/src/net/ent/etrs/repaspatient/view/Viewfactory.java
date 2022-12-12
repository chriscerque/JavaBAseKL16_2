package net.ent.etrs.repaspatient.view;

public class Viewfactory {

    private Viewfactory() {
    }

    public static FacadeVue fabriquerFacadeVue(){
        return new FacadeVueImpl();
    }
}
