package net.ent.etrs.repaspatient.model.entities;

import net.ent.etrs.repaspatient.model.entities.exceptions.PatientException;
import net.ent.etrs.repaspatient.model.entities.exceptions.RepasConstructionException;
import net.ent.etrs.repaspatient.model.entities.exceptions.RepasException;
import net.ent.etrs.repaspatient.model.entities.references.ConstantesMetier;
import net.ent.etrs.repaspatient.model.entities.references.RegimeAlimentaire;
import net.ent.etrs.repaspatient.model.entities.references.TypeRepas;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Repas {
    private final String id;
    private LocalDate dateRepas;
    private TypeRepas typeRepas;
    private List<RegimeAlimentaire> lstRegimeAlimentaire = new ArrayList<>();;

    public Repas(final LocalDate dateRepas, final TypeRepas typeRepas) throws RepasException {
        try {
            this.id = UUID.randomUUID().toString();
            this.setDateRepas(dateRepas);
            this.setTypeRepas(typeRepas);
        } catch (RepasException e) {
            throw new RepasException(e.getMessage());
        }
    }

    public String getId() {
        return this.id;
    }

    public LocalDate getDateRepas() {
        return this.dateRepas;
    }

    public void setDateRepas(final LocalDate dateRepas) throws RepasException {
        if (dateRepas == null) {
            throw new RepasException(ConstantesMetier.REPAS_DATE_EXCEPTION);
        }
        this.dateRepas = dateRepas;
    }

    public TypeRepas getTypeRepas() {
        return this.typeRepas;
    }

    public void setTypeRepas(final TypeRepas typeRepas) throws RepasException {
        if (typeRepas == null) {
            throw new RepasException(ConstantesMetier.REPAS_TYPE_REPAS_EXCEPTION);
        }
        this.typeRepas = typeRepas;
    }

    public List<RegimeAlimentaire> getLstRegimeAlimentaire() {
        return this.lstRegimeAlimentaire;
    }

    public void ajouterRegimeAlimentaire(final RegimeAlimentaire regimeAlimentaire) throws RepasConstructionException {
        if (regimeAlimentaire == null) {
            throw new RepasConstructionException(ConstantesMetier.MSG_PATIENT_REGIME_ALIMENTAIRE_EXCEPTION);
        }
        if (this.getLstRegimeAlimentaire().contains(regimeAlimentaire)) {
            throw new RepasConstructionException(ConstantesMetier.MSG_PATIENT_REGIME_ALIMENTAIRE_EXIST_EXCEPTION);
        }
        this.lstRegimeAlimentaire.add(regimeAlimentaire);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Repas)) return false;
        Repas repas = (Repas) o;
        return Objects.equals(getDateRepas(), repas.getDateRepas()) && getTypeRepas() == repas.getTypeRepas();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDateRepas(), getTypeRepas());
    }

    @Override
    public String toString() {
        return "Repas{" +
                "id='" + id + '\'' +
                ", dateRepas=" + dateRepas +
                ", typeRepas=" + typeRepas +
                '}';
    }
}
