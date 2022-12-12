package net.ent.etrs.test.view;

public final class FacadeViewFactory {

    private FacadeViewFactory() {}

    public static FacadeVue fabriquerFacadeVue(){
        return new FacadeViewImpl();
    }

}
