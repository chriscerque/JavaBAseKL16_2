package net.ent.etrs.repaspatient.model.entities;

import net.ent.etrs.repaspatient.model.entities.exceptions.ConstantesException;
import net.ent.etrs.repaspatient.model.entities.exceptions.RepasException;
import net.ent.etrs.repaspatient.model.entities.references.RegimeAlimentaire;
import net.ent.etrs.repaspatient.model.entities.references.TypeRepas;

import java.time.LocalDate;
import java.util.*;

public class Repas {

    // ATTRIBUTES
    private final UUID id;
    private LocalDate dateRepas;
    private TypeRepas typeRepas;
    private List<RegimeAlimentaire> lstRegimeAlimentaire;

    // CONSTRUCTOR(S)

    protected Repas(LocalDate dateRepas, TypeRepas typeRepas) throws RepasException {
        this.setDateRepas(dateRepas);
        this.setTypeRepas(typeRepas);
        this.lstRegimeAlimentaire = new ArrayList<>();
        this.id = UUID.randomUUID();
    }


    // GETTERS & SETTERS


    public UUID getId() {
        return this.id;
    }

    public LocalDate getDateRepas() {
        return this.dateRepas;
    }

    public void setDateRepas(LocalDate dateRepas) throws RepasException {
        if (dateRepas == null) {
            throw new RepasException(ConstantesException.REPAS_DATEREPAS_NULL_EXCEPTION);
        }

        this.dateRepas = dateRepas;
    }

    public TypeRepas getTypeRepas() {
        return this.typeRepas;
    }

    public void setTypeRepas(TypeRepas typeRepas) throws RepasException {
        if (typeRepas == null) {
            throw new RepasException(ConstantesException.REPAS_TYPEREPAS_NULL_EXCEPTION);
        }

        this.typeRepas = typeRepas;
    }

    public List<RegimeAlimentaire> getLstRegimeAlimentaire() {
        return Collections.unmodifiableList(this.lstRegimeAlimentaire);
    }

    /**
     * Ajouter un regime alimentaire a la liste des regimes alimentaires.
     * <p>
     * Contrôle si le régime alimentaire est deja present, car un repas ne peut pas avoir plusieurs fois le même.
     *
     * @param regimeAlimentaire le regime alimentaire à ajouter.
     * @throws RepasException l'exception associé a la classe Repas.
     */
    public void ajouterRegimeAlimentaire(final RegimeAlimentaire regimeAlimentaire) throws RepasException {
        if (Objects.isNull(regimeAlimentaire)) {
            throw new RepasException(ConstantesException.MSG_REPAS_REGIME_ALIMENTAIRE_NON_RENSEIGNE_EXCEPTION);
        }
        if (this.lstRegimeAlimentaire.contains(regimeAlimentaire)) {
            throw new RepasException(ConstantesException.MSG_REPAS_REGIME_ALIMENTAIRE_EXIST_EXCEPTION);
        }
        for (RegimeAlimentaire item : this.lstRegimeAlimentaire) {
            if (item.name().equals(regimeAlimentaire.name())) {
                throw new RepasException(ConstantesException.MSG_REPAS_REGIME_ALIMENTAIRE_EXIST_EXCEPTION);
            }
        }
        this.lstRegimeAlimentaire.add(regimeAlimentaire);
    }


    // EQUALS & HASHCODE

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Repas repas = (Repas) o;
        return Objects.equals(getDateRepas(), repas.getDateRepas()) && getTypeRepas() == repas.getTypeRepas();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDateRepas(), getTypeRepas());
    }


    // TO STRING

    @Override
    public String toString() {
        return "Repas{" +
                "id='" + id + '\'' +
                ", dateRepas=" + dateRepas +
                ", typeRepas=" + typeRepas +
                ", lstRegimeAlimentaire=" + lstRegimeAlimentaire +
                '}';
    }


}
