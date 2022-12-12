package net.ent.etrs.repaspatient.model.entities.references;


import net.ent.etrs.repaspatient.model.entities.exceptions.RegimeAlimentaireException;

import java.util.Objects;

public enum RegimeAlimentaire {
    VEGAN("végan"),
    VEGETARIEN("Végétarien"),
    MIXE("mixé"),
    SANS_SEL("sans sel"),
    DIABETIQUE("diabétique");

    //region ATTRIBUTS
    private final String libelle;
    //endregion

    //region CONSTRUCTEUR(S)
    RegimeAlimentaire(final String libelle) {
        this.libelle = libelle;
    }
    //endregion
    //region GETTER SETTER

    /**
     * Retourne le régime alimentaire en fonction de son libellé.
     *
     * @param lib le libellé du Régime alimentaire
     * @return le régime alimentaire trouvée.
     * @throws RegimeAlimentaireException si aucun régime Alimentaire n'est trouvé.
     */
    public static RegimeAlimentaire getByLibelle(String lib) throws RegimeAlimentaireException {
        for (RegimeAlimentaire r : RegimeAlimentaire.values()) {
            if (Objects.equals(r.getLibelle(), lib)) {
                return r;
            }
        }
        throw new RegimeAlimentaireException(ConstantesMetier.REGIME_ALIMENTAIRE_GET_BY_LIBELLE_EXCEPTION);
    }

    //endregion
    //region MÉTHODES

    public String getLibelle() {
        return this.libelle;
    }
    //endregion
}
