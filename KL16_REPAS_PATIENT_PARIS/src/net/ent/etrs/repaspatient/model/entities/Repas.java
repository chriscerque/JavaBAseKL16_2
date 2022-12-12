package net.ent.etrs.repaspatient.model.entities;

import net.ent.etrs.repaspatient.model.entities.exceptions.PatientException;
import net.ent.etrs.repaspatient.model.entities.exceptions.RegimeAlimentaireException;
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

    // attributs

    private UUID id = UUID.randomUUID();

    private LocalDate dateRepas;

    private TypeRepas typeRepas;

    private List<RegimeAlimentaire> lstRegimeAlimentaire = new ArrayList<>();

    // constructeur

    protected Repas(LocalDate dateRepas, TypeRepas typeRepas) {
        this.setDateRepas(dateRepas);
        this.setTypeRepas(typeRepas);
    }


    // accesseurs


    public UUID getId() {
        return id;
    }

    public LocalDate getDateRepas() {
        return dateRepas;
    }

    public TypeRepas getTypeRepas() {
        return typeRepas;
    }

    public List<RegimeAlimentaire> getLstRegimeAlimentaire() {
        return lstRegimeAlimentaire;
    }

    public void setDateRepas(LocalDate dateRepas) {
        this.dateRepas = dateRepas;
    }

    public void setTypeRepas(TypeRepas typeRepas) {
        this.typeRepas = typeRepas;
    }

    // autres méthodes

    public void ajouterRegimeAlimentaire(RegimeAlimentaire regimeAlimentaire) throws RegimeAlimentaireException {
        if (Objects.isNull(regimeAlimentaire)){
            throw new RegimeAlimentaireException(ConstantesMetier.MSG_PATIENT_REGIME_ALIMENTAIRE_EXCEPTION);
        }
        if (this.lstRegimeAlimentaire.contains(regimeAlimentaire)){
            throw new RegimeAlimentaireException(ConstantesMetier.MSG_PATIENT_REGIME_ALIMENTAIRE_EXIST_EXCEPTION);
        }
        this.lstRegimeAlimentaire.add(regimeAlimentaire);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Repas)) return false;
        Repas repas = (Repas) o;
        return Objects.equals(getId(), repas.getId()) && Objects.equals(getDateRepas(), repas.getDateRepas()) && getTypeRepas() == repas.getTypeRepas() && Objects.equals(getLstRegimeAlimentaire(), repas.getLstRegimeAlimentaire());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDateRepas(), getTypeRepas(), getLstRegimeAlimentaire());
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
