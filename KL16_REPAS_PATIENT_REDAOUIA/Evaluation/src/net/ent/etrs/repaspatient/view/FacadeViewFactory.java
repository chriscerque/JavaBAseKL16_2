package net.ent.etrs.repaspatient.view;

public final class FacadeViewFactory {

    private FacadeViewFactory(){}

    public static FacadeView fabriquerFacadeView(){
      return new FacadeViewImpl();
    }
}
