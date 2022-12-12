package net.ent.etrs.repaspatient.presenterView;

public final class FacadeViewFactory {

    private FacadeViewFactory() {
    }

    public static FacadeView fabriquerFacadeView(){
        return new FacadeViewImpl();
    }
}
