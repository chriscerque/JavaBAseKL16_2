package net.ent.etrs.repaspatient.view;

//@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Viewfactory {

    private Viewfactory() {
    }

    public static FacadeVue fabriquerFacadeVue() {
        return new FacadeVueImpl();
    }


}
