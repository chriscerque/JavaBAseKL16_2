package net.ent.etrs.KL16_REPAS_PATIENT_FARIAT.Model.entities;

import net.ent.etrs.KL16_REPAS_PATIENT_FARIAT.Model.entities.excetions.PatientException;
import net.ent.etrs.KL16_REPAS_PATIENT_FARIAT.Model.entities.references.ConstantesMetier;
import net.ent.etrs.KL16_REPAS_PATIENT_FARIAT.Model.entities.references.RegimeAlimentaire;

import java.time.LocalDate;
import java.util.*;

public class Patient {
    //ATTRIBUTS
    private LocalDate dateEntree;
    private List<Repas> lstRepas = new ArrayList<>();
    private List<RegimeAlimentaire> lstRegimeAlimentaire = new ArrayList<>();
    private String id = UUID.randomUUID().toString();
    private String numSecu;
    private String nom;
    private String prenom;

    //CONSTRUCTEUR

    public Patient(final LocalDate dateEntree, final String numSecu, final String nom, final String prenom) throws PatientException {
        this.setDateEntree(dateEntree);
        this.setNumSecu(numSecu);
        this.setNom(nom);
        this.setPrenom(prenom);
    }


    //GETTER SETTER


    public String getId() {
        return this.id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public LocalDate getDateEntree() {
        return this.dateEntree;
    }

    public void setDateEntree(final LocalDate dateEntree) throws PatientException {
        if (dateEntree.isAfter(LocalDate.now())) {
            throw new PatientException(ConstantesMetier.MSG_PATIENT_DATE_ENTREE_INVALIDE_EXCEPTION);
        }
        if (dateEntree == null) {
            throw new PatientException(ConstantesMetier.MSG_PATIENT_DATE_ENTREE_EXCEPTION);
        }
        this.dateEntree = dateEntree;
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
        this.nom = nom;
    }

    public String getPrenom() {

        return this.prenom;
    }

    public void setPrenom(final String prenom) throws PatientException {
        if (prenom == null || prenom.isBlank()) {
            throw new PatientException(ConstantesMetier.MSG_PATIENT_PRENOM_EXCEPTION);
        }
        if (prenom.length() < ConstantesMetier.PATIENT_PRENOM_TAILLE_MAX || prenom.length() > ConstantesMetier.PATIENT_PRENOM_TAILLE_MIN) {
            throw new PatientException(ConstantesMetier.MSG_PATIENT_PRENOM_LENGTH_EXCEPTION);
        }
        this.prenom = prenom;
    }

    public List<Repas> getLstRepas() {
        return Collections.unmodifiableList(this.lstRepas);
    }

    public List<RegimeAlimentaire> getLstRegimeAlimentaire() {
        return Collections.unmodifiableList(this.lstRegimeAlimentaire);
    }


    public void ajouterRepas(Repas repas) throws PatientException {
        if (repas == null) {
            throw new PatientException(ConstantesMetier.REPAS_NULL);
        }
        this.lstRepas.add(repas);

    }

    private void controlerRegimeAlimentaire(Repas repas) throws PatientException {
        for (RegimeAlimentaire r : this.lstRegimeAlimentaire) {
            if (!(repas.getLstRegimeAlimentaire().contains(r))) {
                throw new PatientException(ConstantesMetier.PATIENT_REPAS_REGIME_ALIMENTAIRE_NON_COMPATIBLE_EXCEPTION);

            }
        }
    }

    public void ajouterRegimeAlimentaire(RegimeAlimentaire regimeAlimentaire) throws PatientException {
        if (regimeAlimentaire == null) {
            throw new PatientException(ConstantesMetier.REGIME_ALIMENTAIRE_NULL);
        }
        if (this.lstRegimeAlimentaire.contains(regimeAlimentaire)) {
            throw new PatientException(ConstantesMetier.MSG_PATIENT_REGIME_ALIMENTAIRE_EXIST_EXCEPTION);
        }
        this.lstRegimeAlimentaire.add(regimeAlimentaire);
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
                ", id=" + id +
                ", numSecu='" + numSecu + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                '}';
    }
}
