package net.ent.etrs.repaspatient.model.entities;

import net.ent.etrs.repaspatient.model.entities.exceptions.RepasException;
import net.ent.etrs.repaspatient.model.entities.references.RegimeAlimentaire;
import net.ent.etrs.repaspatient.model.entities.references.TypeRepas;

import java.time.LocalDate;
import java.util.*;

public class Repas {

    //ATTRIBUTS
    private String id = UUID.randomUUID().toString();
    private LocalDate dateRepas;
    private TypeRepas typeRepas;
    private Set<RegimeAlimentaire> lstRegimeALimentaire = new HashSet<>();

    //CONSTRUCTEUR
    public Repas(LocalDate dateRepas, TypeRepas typeRepas) throws RepasException {
        this.setDateRepas(dateRepas);
        this.setTypeRepas(typeRepas);
    }


    //GETTER & SETTER

    public LocalDate getDateRepas() {
        return this.dateRepas;
    }

    public void setDateRepas(LocalDate dateRepas) throws RepasException {
        if (Objects.isNull(this.dateRepas)){
            throw new RepasException(ConstanteModel.DATE_REPAS_NULL_EXCEPTION);
        }
        this.dateRepas = dateRepas;
    }

    public TypeRepas getTypeRepas() {
        return this.typeRepas;
    }

    public void setTypeRepas(TypeRepas typeRepas) throws RepasException {
        if (Objects.isNull(this.typeRepas)){
            throw new RepasException(ConstanteModel.TYPE_REPAS_NULL_EXCEPTION);
        }
        this.typeRepas = typeRepas;
    }

    public String getId() {
        return this.id;
    }

    public Set<RegimeAlimentaire> getLstRegimeALimentaire() {
        return Collections.unmodifiableSet(lstRegimeALimentaire);
    }

    //AUTRES METHODES

    /**
     * ajoute un regime alimentaire à la liste
     * @param regimeAlimentaire
     */
    public void ajouterRegimeAlimentaire(RegimeAlimentaire regimeAlimentaire){
        lstRegimeALimentaire.add(regimeAlimentaire);
    }

    //EQUALS & HASHCODE + TOSTRING
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Repas repas = (Repas) o;
        return Objects.equals(id, repas.id) && Objects.equals(dateRepas, repas.dateRepas) && typeRepas == repas.typeRepas && Objects.equals(lstRegimeALimentaire, repas.lstRegimeALimentaire);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateRepas, typeRepas, lstRegimeALimentaire);
    }

    @Override
    public String toString() {
        return "Repas{" +
                "id='" + id + '\'' +
                ", dateRepas=" + dateRepas +
                ", typeRepas=" + typeRepas +
                ", lstRegimeALimentaire=" + lstRegimeALimentaire +
                '}';
    }
}
