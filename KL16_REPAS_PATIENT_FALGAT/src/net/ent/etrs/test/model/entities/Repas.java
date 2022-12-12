package net.ent.etrs.test.model.entities;

import net.ent.etrs.test.model.entities.exception.PatientException;
import net.ent.etrs.test.model.entities.exception.RepasException;
import net.ent.etrs.test.model.entities.references.ConstantesMetier;
import net.ent.etrs.test.model.entities.references.RegimeAlimentaire;
import net.ent.etrs.test.model.entities.references.TypeRepas;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Repas {

    private final String id = UUID.randomUUID().toString();

    private LocalDate dateRepas;

    private TypeRepas typeRepas;

    private List<RegimeAlimentaire> lstRegimeAlimentaire = new ArrayList<>();


    /**
     * Constructeur de la classe Repas
     * @param dateRepas
     * @param typeRepas
     */
    protected Repas(final LocalDate dateRepas, final TypeRepas typeRepas) throws RepasException {
        this.setDateRepas(dateRepas);
        this.setTypeRepas(typeRepas);
    }

    //Getter & Setter
    public String getId() {
        return this.id;
    }

    public List<RegimeAlimentaire> getLstRegimeAlimentaire() {
        return lstRegimeAlimentaire;
    }

    public LocalDate getDateRepas() {
        return this.dateRepas;
    }

    public void setDateRepas(final LocalDate dateRepas) throws RepasException {
        if(dateRepas == null){
            throw new RepasException(ConstantesMetier.REPAS_DATE_EXCEPTION);
        }
        this.dateRepas = dateRepas;
    }

    public TypeRepas getTypeRepas() {
        return this.typeRepas;
    }

    public void setTypeRepas(final TypeRepas typeRepas) throws RepasException {
        if(typeRepas == null){
            throw new RepasException(ConstantesMetier.REPAS_TYPE_REPAS_EXCEPTION);
        }
        this.typeRepas = typeRepas;
    }

    //Equals / HashCode / ToString


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
                "id='" + id + '\'' +
                ", dateRepas=" + dateRepas +
                ", typeRepas=" + typeRepas +
                ", lstRegimeAlimentaire=" + lstRegimeAlimentaire +
                '}';
    }


    //Autres méthodes

    /**
     * Ajoute un régime alimentaire à un repas
     * @param regimeAlimentaire
     * @throws RepasException
     */
    public void ajouterRegimeAlimentaire(final RegimeAlimentaire regimeAlimentaire) throws RepasException {
        if(regimeAlimentaire == null){
            throw new RepasException(ConstantesMetier.MSG_PATIENT_REGIME_ALIMENTAIRE_EXCEPTION);
        }
        if(this.getLstRegimeAlimentaire().contains(regimeAlimentaire)){
            throw new RepasException(ConstantesMetier.MSG_PATIENT_REGIME_ALIMENTAIRE_EXIST_EXCEPTION);
        }
        this.getLstRegimeAlimentaire().add(regimeAlimentaire);
    }

}
