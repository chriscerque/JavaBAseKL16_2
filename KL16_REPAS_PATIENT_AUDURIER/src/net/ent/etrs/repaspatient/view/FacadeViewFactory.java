package net.ent.etrs.repaspatient.view;


import java.util.Objects;

public final class FacadeViewFactory {
    private static FacadeViewImpl fv;

    private FacadeViewFactory() {
    }

    public static FacadeViewImpl fabriquerFacadeView() {
        return FacadeViewFactory.getInstance();
    }

    private static FacadeViewImpl getInstance() {
        return Objects.isNull(FacadeViewFactory.fv) ? fv = new FacadeViewImpl() : FacadeViewFactory.fv;
    }
}
