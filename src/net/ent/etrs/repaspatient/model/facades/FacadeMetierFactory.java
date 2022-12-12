package net.ent.etrs.repaspatient.model.facades;

//@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FacadeMetierFactory {
    private FacadeMetierFactory() {
    }

    public static FacadeMetier fabriquerFacadeMetier() {
        return new FacadeMetierImpl();
    }
}
