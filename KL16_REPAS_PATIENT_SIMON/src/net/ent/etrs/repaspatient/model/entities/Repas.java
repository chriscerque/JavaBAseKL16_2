package net.ent.etrs.repaspatient.model.entities;

import net.ent.etrs.repaspatient.model.entities.exceptions.DateRepasException;
import net.ent.etrs.repaspatient.model.entities.exceptions.PatientException;
import net.ent.etrs.repaspatient.model.entities.exceptions.RepasException;
import net.ent.etrs.repaspatient.model.entities.exceptions.TypeRepasException;
import net.ent.etrs.repaspatient.model.entities.references.ConstantesMetier;
import net.ent.etrs.repaspatient.model.entities.references.RegimeAlimentaire;
import net.ent.etrs.repaspatient.model.entities.references.TypeRepas;

import java.time.LocalDate;
import java.util.*;

public class Repas {
    //Attributs
    private String id ;
    private LocalDate dateRepas;
    private TypeRepas typeRepas;
    private List<RegimeAlimentaire> lstRegimeAlimentaire = new ArrayList();

    //Constructeur

    protected Repas(final LocalDate dateRepas, final TypeRepas typeRepas) throws RepasException {
        try {
            this.setDateRepas(dateRepas);
            this.setTypeRepas(typeRepas);
            this.id = UUID.randomUUID().toString();
        }catch (DateRepasException | TypeRepasException e){
            throw new RepasException(e.getMessage(), e);
        }

    }

    //Accesseurs


    public String getId() {
        return id;
    }

    public LocalDate getDateRepas() {
        return dateRepas;
    }

    public void setDateRepas(final LocalDate dateRepas) throws DateRepasException {
        if (Objects.isNull(dateRepas)){
            throw new DateRepasException(ConstantesMetier.REPAS_DATE_EXCEPTION);
        }
        this.dateRepas = dateRepas;
    }

    public TypeRepas getTypeRepas() {
        return typeRepas;
    }

    public void setTypeRepas(final TypeRepas typeRepas) throws TypeRepasException {
        if (Objects.isNull(typeRepas)){
            throw new TypeRepasException(ConstantesMetier.REPAS_TYPE_REPAS_EXCEPTION);
        }
        this.typeRepas = typeRepas;
    }

    public List<RegimeAlimentaire> getLstRegimeAlimentaire() {
        return Collections.unmodifiableList(lstRegimeAlimentaire);
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
                ", lstRegimeAlimentaire=" + lstRegimeAlimentaire +
                '}';
    }

    public void ajouterRegimeAlimentaire(final RegimeAlimentaire regimeAlimentaire) throws RepasException {
        if (Objects.isNull(regimeAlimentaire)){
            throw new RepasException(ConstantesMetier.PATIENT_REPAS_REGIME_ALIMENTAIRE_EXCEPTION);
        }
        if (this.lstRegimeAlimentaire.contains(regimeAlimentaire)){
            throw new RepasException(ConstantesMetier.MSG_PATIENT_REGIME_ALIMENTAIRE_EXIST_EXCEPTION);
        }
        for (RegimeAlimentaire ra : this.lstRegimeAlimentaire){
            if (ra.getLibelle().equals(regimeAlimentaire.getLibelle())){
                throw new RepasException(ConstantesMetier.PATIENT_REPAS_REGIME_ALIMENTAIRE_NON_COMPATIBLE_EXCEPTION);
            }
        }

    }

    //Autres méthodes
}
