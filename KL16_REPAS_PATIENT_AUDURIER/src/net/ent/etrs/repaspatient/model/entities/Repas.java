package net.ent.etrs.repaspatient.model.entities;

import net.ent.etrs.repaspatient.model.daos.Identifiable;
import net.ent.etrs.repaspatient.model.entities.exceptions.RepasException;
import net.ent.etrs.repaspatient.model.entities.references.ConstantesMetier;
import net.ent.etrs.repaspatient.model.entities.references.RegimeAlimentaire;
import net.ent.etrs.repaspatient.model.entities.references.TypeRepas;

import java.time.LocalDate;
import java.util.*;

public class Repas implements Identifiable<String> {
    //Attributs
    private String id;
    private LocalDate dateRepas;
    private TypeRepas typeRepas;
    private List<RegimeAlimentaire> lstRegimeAlimentaire;
    //Constructor

    protected Repas(final LocalDate dateRepas, final TypeRepas typeRepas) throws RepasException {
        try {
            this.setDateRepas(dateRepas);
            this.setTypeRepas(typeRepas);
        } catch (RepasException e) {
            throw new RepasException(ConstantesMetier.REPAS_CONSTRUCTION_EXCEPTION);
        }
        this.id = UUID.randomUUID().toString();
        this.lstRegimeAlimentaire = new ArrayList<>();
    }

    //Getter/Setter

    @Override
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
        return Collections.unmodifiableList(lstRegimeAlimentaire);
    }

    public void ajouterRegimeAlimentaire(RegimeAlimentaire regimeAlimentaire) throws RepasException {
        if (regimeAlimentaire == null) {
            throw new RepasException(ConstantesMetier.REPAS_REGIME_ALIMENATIRE_EXCEPTION);
        }
        lstRegimeAlimentaire.add(regimeAlimentaire);
    }

    //Other methods

    //Equals/Hashcode

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


    //To String

    @Override
    public String toString() {
        return "Repas{" +
                "id='" + id + '\'' +
                ", dateRepas=" + dateRepas +
                ", typeRepas=" + typeRepas +
                '}';
    }
}
