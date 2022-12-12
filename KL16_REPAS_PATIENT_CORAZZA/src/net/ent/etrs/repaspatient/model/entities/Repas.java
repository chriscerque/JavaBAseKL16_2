package net.ent.etrs.repaspatient.model.entities;

import net.ent.etrs.repaspatient.model.entities.exceptions.RepasException;
import net.ent.etrs.repaspatient.model.entities.references.ConstantesMetier;
import net.ent.etrs.repaspatient.model.entities.references.RegimeAlimentaire;
import net.ent.etrs.repaspatient.model.entities.references.TypeRepas;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Repas {

    private String id;
    private LocalDate dateRepas;
    private TypeRepas typeRepas;
    private List<RegimeAlimentaire> lstRegimeAlimentaire;

    // CONSTRUCTOR
    protected Repas(final LocalDate dateRepas, final TypeRepas typeRepas) {
        this.dateRepas = dateRepas;
        this.typeRepas = typeRepas;
        this.id = String.valueOf(UUID.randomUUID());
    }


    // GETTERS
    public String getId() {
        return this.id;
    }
    public LocalDate getDateRepas() {
        return this.dateRepas;
    }
    public TypeRepas getTypeRepas() {
        return this.typeRepas;
    }
    public List<RegimeAlimentaire> getLstRegimeAlimentaire() {
        return this.lstRegimeAlimentaire;
    }

    // SETTERS
    public void setDateRepas(LocalDate dateRepas) throws RepasException {
        if (Objects.isNull(dateRepas)) {
            throw new RepasException(ConstantesMetier.REPAS_DATE_EXCEPTION);
        }
        this.dateRepas = dateRepas;
    }
    public void setTypeRepas(TypeRepas typeRepas) throws RepasException {
        if (Objects.isNull(typeRepas)) {
           throw new RepasException(ConstantesMetier.REPAS_TYPE_REPAS_EXCEPTION);
        }
        this.typeRepas = typeRepas;
    }

    public void ajouterRegimeAlimentaire(RegimeAlimentaire ra) throws RepasException {
        if (Objects.isNull(ra) | !existRegime(ra)){
            throw new RepasException(ConstantesMetier.REPAS_LISTE_REPAS_EXCEPTION);
        }
        lstRegimeAlimentaire.add(ra);
    }


    /**
     * Vérifie si le régime existe déja dans la liste.
     * @param ra
     * @return boolean
     */
    private boolean existRegime(final RegimeAlimentaire ra){
        //TODO
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Repas repas = (Repas) o;
        return Objects.equals(id, repas.id) && Objects.equals(dateRepas, repas.dateRepas) && typeRepas == repas.typeRepas && Objects.equals(lstRegimeAlimentaire, repas.lstRegimeAlimentaire);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateRepas, typeRepas, lstRegimeAlimentaire);
    }

    @Override
    public String toString() {
        return "Repas{" +
                "id='" + id + '\'' +
                ", dateRepas=" + dateRepas +
                ", typeRepas=" + typeRepas +
                '}';
    }
}
