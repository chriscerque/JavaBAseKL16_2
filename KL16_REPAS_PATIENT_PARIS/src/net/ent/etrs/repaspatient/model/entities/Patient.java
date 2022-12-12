package net.ent.etrs.repaspatient.model.entities;

import net.ent.etrs.repaspatient.model.entities.exceptions.PatientException;
import net.ent.etrs.repaspatient.model.entities.references.ConstantesMetier;
import net.ent.etrs.repaspatient.model.entities.references.RegimeAlimentaire;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

public class Patient {

    // attributs

    private LocalDate dateEntree;

    private List<Repas> lstRepas = new ArrayList<>();

    private List<RegimeAlimentaire> lstRegimeAlimentaire = new ArrayList<>();

    private UUID id = UUID.randomUUID();

    private String numSecu;

    private String nom;

    private String prenom;

    // constructeur

    protected Patient(LocalDate dateEntree, String numSecu, String nom, String prenom) throws PatientException{
        try {
            this.setDateEntree(dateEntree);
            this.setNumSecu(numSecu);
            this.setNom(nom);
            this.setPrenom(prenom);
        }catch (PatientException e){
            throw new PatientException(ConstantesMetier.MSG_PATIENT_CREATION_EXCEPTION);
        }

    }


    // accesseurs


    public LocalDate getDateEntree() {
        return dateEntree;
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

    public String getNumSecu() {
        return numSecu;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setDateEntree(LocalDate dateEntree) throws PatientException{
        if (LocalDate.now().isBefore(dateEntree)){
            throw new PatientException(ConstantesMetier.MSG_PATIENT_DATE_ENTREE_INVALIDE_EXCEPTION);
        }
        if (Objects.isNull(dateEntree)){
            throw new PatientException(ConstantesMetier.MSG_PATIENT_DATE_ENTREE_EXCEPTION);
        }
        this.dateEntree = dateEntree;
    }

    public void setNumSecu(String numSecu) throws PatientException {
        if (numSecu.length() != ConstantesMetier.PATIENT_NUM_SECU_TAILLE){
            throw new PatientException(ConstantesMetier.MSG_PATIENT_NUM_SECU_LENGTH_EXCEPTION);
        }
        if (Objects.isNull(numSecu)){
            throw new PatientException(ConstantesMetier.MSG_PATIENT_NUM_SECU_EXCEPTION);
        }
        this.numSecu = numSecu;
    }

    public void setNom(String nom) throws PatientException{
        if (Objects.isNull(nom) || nom.length() < ConstantesMetier.PATIENT_NOM_TAILLE_MIN || nom.length() > ConstantesMetier.PATIENT_NOM_TAILLE_MAX){
            throw new PatientException(ConstantesMetier.MSG_PATIENT_NOM_LENGTH_EXCEPTION);
        }
        this.nom = nom;
    }

    public void setPrenom(String prenom) throws PatientException{
        if (Objects.isNull(prenom) || prenom.length() < ConstantesMetier.PATIENT_PRENOM_TAILLE_MAX || prenom.length() > ConstantesMetier.PATIENT_PRENOM_TAILLE_MAX){
            throw new PatientException(ConstantesMetier.MSG_PATIENT_PRENOM_LENGTH_EXCEPTION);
        }
        this.prenom = prenom;
    }

    // autres méthodes

    private void controlerRegimeAlimentaire(Repas repas){

    }

    public void ajouterRepas(Repas repas) throws PatientException{
        if (Objects.isNull(repas)){
            throw new PatientException(ConstantesMetier.PATIENT_REPAS_REGIME_ALIMENTAIRE_EXCEPTION);
        }
        this.lstRepas.add(repas);

    }

    public void ajouterRegimeAlimentaire(RegimeAlimentaire regimeAlimentaire) throws PatientException{
        if (Objects.isNull(regimeAlimentaire)){
            throw new PatientException(ConstantesMetier.MSG_PATIENT_REGIME_ALIMENTAIRE_EXCEPTION);
        }
        if (this.lstRegimeAlimentaire.contains(regimeAlimentaire)){
            throw new PatientException(ConstantesMetier.MSG_PATIENT_REGIME_ALIMENTAIRE_EXIST_EXCEPTION);
        }
        this.lstRegimeAlimentaire.add(regimeAlimentaire);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Patient)) return false;
        Patient patient = (Patient) o;
        return Objects.equals(getDateEntree(), patient.getDateEntree()) && Objects.equals(getLstRepas(), patient.getLstRepas()) && Objects.equals(getLstRegimeAlimentaire(), patient.getLstRegimeAlimentaire()) && Objects.equals(getId(), patient.getId()) && Objects.equals(getNumSecu(), patient.getNumSecu()) && Objects.equals(getNom(), patient.getNom()) && Objects.equals(getPrenom(), patient.getPrenom());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDateEntree(), getLstRepas(), getLstRegimeAlimentaire(), getId(), getNumSecu(), getNom(), getPrenom());
    }

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
}
