package net.ent.etrs.repaspatient.model.entities.references;


import net.ent.etrs.repaspatient.model.entities.exceptions.RegimeAlimentaireException;

public enum RegimeAlimentaire {
    VEGAN("végan"),
    VEGETARIEN("Végétarien"),
    MIXE("mixé"),
    SANS_SEL("sans sel"),
    DIABETIQUE("diabétique");

    private final String libelle;

    RegimeAlimentaire(final String libelle) {
        this.libelle = libelle;
    }

    /**
     * Retourne le libellé du regime alim.
     * @return nom et le libelle du regime alim
     */
    public String getLibelle() {
        return String.format("%s | %s", this.name(),this.getLibelle());
    }

    /**
     * Retourne le RegimeAlimentaire en fonction de son libelle.
     * @param lib le libellé du RegimeAlimentaire
     * @return le RegimeAlimentaire trouvé.
     * @throws RegimeAlimentaireException si aucune RegimeAlimentaire n'est trouvé.
     */
    public static RegimeAlimentaire getByLibelle(String lib) throws RegimeAlimentaireException {
        for (RegimeAlimentaire r : RegimeAlimentaire.values()){
            if (r.getLibelle().equals(lib)){
                return r;
            }
        }
        throw new RegimeAlimentaireException(ConstantesMetier.REGIME_ALIMENTAIRE_GET_BY_LIBELLE_EXCEPTION);
    }
}
