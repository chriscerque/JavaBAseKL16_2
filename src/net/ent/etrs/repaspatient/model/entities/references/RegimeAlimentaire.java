package net.ent.etrs.repaspatient.model.entities.references;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.ent.etrs.repaspatient.model.entities.exceptions.RegimeAlimentaireException;

@AllArgsConstructor
@Getter
public enum RegimeAlimentaire {
    VEGAN("végan"),
    VEGETARIEN("Végétarien"),
    MIXE("mixé"),
    SANS_SEL("sans sel"),
    DIABETIQUE("diabétique");

    private final String libelle;

    public static RegimeAlimentaire getByLibelle(final String libelle) throws RegimeAlimentaireException {
        for (RegimeAlimentaire rg : RegimeAlimentaire.values()) {
            if (rg.getLibelle().equals(libelle)) {
                return rg;
            }
        }
        throw new RegimeAlimentaireException(ConstantesMetier.REGIME_ALIMENTAIRE_GET_BY_LIBELLE_EXCEPTION);
    }

//    public static List<String> listerRegimeAlimentaire() {
//        List<String> lst = new ArrayList<>();
//        for (RegimeAlimentaire rg : RegimeAlimentaire.values()) {
//            lst.add(rg.getLibelle());
//        }
//        return Collections.unmodifiableList(lst);
//    }
}
