package net.ent.etrs.repaspatient.model.entities;

import net.ent.etrs.repaspatient.model.entities.exceptions.RepasException;
import net.ent.etrs.repaspatient.model.entities.references.ConstantesMetier;
import net.ent.etrs.repaspatient.model.entities.references.RegimeAlimentaire;
import net.ent.etrs.repaspatient.model.entities.references.TypeRepas;

import java.time.LocalDate;
import java.util.*;

public class Repas {
    //region ATTRIBUTS
    private final String id = UUID.randomUUID().toString();
    private LocalDate dateRepas;
    private TypeRepas typeRepas;
    private List<RegimeAlimentaire> lstRegimeAlimentaire = new ArrayList<>();
    //endregion
    //region CONSTRUCTEUR(S)

    protected Repas(final LocalDate dateRepas, final TypeRepas typeRepas) throws RepasException {
        this.setDateRepas(dateRepas);
        this.setTypeRepas(typeRepas);
    }

    //endregion
    //region GETTER SETTER

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

    public List<RegimeAlimentaire> getLstRegimeAlimentaire() {
        return Collections.unmodifiableList(this.lstRegimeAlimentaire);
    }

    public void ajouterRegimeAlimentaire(final RegimeAlimentaire regimeAlimentaire) throws RepasException {
        if (Objects.isNull(regimeAlimentaire)) {
            throw new RepasException(ConstantesMetier.MSG_PATIENT_REGIME_ALIMENTAIRE_EXCEPTION);
        }
        if (this.lstRegimeAlimentaire.contains(regimeAlimentaire)) {
            throw new RepasException(ConstantesMetier.MSG_PATIENT_REGIME_ALIMENTAIRE_EXIST_EXCEPTION);
        }
        this.lstRegimeAlimentaire.add(regimeAlimentaire);
    }
    //endregion
    //region Equals / HashCode

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

    //endregion
    //region MÉTHODES
    @Override
    public String toString() {
        return "Repas{" +
                "id='" + id + '\'' +
                ", dateRepas=" + dateRepas +
                ", typeRepas=" + typeRepas +
                '}';
    }


    //endregion
}
