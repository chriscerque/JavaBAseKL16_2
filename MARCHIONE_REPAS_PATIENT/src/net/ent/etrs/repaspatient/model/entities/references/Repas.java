package net.ent.etrs.repaspatient.model.entities.references;

import java.util.*;

/**
 * The type Repas.
 */
public class Repas {

    //------------------------------------------------
    //          ATTRIBUTS
    //------------------------------------------------

    private final UUID id;

    private Locale dateRepas;

    private TypeRepas typeRepas;

    private List<RegimeAlimentaire> lstRegimeAlimentaire = new ArrayList<>();

    //------------------------------------------------
    //          Constructeur
    //------------------------------------------------

    /**
     * Instantiates a new Repas.
     *
     * @param id        the id
     * @param dateRepas the date repas
     * @param typeRepas the type repas
     */
    protected Repas(UUID id, Locale dateRepas, TypeRepas typeRepas) {
        this.id = id;
        this.dateRepas = dateRepas;
        this.typeRepas = typeRepas;
    }

    //------------------------------------------------
    //          Getter & Setter
    //------------------------------------------------

    /**
     * Gets type repas.
     *
     * @return the type repas
     */
    public TypeRepas getTypeRepas() {
        return typeRepas;
    }

    /**
     * Sets type repas.
     *
     * @param typeRepas the type repas
     */
    public void setTypeRepas(TypeRepas typeRepas) {
        this.typeRepas = typeRepas;
    }

    /**
     * Gets date repas.
     *
     * @return the date repas
     */
    public Locale getDateRepas() {
        return dateRepas;
    }

    /**
     * Sets date repas.
     *
     * @param dateRepas the date repas
     */
    public void setDateRepas(Locale dateRepas) {
        this.dateRepas = dateRepas;
    }

    /**
     * Gets lst regime alimentaire.
     *
     * @return the lst regime alimentaire
     */
    public List<RegimeAlimentaire> getLstRegimeAlimentaire() {
        return lstRegimeAlimentaire;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public UUID getId() {
        return id;
    }

    //------------------------------------------------
    //          Equals & Hascode
    //------------------------------------------------


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

    //------------------------------------------------
    //          to String
    //------------------------------------------------

    @Override
    public String toString() {
        return "Repas{" +
                "id=" + id +
                ", dateRepas=" + dateRepas +
                ", typeRepas=" + typeRepas +
                ", lstRegimeAlimentaire=" + lstRegimeAlimentaire +
                '}';
    }

    /**
     * Ajouter regimealimentaire.
     *
     * @param regimeAlimentaire the regime alimentaire
     */
    public void ajouterRegimealimentaire(RegimeAlimentaire regimeAlimentaire) {

    }

}
