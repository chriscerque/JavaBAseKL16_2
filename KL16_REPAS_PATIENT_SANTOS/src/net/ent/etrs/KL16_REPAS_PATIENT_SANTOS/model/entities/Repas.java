package net.ent.etrs.KL16_REPAS_PATIENT_SANTOS.model.entities;

import net.ent.etrs.KL16_REPAS_PATIENT_SANTOS.model.entities.exceptions.RepasConstructionException;
import net.ent.etrs.KL16_REPAS_PATIENT_SANTOS.model.entities.exceptions.RepasException;
import net.ent.etrs.KL16_REPAS_PATIENT_SANTOS.model.references.ConstantesMetier;
import net.ent.etrs.KL16_REPAS_PATIENT_SANTOS.model.references.RegimeAlimentaire;
import net.ent.etrs.KL16_REPAS_PATIENT_SANTOS.model.references.TypeRepas;

import java.time.LocalDate;
import java.util.*;

public class Repas {
    private UUID id =UUID.randomUUID() ;
    private LocalDate dateRepas ;
    private TypeRepas typeRepas ;
    private final List<RegimeAlimentaire> lstRegimeAlimentaire = new ArrayList<>();

    public UUID getId() {
        return this.id;
    }

    public LocalDate getDateRepas() {
        return this.dateRepas;
    }

    public TypeRepas getTypeRepas() {
        return this.typeRepas;
    }

    public List<RegimeAlimentaire> getLstRegimeAlimentaire() {
        return Collections.unmodifiableList(this.lstRegimeAlimentaire);
    }

    public Repas(LocalDate dateRepas, TypeRepas typeRepas) throws RepasConstructionException {
        setDateRepas(dateRepas);
         setTypeRepas(typeRepas);
    }

    public void setDateRepas(LocalDate dateRepas) throws RepasConstructionException {
        if (dateRepas == null) {
            throw new RepasConstructionException(ConstantesMetier.REPAS_DATE_EXCEPTION);
        }

        this.dateRepas = dateRepas;
    }
    public void setTypeRepas(TypeRepas typeRepas) throws RepasConstructionException {
        if (typeRepas == null) {
            throw new RepasConstructionException(ConstantesMetier.REPAS_TYPE_REPAS_EXCEPTION);
        }

        this.typeRepas = typeRepas;
    }
    public void  ajouterRegimeAlimentaire (RegimeAlimentaire regimeAlimentaire) throws RepasException {
        if (regimeAlimentaire == null || this.lstRegimeAlimentaire.contains(regimeAlimentaire) ) {
            throw new RepasException(ConstantesMetier.PATIENT_REPAS_REGIME_ALIMENTAIRE_EXCEPTION);
        }
        this.lstRegimeAlimentaire.add(regimeAlimentaire);

    }

    @Override
    public boolean equals(Object o) {
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
                "id=" + id +
                ", dateRepas=" + dateRepas +
                ", typeRepas=" + typeRepas +
                ", lstRegimeAlimentaire=" + lstRegimeAlimentaire +
                '}';
    }
}
