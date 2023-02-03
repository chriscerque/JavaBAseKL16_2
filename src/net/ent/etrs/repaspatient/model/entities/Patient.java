package net.ent.etrs.repaspatient.model.entities;


import net.ent.etrs.repaspatient.model.entities.exceptions.PatientException;
import net.ent.etrs.repaspatient.model.entities.references.ConstantesMetier;

import java.time.LocalDate;
import java.util.*;

/**
 * Definie un Patient.
 * identité basé sur le numSecu
 * un patient à un nom, prenom, date de naissance et une liste de regime alimentaire et une liste de repas
 *
 * @author christophe.cerqueira
 */

public class Patient {

    private final String id = UUID.randomUUID().toString();

    private String numSecu;
    private String nom;
    private String prenom;

    private LocalDate dateEntree;

    private List<RegimeAlimentaire> lstRegimeAlimentaire = new ArrayList<>();

    private Set<Repas> lstRepas = new HashSet<>();

    protected Patient(final String numSecu, final String nom, final String prenom, final LocalDate dateEntree) throws PatientException {
        this.setNumSecu(numSecu);
        this.setNom(nom);
        this.setPrenom(prenom);
        this.setDateEntree(dateEntree);
    }


    public Set<Repas> getLstRepas() {
        return Collections.unmodifiableSet(this.lstRepas);
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

    public void ajouterRepas(final Repas repas) throws PatientException {
        this.controlerRegimeAlimentaire(repas);
        this.lstRepas.add(repas);
    }

    public List<RegimeAlimentaire> getLstRegimeAlimentaire() {
        return Collections.unmodifiableList(lstRegimeAlimentaire);
    }

    private void controlerRegimeAlimentaire(final Repas repas) throws PatientException {
        if (Objects.isNull(repas)) {
            throw new PatientException(ConstantesMetier.PATIENT_REPAS_REGIME_ALIMENTAIRE_EXCEPTION);
        }
//        //TODO sout
//        System.out.println("this.lstRegimeAlimentaire : " + this.lstRegimeAlimentaire);
//        System.out.println("repas.getLstRegimeAlimentaire() : " + repas.getLstRegimeAlimentaire());
        if (!repas.getLstRegimeAlimentaire().containsAll(this.lstRegimeAlimentaire)) {
            throw new PatientException(ConstantesMetier.PATIENT_REPAS_REGIME_ALIMENTAIRE_NON_COMPATIBLE_EXCEPTION);
        }
        this.lstRepas.add(repas);
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
        if (Objects.isNull(nom) || nom.isBlank()) {
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
        if (Objects.isNull(prenom) || prenom.isBlank()) {
            throw new PatientException(ConstantesMetier.MSG_PATIENT_PRENOM_EXCEPTION);
        }
        if (prenom.length() < ConstantesMetier.PATIENT_PRENOM_TAILLE_MIN || prenom.length() > ConstantesMetier.PATIENT_PRENOM_TAILLE_MAX) {
            throw new PatientException(ConstantesMetier.MSG_PATIENT_PRENOM_LENGTH_EXCEPTION);
        }
        this.prenom = prenom;
    }

    public LocalDate getDateEntree() {
        return this.dateEntree;
    }

    public void setDateEntree(final LocalDate dateEntree) throws PatientException {
        if (Objects.isNull(dateEntree)) {
            throw new PatientException(ConstantesMetier.MSG_PATIENT_DATE_ENTREE_EXCEPTION);
        }
        if (dateEntree.isAfter(LocalDate.now())) {
            throw new PatientException(ConstantesMetier.MSG_PATIENT_DATE_ENTREE_INVALIDE_EXCEPTION);
        }
        this.dateEntree = dateEntree;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Patient)) return false;
        Patient patient = (Patient) o;
        return Objects.equals(numSecu, patient.numSecu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numSecu);
    }
}
