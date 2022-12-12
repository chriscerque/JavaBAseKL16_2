package net.ent.etrs.repaspatient.model.entities;

import net.ent.etrs.repaspatient.model.entities.exceptions.PatientException;
import net.ent.etrs.repaspatient.model.entities.exceptions.RepasException;
import net.ent.etrs.repaspatient.model.entities.references.ConstantesMetier;
import net.ent.etrs.repaspatient.model.entities.references.RegimeAlimentaire;

import java.security.PrivateKey;
import java.time.LocalDate;
import java.util.*;

public class Patient {

    // Attribut(s)

    private LocalDate dateEntree;
    private List<Repas> lstRepas = new ArrayList<>();
    private List<RegimeAlimentaire> lstRegimeAlimentaire = new ArrayList<>();
    private String id = UUID.randomUUID().toString();
    private String numSecu;
    private String nom;
    private String prenom;

    // Constructeur(s)

    protected Patient(final LocalDate dateEntree, final String numSecu, final String nom, final String prenom) throws PatientException {
        this.setDateEntree(dateEntree);
        this.setNumSecu(numSecu);
        this.setNom(nom);
        this.setPrenom(prenom);
    }

    // Accesseur(s)

    public LocalDate getDateEntree() {
        return this.dateEntree;
    }

    public void setDateEntree(final LocalDate dateEntree) throws PatientException {
        if (dateEntree == null) {
            throw new PatientException(ConstantesMetier.MSG_PATIENT_DATE_ENTREE_EXCEPTION);
        }
        if (dateEntree.isAfter(LocalDate.now())) {
            throw new PatientException(ConstantesMetier.MSG_PATIENT_DATE_ENTREE_INVALIDE_EXCEPTION);
        }
        this.dateEntree = dateEntree;
    }

    public List<Repas> getLstRepas() throws PatientException {
        if (lstRepas.isEmpty()) {
            throw new PatientException(ConstantesMetier.MSG_PATIENT_REPAS_VIDE_EXCEPTION);
        }
        return Collections.unmodifiableList(this.lstRepas);
    }

    public List<RegimeAlimentaire> getLstRegimeAlimentaire() throws PatientException {
        if (lstRegimeAlimentaire.isEmpty()) {
            throw new PatientException(ConstantesMetier.MSG_PATIENT_REGIME_ALIMENTAIRE_VIDE_EXCEPTION);
        }
        return Collections.unmodifiableList(this.lstRegimeAlimentaire);
    }

    public String getId() {
        return this.id;
    }


    public String getNumSecu() {
        return this.numSecu;
    }

    public void setNumSecu(final String numSecu) throws PatientException {
        if (numSecu == null || numSecu.isBlank()) {
            throw new PatientException(ConstantesMetier.MSG_PATIENT_NUM_SECU_EXCEPTION);
        }
        if (ConstantesMetier.PATIENT_NUM_SECU_TAILLE != numSecu.length()){
            throw new PatientException(ConstantesMetier.MSG_PATIENT_NUM_SECU_LENGTH_EXCEPTION);
        }
        this.numSecu = numSecu;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(final String nom) throws PatientException {
        if (nom == null || nom.isBlank()) {
            throw new PatientException(ConstantesMetier.MSG_PATIENT_NOM_EXCEPTION);
        }
        if (nom.length() < ConstantesMetier.PATIENT_NOM_TAILLE_MIN || nom.length() > ConstantesMetier.PATIENT_NOM_TAILLE_MAX){
            throw new PatientException(ConstantesMetier.MSG_PATIENT_NOM_LENGTH_EXCEPTION);
        }
        this.nom = nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public void setPrenom(final String prenom) throws PatientException {
        if (prenom == null || prenom.isBlank()) {
            throw new PatientException(ConstantesMetier.MSG_PATIENT_PRENOM_EXCEPTION);
        }
        if (nom.length() < ConstantesMetier.PATIENT_PRENOM_TAILLE_MIN || nom.length() > ConstantesMetier.PATIENT_PRENOM_TAILLE_MAX){
            throw new PatientException(ConstantesMetier.MSG_PATIENT_PRENOM_LENGTH_EXCEPTION);
        }
        this.prenom = prenom;
    }

    // Autres méthode(s)

    protected void ajouterRegimeAlimentaire(final RegimeAlimentaire regimeAlimentaire) throws PatientException {
        if (Objects.isNull(regimeAlimentaire)){
            throw new PatientException(ConstantesMetier.MSG_PATIENT_REGIME_ALIMENTAIRE_EXCEPTION);
        }
        if (this.lstRegimeAlimentaire.contains(regimeAlimentaire)){
            throw new PatientException(ConstantesMetier.MSG_PATIENT_REGIME_ALIMENTAIRE_EXIST_EXCEPTION);
        }
        this.lstRegimeAlimentaire.add(regimeAlimentaire);
    }

    public void ajouterRepas(final Repas repas) throws PatientException, RepasException {

        boolean ajoute = false;
        if (Objects.isNull(repas)){
            throw new PatientException(ConstantesMetier.MSG_PATIENT_REPAS_VIDE_EXCEPTION);
        }
        for (RegimeAlimentaire regimeAlimentaire : lstRegimeAlimentaire){
            if (repas.getLstRegimeAlimentaire().contains(regimeAlimentaire)){
                this.lstRepas.add(repas);
                ajoute = true;
            }
        }
        if (!ajoute){
            throw new PatientException(ConstantesMetier.PATIENT_REPAS_REGIME_ALIMENTAIRE_NON_COMPATIBLE_EXCEPTION);
        }
    }

    // Equals & Hashcode

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Patient)) return false;
        Patient repas = (Patient) o;
        return Objects.equals(getNumSecu(), repas.getNumSecu());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumSecu());
    }

    // ToString

    @Override
    public String toString() {
        return "Repas{" +
                "dateEntree=" + dateEntree +
                ", id='" + id + '\'' +
                ", numSecu='" + numSecu + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                '}';
    }
}
