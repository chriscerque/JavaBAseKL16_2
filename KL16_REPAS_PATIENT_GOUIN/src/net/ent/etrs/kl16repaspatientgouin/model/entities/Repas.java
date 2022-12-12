package net.ent.etrs.kl16repaspatientgouin.model.entities;


import net.ent.etrs.kl16repaspatientgouin.model.entities.exceptions.RegimeAlimentaireException;
import net.ent.etrs.kl16repaspatientgouin.model.entities.exceptions.RepasException;
import net.ent.etrs.kl16repaspatientgouin.model.entities.references.ConstantesMetier;
import net.ent.etrs.kl16repaspatientgouin.model.entities.references.RegimeAlimentaire;
import net.ent.etrs.kl16repaspatientgouin.model.entities.references.TypeRepas;

import java.sql.ClientInfoStatus;
import java.time.LocalDate;
import java.util.*;

public class Repas {

    //region  Attributs

    private UUID id;

    private LocalDate dateRepas;

    private TypeRepas typeRepas;

    private List<RegimeAlimentaire> lstRegimeAlimentaire;


    //endregion

    //region  Constructeurs

    protected Repas(final LocalDate dateRepas,final  TypeRepas typeRepas) throws RepasException {
        this.setDateRepas(dateRepas);
        this.setTypeRepas(typeRepas);
        id = UUID.randomUUID();
        lstRegimeAlimentaire = new ArrayList<>();
    }


    //endregion


    //region  Getter & Setter

    public String getId() {
        return this.id.toString();
    }

    public LocalDate getDateRepas() {
        return this.dateRepas;
    }

    public void setDateRepas(final LocalDate dateRepas) throws RepasException {
        if(dateRepas == null || dateRepas.isBefore(LocalDate.now())){
            throw new RepasException(ConstantesMetier.REPAS_DATE_EXCEPTION);
        }
        this.dateRepas = dateRepas;
    }

    public TypeRepas getTypeRepas() {
        return this.typeRepas;
    }

    public void setTypeRepas(final TypeRepas typeRepas) throws RepasException {
        if(typeRepas == null || TypeRepas.values()[0].equals(typeRepas)
                || TypeRepas.values()[1].equals(typeRepas) || TypeRepas.values()[2].equals(typeRepas)){
            throw new RepasException(ConstantesMetier.REPAS_TYPE_REPAS_EXCEPTION);
        }
        this.typeRepas = typeRepas;
    }

    public List<RegimeAlimentaire> getLstRegimeAlimentaire() {
        return Collections.unmodifiableList(this.lstRegimeAlimentaire);
    }

    //endregion

    //region  Méthodes

    public void ajouterRegimeAlimentaire(final RegimeAlimentaire regime) throws RepasException {

        try {
            if(regime == null){
                throw new RepasException(ConstantesMetier.MSG_PATIENT_REGIME_ALIMENTAIRE_EXCEPTION);
            }
            //je test si elle existe en regardant si je peux recuperer son libelle Ps j'ai lipression que c'est pas ouf
            RegimeAlimentaire.getBylibelle(regime.getLibelle());
            if(this.lstRegimeAlimentaire.contains(regime)){
                throw new RepasException(ConstantesMetier.MSG_PATIENT_REGIME_ALIMENTAIRE_EXIST_EXCEPTION);
            }
            this.lstRegimeAlimentaire.add(regime);
        } catch (RegimeAlimentaireException e) {
            throw new RepasException(e);
        }

    }





    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Repas)) return false;
        Repas repas = (Repas) o;
        return getDateRepas().equals(repas.getDateRepas()) && getTypeRepas() == repas.getTypeRepas();
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
                '}';
    }

    //endregion
}
