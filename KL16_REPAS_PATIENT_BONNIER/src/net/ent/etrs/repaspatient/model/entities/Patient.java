package net.ent.etrs.repaspatient.model.entities;

import net.ent.etrs.repaspatient.model.entities.exceptions.PatientException;
import net.ent.etrs.repaspatient.model.entities.exceptions.RepasException;
import net.ent.etrs.repaspatient.model.entities.references.ConstantesMetier;
import net.ent.etrs.repaspatient.model.entities.references.RegimeAlimentaire;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Patient {

    private final List<Repas> lstRepas = new ArrayList<>();
    private final List<RegimeAlimentaire> lstRegimeAlimentaire = new ArrayList<>();
    private LocalDate dateEntree;
    private String numSecu;
    private String nom;    private final UUID id = UUID.fromString(toString());
    private String prenom;
    protected Patient(LocalDate dateEntree, String numSecu, String nom, String prenom) throws PatientException {
        this.setDateEntree(dateEntree);
        this.setNumSecu(numSecu);
        this.setNom(nom);
        this.setPrenom(prenom);
    }

    public LocalDate getDateEntree() {
        return dateEntree;
    }

    /**
     * Si la date d'entrée est null ou blanc renvoie une exception.
     * Si le date d'entrée se situe dans le futur renvoie une exception.
     */

    public void setDateEntree(final LocalDate dateEntree) throws PatientException {
        if (dateEntree == null) {
            throw new PatientException(ConstantesMetier.MSG_PATIENT_DATE_ENTREE_EXCEPTION);
        }
        if (dateEntree.isAfter(LocalDate.now())) {
            throw new PatientException(ConstantesMetier.MSG_PATIENT_DATE_ENTREE_INVALIDE_EXCEPTION);
        }
        this.dateEntree = dateEntree;
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

    /**
     * Si le numSecu est null ou blanc renvoie une exception.
     * Si le numSecu ne fait pas SECU_TAILLE(5) renvoie une exception.
     */
    public void setNumSecu(final String numSecu) throws PatientException {
        if (numSecu == null || numSecu.isBlank()) {
            throw new PatientException(ConstantesMetier.MSG_PATIENT_NUM_SECU_EXCEPTION);
        }
        if (!(numSecu.length() == ConstantesMetier.PATIENT_NUM_SECU_TAILLE)) {
            throw new PatientException(ConstantesMetier.MSG_PATIENT_NUM_SECU_LENGTH_EXCEPTION);
        }
        this.numSecu = numSecu;
    }

    public String getNom() {
        return nom;
    }

    /**
     * Si le nom est null ou blanc renvoie une exception.
     * Si le nom ne fait pas entre la taille minimum(3) ou la taille maximum (50) renvoie une exception.
     */
    public void setNom(final String nom) throws PatientException {
        if (nom == null || nom.isBlank()) {
            throw new PatientException(ConstantesMetier.MSG_PATIENT_NOM_EXCEPTION);
        }
        if (nom.length() < ConstantesMetier.PATIENT_NOM_TAILLE_MIN || nom.length() > ConstantesMetier.PATIENT_NOM_TAILLE_MAX) {
            throw new PatientException(ConstantesMetier.MSG_PATIENT_NOM_LENGTH_EXCEPTION);
        }
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    /**
     * Si le prénom est null ou blanc renvoie une exception.
     * Si le prénom ne fait pas entre la taille minimum(3) ou la taille maximum (50) renvoie une exception.
     */
    public void setPrenom(final String prenom) throws PatientException {
        if (prenom == null || prenom.isBlank()) {
            throw new PatientException(ConstantesMetier.MSG_PATIENT_PRENOM_EXCEPTION);
        }
        if (prenom.length() < ConstantesMetier.PATIENT_PRENOM_TAILLE_MIN || prenom.length() > ConstantesMetier.PATIENT_PRENOM_TAILLE_MAX) {
            throw new PatientException(ConstantesMetier.MSG_PATIENT_PRENOM_LENGTH_EXCEPTION);
        }
        this.prenom = prenom;
    }

    private void controlerRegimeAlimentaire(Repas repas) throws RepasException, PatientException {
        if (repas.getLstRegimeAlimentaire().contains(this.lstRepas)) {
            throw new PatientException(ConstantesMetier.PATIENT_REPAS_REGIME_ALIMENTAIRE_EXCEPTION);
        }
    }

    /**
     * Si le régime alimentaire est null renvoie une exception.
     * Si le régime alimentaire est déjà contenu dans la liste des régimes alimentaire du patient renvoie une exception.
     */

    public void ajouterRegimeAlimentaire(final RegimeAlimentaire regimeAlimentaire) throws PatientException {
        if (regimeAlimentaire == null) {
            throw new PatientException(ConstantesMetier.MSG_PATIENT_REGIME_ALIMENTAIRE_EXCEPTION);
        }
        if (this.lstRegimeAlimentaire.contains(regimeAlimentaire)) {
            throw new PatientException(ConstantesMetier.MSG_PATIENT_REGIME_ALIMENTAIRE_EXIST_EXCEPTION);
        }
        this.lstRegimeAlimentaire.add(regimeAlimentaire);
    }

    /**
     * Comparaison sur NumSecu
     */

    @Override
    public boolean equals(Object o) {
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
                ", id=" + id +
                ", numSecu='" + numSecu + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                '}';
    }




}
