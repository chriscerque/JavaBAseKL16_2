package net.ent.etrs.repaspatient.model.entities;

import net.ent.etrs.repaspatient.model.entities.exceptions.RepasException;
import net.ent.etrs.repaspatient.model.entities.references.ConstantesMetier;
import net.ent.etrs.repaspatient.model.entities.references.RegimeAlimentaire;
import net.ent.etrs.repaspatient.model.entities.references.TypeRepas;

import java.time.LocalDate;
import java.util.*;

public class Repas {

    //attribut(s)
    private final String id;
    private LocalDate dateRepas;
    private TypeRepas typeRepas;
    private List<RegimeAlimentaire> lstRegimeAlimentaires = new ArrayList<>();

    //constructeur(s)

    protected Repas(final LocalDate dateRepas, final TypeRepas typeRepas) throws RepasException {
        this.id = UUID.randomUUID().toString();
        this.setDateRepas(dateRepas);
        this.setTypeRepas(typeRepas);
    }


    //getter et setter

    public String getId() {
        return this.id;
    }

    public LocalDate getDateRepas() {
        return this.dateRepas;
    }

    public void setDateRepas(final LocalDate dateRepas) throws RepasException {
        if (Objects.isNull(dateRepas)) {
            throw new RepasException(ConstantesMetier.REPAS_DATE_EXCEPTION);
        }
        this.dateRepas = dateRepas;
    }

    public TypeRepas getTypeRepas() {
        return this.typeRepas;
    }

    public void setTypeRepas(final TypeRepas typeRepas) throws RepasException {
        if (Objects.isNull(typeRepas)) {
            throw new RepasException(ConstantesMetier.REPAS_TYPE_REPAS_EXCEPTION);
        }
        this.typeRepas = typeRepas;
    }

    public List<RegimeAlimentaire> getLstRegimeAlimentaires() {
        return Collections.unmodifiableList(this.lstRegimeAlimentaires);
    }

    public void ajouterRegimeAlimentaire(final RegimeAlimentaire regimeAlimentaires) throws RepasException {
        if (Objects.isNull(regimeAlimentaires)) {
            throw new RepasException(ConstantesMetier.PATIENT_REPAS_REGIME_ALIMENTAIRE_EXCEPTION);
        }
        if (this.lstRegimeAlimentaires.contains(regimeAlimentaires)) {
            throw new RepasException(ConstantesMetier.PATIENT_REPAS_REGIME_ALIMENTAIRE_EXCEPTION);
        }

        this.lstRegimeAlimentaires.add(regimeAlimentaires);
    }


    //egal et hash

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


    //to string

    @Override
    public String toString() {
        return "Repas{" +
                "id='" + id + '\'' +
                ", dateRepas=" + dateRepas +
                ", typeRepas=" + typeRepas +
                '}';
    }


    //autre(s) methode(s)


}
