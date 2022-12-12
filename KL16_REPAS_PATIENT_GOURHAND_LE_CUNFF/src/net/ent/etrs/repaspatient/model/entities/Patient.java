package net.ent.etrs.repaspatient.model.entities;

import net.ent.etrs.repaspatient.model.entities.exceptions.PatientConstructionException;
import net.ent.etrs.repaspatient.model.entities.exceptions.PatientException;
import net.ent.etrs.repaspatient.model.entities.references.ConstantesMetier;
import net.ent.etrs.repaspatient.model.entities.references.RegimeAlimentaire;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Patient {
    private LocalDate dateEntree;
    private List<Repas> lstRepas = new ArrayList<>();
    private List<RegimeAlimentaire> lstRegimeAlimentaire = new ArrayList<>();
    private final String id;
    private String numSecu;
    private String nom;
    private String prenom;

    public Patient(final String numSecu, final String nom, final String prenom, final LocalDate dateEntree) throws PatientException {
        try {
            this.id = UUID.randomUUID().toString();
            this.setNumSecu(numSecu);
            this.setNom(nom);
            this.setPrenom(prenom);
            this.setDateEntree(dateEntree);
        } catch (PatientException e) {
            throw new PatientException(e.getMessage());
        }
    }

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

    public List<Repas> getLstRepas() {
        return this.lstRepas;
    }

    public void ajouterRepas(final Repas repas) throws PatientException {
        if (repas == null) {
            throw new PatientException(ConstantesMetier.PATIENT_REPAS_REGIME_ALIMENTAIRE_EXCEPTION);
        }
        for (RegimeAlimentaire regimeAlimentaire :
                repas.getLstRegimeAlimentaire()) {
            if (this.getLstRegimeAlimentaire().contains(regimeAlimentaire)) {
                if (!this.lstRepas.contains(repas)) {
                    this.lstRepas.add(repas);
                }
            }
        }
    }

    public List<RegimeAlimentaire> getLstRegimeAlimentaire() {
        return this.lstRegimeAlimentaire;
    }

    public void ajouterRegimeAlimentaire(final RegimeAlimentaire regimeAlimentaire) throws PatientException {
        if (regimeAlimentaire == null) {
            throw new PatientException(ConstantesMetier.MSG_PATIENT_REGIME_ALIMENTAIRE_EXCEPTION);
        }
        if (this.getLstRegimeAlimentaire().contains(regimeAlimentaire)) {
            throw new PatientException(ConstantesMetier.MSG_PATIENT_REGIME_ALIMENTAIRE_EXIST_EXCEPTION);
        }
        this.lstRegimeAlimentaire.add(regimeAlimentaire);
    }

    public String getId() {
        return this.id;
    }

    public String getNumSecu() {
        return this.numSecu;
    }

    public void setNumSecu(final String numSecu) throws PatientException {
        if (numSecu == null) {
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
        if (nom == null) {
            throw new PatientException(ConstantesMetier.MSG_PATIENT_NOM_EXCEPTION);
        }
        if (nom.length() < ConstantesMetier.PATIENT_NOM_TAILLE_MIN || nom.length() > ConstantesMetier.PATIENT_NOM_TAILLE_MAX) {
            throw new PatientException(ConstantesMetier.MSG_PATIENT_NOM_LENGTH_EXCEPTION);
        }
        this.nom = nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public void setPrenom(final String prenom) throws PatientException {
        if (prenom == null) {
            throw new PatientException(ConstantesMetier.MSG_PATIENT_PRENOM_EXCEPTION);
        }
        if (prenom.length() < ConstantesMetier.PATIENT_PRENOM_TAILLE_MIN || prenom.length() > ConstantesMetier.PATIENT_PRENOM_TAILLE_MAX) {
            throw new PatientException(ConstantesMetier.MSG_PATIENT_PRENOM_LENGTH_EXCEPTION);
        }
        this.prenom = prenom;
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
                ", id='" + id + '\'' +
                ", numSecu='" + numSecu + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                '}';
    }

    private void controlerRegimeAlimentaire(RegimeAlimentaire regimeAlimentaire) {

    }
}
