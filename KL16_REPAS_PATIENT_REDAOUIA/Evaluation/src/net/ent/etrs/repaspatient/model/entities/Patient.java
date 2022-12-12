package net.ent.etrs.repaspatient.model.entities;

import net.ent.etrs.repaspatient.model.entities.exceptions.PatientConstructionException;
import net.ent.etrs.repaspatient.model.entities.exceptions.RegimeAlimentaireException;
import net.ent.etrs.repaspatient.model.entities.exceptions.RepasException;
import net.ent.etrs.repaspatient.model.entities.references.ConstantesMetier;
import net.ent.etrs.repaspatient.model.entities.references.RegimeAlimentaire;

import java.time.LocalDate;
import java.util.*;

public class Patient implements Comparable<Patient>{

    private LocalDate dateEntree;

    private List<Repas> lstRepas = new ArrayList<>();

    private List<RegimeAlimentaire> lstRegimeAlimentaire = new ArrayList<>();

    private String id ;

    private String numSecu;

    private String nom;

    private String prenom;

    protected Patient( String numSecu, String nom, String prenom, LocalDate dateEntree) throws PatientConstructionException {
        this.setDateEntree(dateEntree);
        this.setNumSecu(numSecu);
        this.setNom(nom);
        this.setPrenom(prenom);
        this.id = UUID.randomUUID().toString();
    }

    public LocalDate getDateEntree() {
        return dateEntree;
    }

    public void setDateEntree(LocalDate dateEntree) {
        this.dateEntree = dateEntree;
    }

    public List<Repas> getLstRepas() {
        return Collections.unmodifiableList(lstRepas);
    }

    public void ajouterRepas(Repas repas) throws RepasException, RegimeAlimentaireException {
        if (repas == null){
            throw new RepasException(ConstantesMetier.REPAS_VIDE);
        }
       controlerRegimeAlimentaire(repas);
    }

    private void controlerRegimeAlimentaire(Repas repas) throws RepasException, RegimeAlimentaireException {
        if (repas == null){
            throw new RepasException(ConstantesMetier.REPAS_VIDE);
        }

        //TODO ca marche pas
//        for (RegimeAlimentaire ra: repas.getLstRegimeAlimentaire()){
//            if (!lstRegimeAlimentaire.contains(ra)){
//                throw new RegimeAlimentaireException(ConstantesMetier.PATIENT_REPAS_REGIME_ALIMENTAIRE_EXCEPTION);
//            }
//        }
        lstRepas.add(repas);
    }


    public List<RegimeAlimentaire> getLstRegimeAlimentaire() {
        return Collections.unmodifiableList(lstRegimeAlimentaire);
    }

    public void ajouterRegimeAlimentaire(RegimeAlimentaire ra) throws RepasException {
        if (ra == null){
            throw new RepasException(ConstantesMetier.REGIME_ALIMENTAIRE_VIDE);
        }
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumSecu() {
        return numSecu;
    }

    public void setNumSecu(String numSecu) throws PatientConstructionException {
        if (numSecu.length()!=ConstantesMetier.PATIENT_NUM_SECU_TAILLE){
            throw new PatientConstructionException(ConstantesMetier.MSG_PATIENT_NUM_SECU_LENGTH_EXCEPTION);
        }
        this.numSecu = numSecu;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return Objects.equals(getNumSecu(), patient.getNumSecu());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumSecu());
    }

    @Override
    public String toString() {
        return "Patient{" +
                "dateEntree=" + dateEntree +
                ", id='" + id + '\'' +
                ", numSecu='" + numSecu + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                '}';
    }

    @Override
    public int compareTo(Patient o) {
        return this.getNumSecu().compareTo(o.getNumSecu());
    }
}
