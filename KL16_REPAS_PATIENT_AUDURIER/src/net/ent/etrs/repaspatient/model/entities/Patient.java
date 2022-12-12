package net.ent.etrs.repaspatient.model.entities;

import net.ent.etrs.repaspatient.model.daos.Identifiable;
import net.ent.etrs.repaspatient.model.entities.exceptions.PatientException;
import net.ent.etrs.repaspatient.model.entities.references.ConstantesMetier;
import net.ent.etrs.repaspatient.model.entities.references.RegimeAlimentaire;

import java.time.LocalDate;
import java.util.*;

public class Patient implements Identifiable<String>, Comparable<Patient> {
    //Attributs
    private LocalDate dateEntree;
    private List<Repas> lstRepas;
    private List<RegimeAlimentaire> lstRegimeAlimentaire;
    private String id;
    private String numSecu;
    private String nom;
    private String prenom;
    //Constructor

    protected Patient(final String numSecu, final String nom, final String prenom, final LocalDate dateEntree)
            throws PatientException {
        this.id = UUID.randomUUID().toString();
        try {
            this.setDateEntree(dateEntree);
            this.setNumSecu(numSecu);
            this.setNom(nom);
            this.setPrenom(prenom);
            this.lstRepas = new ArrayList<>();
            this.lstRegimeAlimentaire = new ArrayList<>();
        } catch (PatientException e) {
            throw new PatientException(ConstantesMetier.PATIENT_CONSTRUCTION_EXCEPTION);
        }
    }

    //Getter/Setter

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

    @Override
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
        if (numSecu.length() != ConstantesMetier.PATIENT_NUM_SECU_TAILLE) {
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
        if (nom.length() < ConstantesMetier.PATIENT_NOM_TAILLE_MIN || nom.length() > ConstantesMetier.PATIENT_NOM_TAILLE_MAX) {
            throw new PatientException(ConstantesMetier.MSG_PATIENT_NOM_LENGTH_EXCEPTION);
        }
        this.nom = nom.toUpperCase();
    }

    public String getPrenom() {
        return this.prenom;
    }

    public void setPrenom(final String prenom) throws PatientException {
        if (prenom == null || prenom.isBlank()) {
            throw new PatientException(ConstantesMetier.MSG_PATIENT_PRENOM_EXCEPTION);
        }
        if (prenom.length() < ConstantesMetier.PATIENT_PRENOM_TAILLE_MIN || prenom.length() > ConstantesMetier.PATIENT_PRENOM_TAILLE_MAX) {
            throw new PatientException(ConstantesMetier.MSG_PATIENT_PRENOM_LENGTH_EXCEPTION);
        }
        this.prenom = prenom;
    }

    public void ajouterRegimeAlimentaire(RegimeAlimentaire regimeAlimentaire) throws PatientException {
        if (regimeAlimentaire == null) {
            throw new PatientException(ConstantesMetier.PATIENT_REPAS_REGIME_ALIMENTAIRE_EXCEPTION);
        }
        if (lstRegimeAlimentaire.contains(regimeAlimentaire)) {
            throw new PatientException(ConstantesMetier.MSG_PATIENT_REGIME_ALIMENTAIRE_EXIST_EXCEPTION);
        }
        lstRegimeAlimentaire.add(regimeAlimentaire);
    }

    public void ajouterRepas(Repas repas) throws PatientException {
        if (repas == null) {
            throw new PatientException(ConstantesMetier.MSG_PATIENT_REGIME_ALIMENTAIRE_EXCEPTION);
        }
        controlerRegimeAlimentaire(repas);
        this.lstRepas.add(repas);
    }

    private void controlerRegimeAlimentaire(final Repas repas) throws PatientException {
        for (RegimeAlimentaire regimeAlimentairePatient : this.lstRegimeAlimentaire) {
            if (!repas.getLstRegimeAlimentaire().contains(regimeAlimentairePatient)) {
                throw new PatientException(ConstantesMetier.PATIENT_REPAS_REGIME_ALIMENTAIRE_NON_COMPATIBLE_EXCEPTION);
            }
        }
    }

    public List<Repas> getLstRepas() {
        return Collections.unmodifiableList(this.lstRepas);
    }

    public List<RegimeAlimentaire> getLstRegimeAlimentaire() {
        return Collections.unmodifiableList(this.lstRegimeAlimentaire);
    }
    //Other methods

    //Equals/Hashcode

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

    //To String

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

    //Compare To
    @Override
    public int compareTo(final Patient o) {
        return this.getNumSecu().compareTo(o.getNumSecu());
    }
}
