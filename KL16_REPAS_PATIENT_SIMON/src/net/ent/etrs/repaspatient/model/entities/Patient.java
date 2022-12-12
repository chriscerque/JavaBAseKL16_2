package net.ent.etrs.repaspatient.model.entities;

import net.ent.etrs.repaspatient.model.entities.exceptions.*;
import net.ent.etrs.repaspatient.model.entities.references.ConstantesMetier;
import net.ent.etrs.repaspatient.model.entities.references.RegimeAlimentaire;

import java.time.LocalDate;
import java.util.*;

public class Patient {
    //Attributs
    private LocalDate dateEntree;
    private List<Repas> lstRepas = new ArrayList<>();
    private List<RegimeAlimentaire> lstRegimeAlimentaire = new ArrayList<>();
    private String id;
    private String numSecu;
    private String nom;
    private String prenom;
    //Constructeur

    protected Patient(final LocalDate dateEntree, final String numSecu, final String nom, final String prenom) throws PatientException {
        try {
            this.setDateEntree(dateEntree);
            this.setNumSecu(numSecu);
            this.setNom(nom);
            this.setPrenom(prenom);
            this.id = UUID.randomUUID().toString();
        }catch (DateEntreePatientException | NumSecuException | NomPatientException | PrenomPatientException e){
            throw new PatientException(e.getMessage(), e);
        }


    }

    //Accesseurs

    public LocalDate getDateEntree() {
        return dateEntree;
    }

    public void setDateEntree(final LocalDate dateEntree) throws DateEntreePatientException {
        if (Objects.isNull(dateEntree)){
            throw new DateEntreePatientException(ConstantesMetier.MSG_PATIENT_DATE_ENTREE_EXCEPTION);
        }

        this.dateEntree = dateEntree;
    }

    public String getNumSecu() {
        return numSecu;
    }

    public void setNumSecu(final String numSecu) throws NumSecuException {
        if (Objects.isNull(numSecu) || numSecu.isBlank()){
            throw new NumSecuException(ConstantesMetier.MSG_PATIENT_NUM_SECU_EXCEPTION);
        }
        if (numSecu.length() != (ConstantesMetier.PATIENT_NUM_SECU_TAILLE)){
            throw new NumSecuException(ConstantesMetier.MSG_PATIENT_NUM_SECU_LENGTH_EXCEPTION);
        }
        this.numSecu = numSecu;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(final String nom) throws NomPatientException {
        if (Objects.isNull(nom) || nom.isBlank()){
            throw new NomPatientException(ConstantesMetier.MSG_PATIENT_NOM_EXCEPTION);
        }
        if (nom.length() < (ConstantesMetier.PATIENT_NOM_TAILLE_MIN ) || nom.length() > (ConstantesMetier.PATIENT_NOM_TAILLE_MAX)){
            throw new NomPatientException((ConstantesMetier.MSG_PATIENT_NOM_LENGTH_EXCEPTION));
        }
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(final String prenom) throws PrenomPatientException {
        if (Objects.isNull(prenom) || prenom.isBlank()){
            throw new PrenomPatientException(ConstantesMetier.MSG_PATIENT_PRENOM_EXCEPTION);
        }
        if (nom.length() < (ConstantesMetier.PATIENT_PRENOM_TAILLE_MIN ) || nom.length() > (ConstantesMetier.PATIENT_PRENOM_TAILLE_MAX)){
            throw new PrenomPatientException((ConstantesMetier.MSG_PATIENT_PRENOM_LENGTH_EXCEPTION));
        }
        this.prenom = prenom;
    }

    public List<Repas> getLstRepas() {
        return Collections.unmodifiableList(lstRepas);
    }

    public List<RegimeAlimentaire> getLstRegimeAlimentaire() {
        return Collections.unmodifiableList(lstRegimeAlimentaire);
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Patient)) return false;
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
                ", lstRepas=" + lstRepas +
                ", lstRegimeAlimentaire=" + lstRegimeAlimentaire +
                ", id='" + id + '\'' +
                ", numSecu='" + numSecu + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                '}';
    }
    //Autres méthodes
    public void ajouterRepas(Repas repas) throws PatientException {
        if (Objects.isNull(repas)){
            throw new PatientException(ConstantesMetier.PATIENT_REPAS_REGIME_ALIMENTAIRE_EXCEPTION);
        }
        if (this.lstRepas.contains(repas)){
            throw new PatientException(ConstantesMetier.PATIENT_REPAS_REGIME_ALIMENTAIRE_EXIST_EXCEPTION);
        }
        for (Repas r : this.lstRepas){
            if (r.getTypeRepas().equals(repas.getTypeRepas())){
                throw new PatientException(ConstantesMetier.MSG_CONSTRUCTION_REPAS);
            }
        }

    }
    public void ajouterRegimeAlimentaire(RegimeAlimentaire regimeAlimentaire) throws PatientException {
        if (Objects.isNull(regimeAlimentaire)){
            throw new PatientException(ConstantesMetier.PATIENT_REPAS_REGIME_ALIMENTAIRE_EXCEPTION);
        }
        if (this.lstRegimeAlimentaire.contains(regimeAlimentaire)){
            throw new PatientException(ConstantesMetier.MSG_PATIENT_REGIME_ALIMENTAIRE_EXIST_EXCEPTION);
        }
        for (RegimeAlimentaire ra : this.lstRegimeAlimentaire){
            if (ra.getLibelle().equals(regimeAlimentaire.getLibelle())){
                throw new PatientException(ConstantesMetier.PATIENT_REPAS_REGIME_ALIMENTAIRE_NON_COMPATIBLE_EXCEPTION);
            }
        }

    }
}
