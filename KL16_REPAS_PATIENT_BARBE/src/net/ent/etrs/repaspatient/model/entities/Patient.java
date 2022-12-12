package net.ent.etrs.repaspatient.model.entities;

import net.ent.etrs.repaspatient.model.entities.exceptions.ConstantesException;
import net.ent.etrs.repaspatient.model.entities.exceptions.PatientException;
import net.ent.etrs.repaspatient.model.entities.exceptions.RegimeAlimentaireException;
import net.ent.etrs.repaspatient.model.entities.references.ConstantesMetier;
import net.ent.etrs.repaspatient.model.entities.references.RegimeAlimentaire;

import java.time.LocalDate;
import java.util.*;

public class Patient {

    private final UUID id;
    // ATTRIBUTES
    private LocalDate dateEntree;
    private List<Repas> lstRepas;
    private List<RegimeAlimentaire> lstRegimeAlimentaire;
    private String numSecu;
    private String nom;
    private String prenom;

    // CONSTRUCTOR(S)

    protected Patient(LocalDate dateEntree, String numSecu, String nom, String prenom) throws PatientException {
        this.setDateEntree(dateEntree);
        this.setNumSecu(numSecu);
        this.setNom(nom);
        this.setPrenom(prenom);
        this.id = UUID.randomUUID();
        this.lstRepas = new ArrayList<>();
        this.lstRegimeAlimentaire = new ArrayList<>();
    }


    // GETTERS & SETTERS

    public LocalDate getDateEntree() {
        return this.dateEntree;
    }

    public void setDateEntree(LocalDate dateEntree) throws PatientException {
        if (dateEntree == null) {
            throw new PatientException(ConstantesException.PATIENT_DATEENTREE_NULL_EXCEPTION);
        }
        if (dateEntree.isAfter(LocalDate.now())) {
            throw new PatientException(ConstantesException.PATIENT_DATEENTREE_FUTURE);
        }

        this.dateEntree = dateEntree;
    }

    public List<Repas> getLstRepas() {
        return Collections.unmodifiableList(this.lstRepas);
    }

    /**
     * Ajouter un regime alimentaire a la liste des regimes alimentaires.
     * <p>
     * Contrôle si le patient a deja ce régime alimentaire, car il ne peut pas avoir plusieurs fois le même.
     *
     * @param regimeAlimentaire le regime alimentaire à ajouter
     * @throws PatientException l'exception de la classe Patient.
     */
    public void ajouterRegimeAlimentaire(final RegimeAlimentaire regimeAlimentaire) throws PatientException {
        if (Objects.isNull(regimeAlimentaire)) {
            throw new PatientException(ConstantesException.MSG_REPAS_REGIME_ALIMENTAIRE_NON_RENSEIGNE_EXCEPTION);
        }
        if (this.lstRegimeAlimentaire.contains(regimeAlimentaire)) {
            throw new PatientException(ConstantesException.MSG_REPAS_REGIME_ALIMENTAIRE_EXIST_EXCEPTION);
        }
        for (RegimeAlimentaire item : this.lstRegimeAlimentaire) {
            if (item.name().equals(regimeAlimentaire.name())) {
                throw new PatientException(ConstantesException.MSG_REPAS_REGIME_ALIMENTAIRE_EXIST_EXCEPTION);
            }
        }
        this.lstRegimeAlimentaire.add(regimeAlimentaire);
    }

    public List<RegimeAlimentaire> getLstRegimeAlimentaire() {
        return Collections.unmodifiableList(this.lstRegimeAlimentaire);
    }

    /**
     * Ajouter un repas a la liste des repas.
     *
     * @param repas le repas
     * @throws PatientException l'exception de la classe Patient.
     */
    public void ajouterRepas(final Repas repas) throws PatientException {
        if (Objects.isNull(repas)) {
            throw new PatientException(ConstantesException.MSG_PATIENT_REPAS_NON_RENSEIGNE_EXCEPTION);
        }
        for (Repas item : this.lstRepas) {
            if (item.getDateRepas().equals(repas.getDateRepas())) {
                throw new PatientException(ConstantesException.MSG_PATIENT_REPAS_EXIST_EXCEPTION);
            }
        }
        this.lstRepas.add(repas);
    }

    public UUID getId() {
        return this.id;
    }


    public String getNumSecu() {
        return this.numSecu;
    }

    public void setNumSecu(String numSecu) throws PatientException {
        if (numSecu == null || numSecu.isBlank()) {
            throw new PatientException(ConstantesException.PATIENT_NUMSECU_NULL_EXCEPTION);
        }
        if (numSecu.length() < 5) {
            throw new PatientException(ConstantesException.PATIENT_NUMSECU_TOO_SHORT_EXCEPTION + ConstantesMetier.PATIENT_NUM_SECU_TAILLE);
        }

        this.numSecu = numSecu;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) throws PatientException {
        if (nom == null || nom.isBlank()) {
            throw new PatientException(ConstantesException.PATIENT_NOM_NULL_EXCEPTION);
        }
        if (nom.length() < ConstantesMetier.PATIENT_NOM_TAILLE_MIN || nom.length() > ConstantesMetier.PATIENT_NOM_TAILLE_MAX) {
            throw new PatientException(ConstantesException.MSG_PATIENT_NOM_LENGTH_EXCEPTION);
        }
        this.nom = nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public void setPrenom(String prenom) throws PatientException {
        if (prenom == null || prenom.isBlank()) {
            throw new PatientException(ConstantesException.PATIENT_PRENOM_NULL_EXCEPTION);
        }

        if (prenom.length() < ConstantesMetier.PATIENT_PRENOM_TAILLE_MIN || prenom.length() > ConstantesMetier.PATIENT_PRENOM_TAILLE_MAX) {
            throw new PatientException(ConstantesException.MSG_PATIENT_PRENOM_LENGTH_EXCEPTION);
        }

        this.prenom = prenom;
    }


    // EQUALS & HASHCODE

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


    // TO STRING
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


    // OTHER METHODS

    /**
     * Contrôle si le régime alimentaire respecte les règles de gestion.
     * <p>
     * Le régime alimentaire du repas doit respecter au minimum le régime alimentaire du patient.
     *
     * @param repas Le repas à controler.
     */
    private void controlerRegimeAlimentaire(Repas repas) throws PatientException {
        try {
            if (!repas.getLstRegimeAlimentaire().contains(RegimeAlimentaire.getByLibelle(repas.getId().toString()))) {
                throw new PatientException(ConstantesException.REGIME_ALIMENTAIRE_EXCEPTION);
            }
        } catch (RegimeAlimentaireException e) {
            throw new PatientException(e.getMessage());
        }

    }

}
