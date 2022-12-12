package net.ent.etrs.repaspatient.view;

import java.util.Objects;

public final class FacadeVueFactory {

    private static FacadeVueImpl fv;

    private FacadeVueFactory() {
    }

    public static FacadeVueImpl fabriquerFacadeVue() {
        return FacadeVueFactory.getInstance();
    }

    private static FacadeVueImpl getInstance() {
        return Objects.isNull(FacadeVueFactory.fv) ? fv = new FacadeVueImpl() : FacadeVueFactory.fv;
    }
}
