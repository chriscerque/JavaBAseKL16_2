package net.ent.etrs.repaspatient.model.entities;

import net.ent.etrs.repaspatient.model.entities.exceptions.RegimeAlimentaireException;

import java.util.ArrayList;
import java.util.List;

public enum RegimeAlimentaire {
    VEGAN("végan"),
    VEGETARIEN("Végétarien"),
    MIXE("mixé"),
    SANS_SEL("sans sel"),
    DIABETIQUE("diabétique");

    private final String libelle;


    RegimeAlimentaire(String libelle) {
        this.libelle = libelle;
    }

    public String getLibelle() {
        return libelle;
    }

    @Override
    public String toString() {
        return "RegimeAlimentaire{" +
                "libelle='" + libelle + '\'' +
                "} " + super.toString();
    }

    /**
     * Permet de récupérer le libelle de la liste des regimes alimentaires.
     * @return
     */
    public static List<String> getLstLibelleRegimeAlimentaire() {
        List<String> lst = new ArrayList<>();

        for (RegimeAlimentaire c : RegimeAlimentaire.values()) {
            lst.add(c.getLibelle());
        }
        return lst;
    }

    /**
     * Permet de recuperer le regime avec son libelle.
     * @param libelle
     * @return
     * @throws RegimeAlimentaireException
     */
    public static RegimeAlimentaire getRegimeByLibelle(final String libelle) throws RegimeAlimentaireException {
        for (RegimeAlimentaire ra : RegimeAlimentaire.values()) {
            if (ra.getLibelle().equals(libelle)) {
                return ra;
            }
        }
        throw new RegimeAlimentaireException("");
    }
}