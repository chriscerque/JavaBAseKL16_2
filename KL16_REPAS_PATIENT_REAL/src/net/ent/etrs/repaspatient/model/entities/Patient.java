package net.ent.etrs.repaspatient.model.entities;

import net.ent.etrs.repaspatient.commons.utils.AffichageConsole;
import net.ent.etrs.repaspatient.model.entities.exceptions.PatientException;
import net.ent.etrs.repaspatient.model.entities.exceptions.RegimeAlimentaireException;
import net.ent.etrs.repaspatient.model.entities.references.ConstantesMetier;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Patient implements Comparable<Patient>{

    // Constantes
    private LocalDate dateEntree;
    private List<Repas> lstRepas = new ArrayList<>();
    private List<RegimeAlimentaire> lstRegimeAlimentaire = new ArrayList<>();
    private final UUID id = UUID.randomUUID();
    private String numSecu;
    private String nom;
    private String prenom;

    // Constructeurs
    protected Patient(String numSecu, String nom, String prenom, LocalDate dateEntree) {
        this.setNumSecu(numSecu);
        this.setNom(nom);
        this.setDateEntree(dateEntree);
        this.setPrenom(prenom);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Patient)) return false;
        Patient patient = (Patient) o;
        return Objects.equals(dateEntree, patient.dateEntree) && Objects.equals(lstRepas, patient.lstRepas) && Objects.equals(lstRegimeAlimentaire, patient.lstRegimeAlimentaire) && Objects.equals(id, patient.id) && Objects.equals(numSecu, patient.numSecu) && Objects.equals(nom, patient.nom) && Objects.equals(prenom, patient.prenom);
    }

    public LocalDate getDateEntree() {
        return dateEntree;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNumSecu() {
        return numSecu;
    }

    public void setNumSecu(String numSecu) {
        this.numSecu = numSecu;
    }

    public List<Repas> getLstRepas() {
        return lstRepas;
    }

    public List<RegimeAlimentaire> getLstRegimeAlimentaire() {
        return lstRegimeAlimentaire;
    }

    public UUID getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setDateEntree(LocalDate dateEntree) {
        this.dateEntree = dateEntree;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateEntree, lstRepas, lstRegimeAlimentaire, id, numSecu, nom, prenom);
    }


    private void controlerRegimeAlimentaire(Repas r) throws PatientException {
        if(!lstRepas.contains(r)){
            throw new PatientException(ConstantesMetier.PATIENT_REPAS_REGIME_ALIMENTAIRE_EXCEPTION);
        } else {
            AffichageConsole.afficherMessageAvecSautLigne(String.format("Le regime alimentaire est: %s",r.getTypeRepas()));
        }
    }

    /**
     * Permet d'ajouter un repas a un patient.
     * @param r
     */
    public void ajouterRepas(Repas r) {
        if(!lstRepas.contains(r)){
            this.lstRepas.add(r);
        }
    }

    /**
     * Permet d'ajouter un régime alimentaire.
     * @param regimeAlimentaire
     */

    public void ajouterRegimeAlimentaire(RegimeAlimentaire regimeAlimentaire){
        if(!lstRegimeAlimentaire.contains(regimeAlimentaire)) {
            this.lstRegimeAlimentaire.add(regimeAlimentaire);
        }
    }

    @Override
    public int compareTo(Patient o) {
        return this.getNom().compareTo(o.getNom());
    }
}
