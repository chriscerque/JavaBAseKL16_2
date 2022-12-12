package net.ent.etrs.kl16repaspatientgouin.model.entities;

import net.ent.etrs.kl16repaspatientgouin.model.entities.exceptions.PatientException;
import net.ent.etrs.kl16repaspatientgouin.model.entities.references.ConstantesMetier;
import net.ent.etrs.kl16repaspatientgouin.model.entities.references.RegimeAlimentaire;

import java.time.LocalDate;
import java.util.*;

public class Patient implements Comparable<Patient>{

    //region  Attributs
    private LocalDate dateEntree;
    private List<Repas> lstRepas;
    private List<RegimeAlimentaire> lstRegimeAlimentaire;
    private UUID id;
    private String numSecu;
    private String nom;
    private String prenom;
    //endregion

    //region  Constructeurs

    protected Patient(final  String numSecu,final  String nom,final  String prenom, final LocalDate dateEntree) throws PatientException {

        this.setDateEntree(dateEntree);
        this.setNumSecu(numSecu);
        this.setNom(nom);
        this.setPrenom(prenom);

        this.id = UUID.randomUUID();
        this.lstRepas = new ArrayList<>();
        this.lstRegimeAlimentaire = new ArrayList<>();
    }


    //endregion


    //region  Getter & Setter

    public LocalDate getDateEntree() {
        return this.dateEntree;
    }

    public List<Repas> getLstRepas() {
        return Collections.unmodifiableList(this.lstRepas);
    }

    public List<RegimeAlimentaire> getLstRegimeAlimentaire() {
        return Collections.unmodifiableList(this.lstRegimeAlimentaire);
    }

    public String getId() {
        return this.id.toString();
    }

    public String getNumSecu() {
        return this.numSecu;
    }

    public String getNom() {
        return this.nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public void setDateEntree(final LocalDate dateEntree) throws PatientException {
        if(dateEntree == null){
            throw new PatientException(ConstantesMetier.MSG_PATIENT_DATE_ENTREE_EXCEPTION);
        }
        if(dateEntree.isAfter(LocalDate.now())){
            throw new PatientException(ConstantesMetier.MSG_PATIENT_DATE_ENTREE_INVALIDE_EXCEPTION);
        }
        this.dateEntree = dateEntree;
    }

    public void setNumSecu(final String numSecu) throws PatientException {
        if(numSecu == null || numSecu.isBlank()){
            throw new PatientException(ConstantesMetier.MSG_PATIENT_NUM_SECU_EXCEPTION);
        }
        if(numSecu.length() != ConstantesMetier.PATIENT_NUM_SECU_TAILLE){
            throw new PatientException(ConstantesMetier.MSG_PATIENT_NUM_SECU_LENGTH_EXCEPTION);
        }
        this.numSecu = numSecu;
    }

    public void setNom(final String nom) throws PatientException {
        controleNomPrenom(nom,ConstantesMetier.MSG_PATIENT_NOM_EXCEPTION, ConstantesMetier.MSG_PATIENT_NOM_LENGTH_EXCEPTION,
                ConstantesMetier.PATIENT_NOM_TAILLE_MIN, ConstantesMetier.PATIENT_NOM_TAILLE_MAX );
        this.nom = nom;
    }

    public void setPrenom(final String prenom) throws PatientException {
        controleNomPrenom(prenom,ConstantesMetier.MSG_PATIENT_PRENOM_EXCEPTION, ConstantesMetier.MSG_PATIENT_PRENOM_LENGTH_EXCEPTION,
                ConstantesMetier.PATIENT_PRENOM_TAILLE_MIN, ConstantesMetier.PATIENT_PRENOM_TAILLE_MAX);
        this.prenom = prenom;
    }
    //endregion


    //region  Méthodes
    private void controleNomPrenom(final String libelle,final  String messageBlank,final  String messageMinMax,final  int min,final  int max) throws PatientException {
        if(libelle == null || libelle.isBlank()){
            throw new PatientException(messageBlank);
        }
        if(libelle.length() > min && libelle.length() < max){
            throw new PatientException(messageMinMax);
        }
    }

    private void controleRegimeAlimentaire(final Repas repas) throws PatientException {
        for (RegimeAlimentaire r : this.lstRegimeAlimentaire){
            if(!(repas.getLstRegimeAlimentaire().contains(r))){
                throw new PatientException(ConstantesMetier.PATIENT_REPAS_REGIME_ALIMENTAIRE_NON_COMPATIBLE_EXCEPTION);
            }
        }
    }
    public void ajouterRepas(final Repas repas) throws PatientException {
        if(repas == null){
            throw new PatientException(ConstantesMetier.REPAS_TYPE_REPAS_EXCEPTION);
        }
        controleRegimeAlimentaire(repas);
        this.lstRepas.add(repas);

    }
    public void ajouterRegimeAlimentaire(final RegimeAlimentaire regime) throws PatientException {
        if(this.lstRegimeAlimentaire.contains(regime)){
            throw new PatientException(ConstantesMetier.MSG_PATIENT_REGIME_ALIMENTAIRE_EXIST_EXCEPTION);
        }

        this.lstRegimeAlimentaire.add(regime);
    }



    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Patient)) return false;
        Patient patient = (Patient) o;
        return getNumSecu().equals(patient.getNumSecu());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumSecu());
    }

    @Override
    public String toString() {
        return "Patient{" +
                "dateEntree=" + dateEntree +
                ", id=" + id +
                ", numSecu='" + numSecu + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                '}';
    }

    @Override
    public int compareTo(final Patient o) {
        return this.getNumSecu().compareTo(o.getNumSecu());
    }

//endregion


}
