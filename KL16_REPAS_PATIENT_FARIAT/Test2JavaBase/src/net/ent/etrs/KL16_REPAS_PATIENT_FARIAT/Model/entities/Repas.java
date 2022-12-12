package net.ent.etrs.KL16_REPAS_PATIENT_FARIAT.Model.entities;

import net.ent.etrs.KL16_REPAS_PATIENT_FARIAT.Model.entities.excetions.RepasException;
import net.ent.etrs.KL16_REPAS_PATIENT_FARIAT.Model.entities.references.ConstantesMetier;
import net.ent.etrs.KL16_REPAS_PATIENT_FARIAT.Model.entities.references.RegimeAlimentaire;
import net.ent.etrs.KL16_REPAS_PATIENT_FARIAT.Model.entities.references.TypeRepas;

import java.time.LocalDate;
import java.util.*;

public class Repas {
    //ATTRIBUTS
    private UUID id = UUID.randomUUID();
    private LocalDate dateRepas;
    private TypeRepas typeRepas;
    private List<RegimeAlimentaire> lstRegimeAlimentaire = new ArrayList<>();

    //CONSTRUCTEUR

    protected Repas(final LocalDate dateRepas, final TypeRepas typeRepas) throws RepasException {
        this.setDateRepas(dateRepas);
        this.setTypeRepas(typeRepas);
    }


    //GETTER SETTER

    public UUID getId() {
        return this.id;
    }

    public void setId(final UUID id) {
        this.id = id;
    }

    public LocalDate getDateRepas() {
        return this.dateRepas;
    }

    public void setDateRepas(final LocalDate dateRepas) throws RepasException {
        if (dateRepas == null || dateRepas.isBefore(LocalDate.now())) {
            throw new RepasException(ConstantesMetier.REPAS_DATE_EXCEPTION);
        }

        this.dateRepas = dateRepas;
    }

    public TypeRepas getTypeRepas() {

        return this.typeRepas;
    }

    public void setTypeRepas(final TypeRepas typeRepas) throws RepasException {
        if (typeRepas == null) {
            throw new RepasException(ConstantesMetier.TYPE_REPAS_NULL);
        }
        this.typeRepas = typeRepas;
    }

    public List<RegimeAlimentaire> getLstRegimeAlimentaire() {
        return Collections.unmodifiableList(this.lstRegimeAlimentaire);
    }

    public void ajouterRegimeAlimentaire(RegimeAlimentaire regimeAlimentaire) throws RepasException {
        if (regimeAlimentaire == null) {
            throw new RepasException(ConstantesMetier.AJOUT_REGIME_ALIMENTAIRE_NULL);
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
                "id=" + id +
                ", dateRepas=" + dateRepas +
                ", typeRepas=" + typeRepas +
                ", lstRegimeAlimentaire=" + lstRegimeAlimentaire +
                '}';
    }
}
