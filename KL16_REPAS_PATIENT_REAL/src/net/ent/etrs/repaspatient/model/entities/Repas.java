package net.ent.etrs.repaspatient.model.entities;

import net.ent.etrs.repaspatient.model.entities.references.TypeRepas;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Repas {

    // Constantes
    private final UUID id;
    private LocalDate dateRepas;
    private TypeRepas typeRepas;
    private List<RegimeAlimentaire> lstRegimeAlimentaire = new ArrayList<>();

    // Constructeur
    protected Repas(LocalDate dateRepas, TypeRepas typeRepas) {
        this.setDateRepas(dateRepas);
        this.setTypeRepas(typeRepas);
        this.id = UUID.randomUUID();
    }

    public void setTypeRepas(TypeRepas typeRepas) {
        this.typeRepas = typeRepas;
    }

    public LocalDate getDateRepas() {
        return dateRepas;
    }

    public TypeRepas getTypeRepas() {
        return typeRepas;
    }

    public UUID getId() {
        return id;
    }

    public void setDateRepas(LocalDate dateRepas) {
        this.dateRepas = dateRepas;
    }

    public List<RegimeAlimentaire> getLstRegimeAlimentaire() {
        return lstRegimeAlimentaire;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Repas)) return false;
        Repas repas = (Repas) o;
        return Objects.equals(id, repas.id) && Objects.equals(getDateRepas(), repas.getDateRepas()) && typeRepas == repas.typeRepas && Objects.equals(lstRegimeAlimentaire, repas.lstRegimeAlimentaire);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, getDateRepas(), typeRepas, lstRegimeAlimentaire);
    }

    /**
     * Permet d'ajouter un regime alimentaire.
     * @param regimeAlimentaire
     */
    public void ajouterRegimeAlimentaire(RegimeAlimentaire regimeAlimentaire){
        if(!lstRegimeAlimentaire.contains(regimeAlimentaire)){
            this.lstRegimeAlimentaire.add(regimeAlimentaire);
        }
    }


}
