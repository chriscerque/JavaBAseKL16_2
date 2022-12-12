package net.ent.etrs.repaspatient.model.entities.references;

import java.time.LocalDate;
import java.util.*;

/**
 * The type Patient.
 */
public class Patient {

    //------------------------------------------------
    //          ATTRIBUTS
    //------------------------------------------------

    private LocalDate dateEntree;

    private List<Repas> lstRepas = new ArrayList<>();

    private List<RegimeAlimentaire> lstRegimeAlimentaire = new ArrayList<>();

    private final UUID id;

    private String numSecu;

    private String nom;

    private String prenom;

    //------------------------------------------------
    //          Constructeur
    //------------------------------------------------


    /**
     * Instantiates a new Patient.
     *
     * @param dateEntree the date entree
     * @param id         the id
     * @param numSecu    the num secu
     * @param nom        the nom
     */
    protected Patient(LocalDate dateEntree, UUID id, String numSecu, String nom) {
        this.dateEntree = dateEntree;
        this.id = id;
        this.numSecu = numSecu;
        this.nom = nom;
    }

    //------------------------------------------------
    //          Getter & Setter
    //------------------------------------------------


    /**
     * Gets date entree.
     *
     * @return the date entree
     */
    public LocalDate getDateEntree() {
        return dateEntree;
    }

    /**
     * Sets date entree.
     *
     * @param dateEntree the date entree
     */
    public void setDateEntree(LocalDate dateEntree) {
        this.dateEntree = dateEntree;
    }

    /**
     * Gets num secu.
     *
     * @return the num secu
     */
    public String getNumSecu() {
        return numSecu;
    }

    /**
     * Sets num secu.
     *
     * @param numSecu the num secu
     */
    public void setNumSecu(String numSecu) {
        this.numSecu = numSecu;
    }

    /**
     * Gets nom.
     *
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Sets nom.
     *
     * @param nom the nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Gets prenom.
     *
     * @return the prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Sets prenom.
     *
     * @param prenom the prenom
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public UUID getId() {
        return id;
    }

    /**
     * Gets lst repas.
     *
     * @return the lst repas
     */
    public List<Repas> getLstRepas() {
        return lstRepas;
    }

    /**
     * Gets lst regime alimentaire.
     *
     * @return the lst regime alimentaire
     */
    public List<RegimeAlimentaire> getLstRegimeAlimentaire() {
        return lstRegimeAlimentaire;
    }

    //------------------------------------------------
    //          Equals & Hascode
    //------------------------------------------------


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return Objects.equals(dateEntree, patient.dateEntree) && Objects.equals(lstRepas, patient.lstRepas) && Objects.equals(lstRegimeAlimentaire, patient.lstRegimeAlimentaire) && Objects.equals(id, patient.id) && Objects.equals(numSecu, patient.numSecu) && Objects.equals(nom, patient.nom) && Objects.equals(prenom, patient.prenom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateEntree, lstRepas, lstRegimeAlimentaire, id, numSecu, nom, prenom);
    }

    //------------------------------------------------
    //          To String
    //------------------------------------------------

    @Override
    public String toString() {
        return "Patient{" +
                "dateEntree=" + dateEntree +
                ", lstRepas=" + lstRepas +
                ", lstRegimeAlimentaire=" + lstRegimeAlimentaire +
                ", id=" + id +
                ", numSecu='" + numSecu + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                '}';
    }


    private void controlerRegimeAlimentaire(Repas repas, Integer leNombre) {

    }

    /**
     * Ajouter repas.
     *
     * @param repas the repas
     */
    public void ajouterRepas (Repas repas) {

    }

    /**
     * Ajouter regime alimentaire.
     *
     * @param regimeAlimentaire the regime alimentaire
     */
    public void ajouterRegimeAlimentaire (RegimeAlimentaire regimeAlimentaire) {

    }

}
