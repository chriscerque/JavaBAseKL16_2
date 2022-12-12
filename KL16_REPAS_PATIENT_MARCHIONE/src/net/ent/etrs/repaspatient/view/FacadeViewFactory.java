package net.ent.etrs.repaspatient.view;

/**
 * The type Facade view factory.
 */
public final class FacadeViewFactory {

    private FacadeViewFactory(){}

    /**
     * Fabriquer facade view facade vue.
     *
     * @return the facade vue
     */
    public static FacadeVue fabriquerFacadeView(){
      return (FacadeVue) new FacadeVueImpl();
    }
}
