package net.ent.etrs.repaspatient.view;

public final class FacadeVueFactory {

    private FacadeVueFactory() {
    }


    /**
     * Permet de fabriquer la facade vue.
     * @return
     */

    public static FacadeVue fabriquerFacadeVue(){
        return new FacadeVueImpl();
    }


}
