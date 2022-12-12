package net.ent.etrs.repaspatient.model.entities;

import net.ent.etrs.repaspatient.model.entities.exceptions.PatientException;
import net.ent.etrs.repaspatient.model.entities.references.ConstantesMetier;
import net.ent.etrs.repaspatient.model.entities.references.RegimeAlimentaire;

import java.time.LocalDate;
import java.util.*;

public class Patient implements Comparable<Patient> {
    private final String id = UUID.randomUUID().toString();
    //region ATTRIBUTS
    private LocalDate dateEntree;
    private List<Repas> lstRepas = new ArrayList<>();
    private List<RegimeAlimentaire> lstRegimeAlimentaire = new ArrayList<>();
    private String numSecu;
    private String nom;
    private String prenom;
    //endregion
    //region CONSTRUCTEUR(S)

    protected Patient(final LocalDate dateEntree, final String numSecu, final String nom, final String prenom) throws PatientException {
        this.setDateEntree(dateEntree);
        this.setNumSecu(numSecu);
        this.setNom(nom);
        this.setPrenom(prenom);
    }

    //endregion
    //region GETTER SETTER

    public String getId() {
        return this.id;
    }

    public LocalDate getDateEntree() {
        return this.dateEntree;
    }

    public void setDateEntree(final LocalDate dateEntree) throws PatientException {
        if (Objects.isNull(dateEntree)) {
            throw new PatientException(ConstantesMetier.MSG_PATIENT_DATE_ENTREE_EXCEPTION);
        }
        if (dateEntree.toEpochDay() > LocalDate.now().toEpochDay()) {
            throw new PatientException(ConstantesMetier.MSG_PATIENT_DATE_ENTREE_INVALIDE_EXCEPTION);
        }
        this.dateEntree = dateEntree;
    }

    public List<Repas> getLstRepas() {
        return Collections.unmodifiableList(this.lstRepas);
    }

    public List<RegimeAlimentaire> getLstRegimeAlimentaire() {
        return Collections.unmodifiableList(this.lstRegimeAlimentaire);
    }

    public String getNumSecu() {
        return this.numSecu;
    }

    public void setNumSecu(final String numSecu) throws PatientException {
        if (Objects.isNull(numSecu) || numSecu.isBlank()) {
            throw new PatientException(ConstantesMetier.MSG_PATIENT_NUM_SECU_EXCEPTION);
        }
        if (numSecu.length() != ConstantesMetier.PATIENT_NUM_SECU_TAILLE) {
            throw new PatientException(String.format(ConstantesMetier.MSG_PATIENT_NUM_SECU_LENGTH_EXCEPTION, ConstantesMetier.PATIENT_NUM_SECU_TAILLE));
        }
        this.numSecu = numSecu;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(final String nom) throws PatientException {
        if (Objects.isNull(nom) || nom.isBlank()) {
            throw new PatientException(ConstantesMetier.MSG_PATIENT_NOM_EXCEPTION);
        }
        if (nom.length() < ConstantesMetier.PATIENT_NOM_TAILLE_MIN || nom.length() > ConstantesMetier.PATIENT_NOM_TAILLE_MAX) {
            throw new PatientException(String.format(ConstantesMetier.MSG_PATIENT_NOM_LENGTH_EXCEPTION, ConstantesMetier.PATIENT_NOM_TAILLE_MIN, ConstantesMetier.PATIENT_NOM_TAILLE_MAX));
        }
        this.nom = nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public void setPrenom(final String prenom) throws PatientException {
        if (Objects.isNull(prenom) || prenom.isBlank()) {
            throw new PatientException(ConstantesMetier.MSG_PATIENT_PRENOM_EXCEPTION);
        }
        if (prenom.length() < ConstantesMetier.PATIENT_PRENOM_TAILLE_MIN || prenom.length() > ConstantesMetier.PATIENT_PRENOM_TAILLE_MAX) {
            throw new PatientException(String.format(ConstantesMetier.MSG_PATIENT_PRENOM_LENGTH_EXCEPTION, ConstantesMetier.PATIENT_PRENOM_TAILLE_MIN, ConstantesMetier.PATIENT_PRENOM_TAILLE_MAX));
        }
        this.prenom = prenom;
    }

    public void ajouterRepas(final Repas repas) throws PatientException {
        if (Objects.isNull(repas)) {
            throw new PatientException(ConstantesMetier.PATIENT_REPAS_REGIME_ALIMENTAIRE_EXCEPTION);
        }
        controlerRegimeAlimentaire(repas);
        this.lstRepas.add(repas);
    }

    /**
     * Contrôle que le repas
     *
     * @param repas
     * @throws PatientException
     */
    private void controlerRegimeAlimentaire(final Repas repas) throws PatientException {
        for (RegimeAlimentaire regimeAlimentairePatient : this.lstRegimeAlimentaire) {
            boolean ALeRegime = false;
            for (RegimeAlimentaire regimeAlimentaireRepas : repas.getLstRegimeAlimentaire()) {
                if (regimeAlimentairePatient == regimeAlimentaireRepas) {
                    ALeRegime = true;
                }
            }
            if (!ALeRegime) {
                throw new PatientException(ConstantesMetier.PATIENT_REPAS_REGIME_ALIMENTAIRE_NON_COMPATIBLE_EXCEPTION);
            }
        }
    }

    public void ajouterRegimeAlimentaire(final RegimeAlimentaire regimeAlimentaire) throws PatientException {
        if (Objects.isNull(regimeAlimentaire)) {
            throw new PatientException(ConstantesMetier.MSG_PATIENT_REGIME_ALIMENTAIRE_EXCEPTION);
        }
        if (this.lstRegimeAlimentaire.contains(regimeAlimentaire)) {
            throw new PatientException(ConstantesMetier.MSG_PATIENT_REGIME_ALIMENTAIRE_EXIST_EXCEPTION);
        }
        this.lstRegimeAlimentaire.add(regimeAlimentaire);
    }

    //endregion
    //region Equals / HashCode

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

    //endregion
    //region MÉTHODES

    @Override
    public String toString() {
        return "Patient{" +
                "id='" + id + '\'' +
                ", dateEntree=" + dateEntree +
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
