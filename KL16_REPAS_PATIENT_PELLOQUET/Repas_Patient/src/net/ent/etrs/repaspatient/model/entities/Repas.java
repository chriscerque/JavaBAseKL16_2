package net.ent.etrs.repaspatient.model.entities;

import net.ent.etrs.repaspatient.model.entities.exceptions.RepasConstructionException;
import net.ent.etrs.repaspatient.model.entities.references.ConstantesMetier;
import net.ent.etrs.repaspatient.model.entities.references.RegimeAlimentaire;
import net.ent.etrs.repaspatient.model.entities.references.TypeRepas;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Repas {
    private String id;
    private LocalDate dateRepas;
    private TypeRepas typeRepas;
    private List<RegimeAlimentaire> lstRegimeRepas;

    protected Repas(LocalDate dateRepas, TypeRepas typeRepas) {
        this.setDateRepas(dateRepas);
        this.setTypeRepas(typeRepas);
        this.lstRegimeRepas = new ArrayList<>();
        this.id = UUID.randomUUID().toString();
    }

    //Accesseurs


    public String getId() {
        return id;
    }

    public LocalDate getDateRepas() {
        return dateRepas;
    }

    public void setDateRepas(LocalDate dateRepas) {
        this.dateRepas = dateRepas;
    }

    public TypeRepas getTypeRepas() {
        return typeRepas;
    }

    public void setTypeRepas(TypeRepas typeRepas) {
        this.typeRepas = typeRepas;
    }

    public List<RegimeAlimentaire> getLstRegimeRepas() {
        return lstRegimeRepas;
    }

    //equals/hashcode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Repas)) return false;
        Repas repas = (Repas) o;
        return Objects.equals(getId(), repas.getId()) && Objects.equals(getDateRepas(), repas.getDateRepas()) && getTypeRepas() == repas.getTypeRepas() && Objects.equals(getLstRegimeRepas(), repas.getLstRegimeRepas());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDateRepas(), getTypeRepas(), getLstRegimeRepas());
    }

    //autres méthode
    public void ajouterRegimeAlimentaire(RegimeAlimentaire regimeAlimentaire) throws RepasConstructionException {
        if (this.lstRegimeRepas.contains(regimeAlimentaire)) {
            throw new RepasConstructionException(ConstantesMetier.MSG_PATIENT_REGIME_ALIMENTAIRE_EXIST_EXCEPTION);
        }
        this.lstRegimeRepas.add(regimeAlimentaire);
    }
}
