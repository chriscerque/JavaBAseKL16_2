package net.ent.etrs.repaspatient.view;

public class FacadeViewFactory {

    public FacadeViewFactory() {
    }

    public static FacadeView fabriquerFacadeView(){
        return new FacadeViewImpl();
    }
}
