package net.ent.etrs.repaspatient.model.entities;

import net.ent.etrs.repaspatient.model.entities.exceptions.PatientConstructionException;
import net.ent.etrs.repaspatient.model.entities.exceptions.PatientException;
import net.ent.etrs.repaspatient.model.entities.exceptions.RepasException;
import net.ent.etrs.repaspatient.model.entities.references.ConstantesMetier;
import net.ent.etrs.repaspatient.model.entities.references.RegimeAlimentaire;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Patient {
    private LocalDate dateEntree;
    private List<Repas> lstRepas;
    private List<RegimeAlimentaire> lstRegimeAlimentaire;
    private String id;
    private String numSecu;
    private String nom;
    private String prenom;


    // CONSTRUCTOR
    protected Patient(final LocalDate dateEntree, final String numSecu, final String nom, final String prenom) {
        this.dateEntree = dateEntree;
        this.numSecu = numSecu;
        this.nom = nom;
        this.prenom = prenom;
        this.id = String.valueOf(UUID.randomUUID());
    }


    // GETTERS
    public LocalDate getDateEntree() {
        return this.dateEntree;
    }
    public List<Repas> getLstRepas() {
        return this.lstRepas;
    }
    public List<RegimeAlimentaire> getLstRegimeAlimentaire() {
        return this.lstRegimeAlimentaire;
    }
    public String getId() {
        return this.id;
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

    // SETTERS
    public void setDateEntree(final LocalDate dateEntree) throws PatientConstructionException {
        if (Objects.isNull(dateEntree)) {
            throw new PatientConstructionException(ConstantesMetier.MSG_PATIENT_DATE_ENTREE_EXCEPTION);
        }
        if (dateEntree.isAfter(LocalDate.now())) {
            throw new PatientConstructionException(ConstantesMetier.MSG_PATIENT_DATE_ENTREE_INVALIDE_EXCEPTION);
        }
        this.dateEntree = dateEntree;
    }
    public void setNumSecu(final String numSecu) throws PatientConstructionException {
        if (Objects.isNull(numSecu) ) {
            throw new PatientConstructionException(ConstantesMetier.MSG_PATIENT_NUM_SECU_EXCEPTION);
        }
        if ( numSecu.length() != ConstantesMetier.PATIENT_NUM_SECU_TAILLE) {
            throw new PatientConstructionException(ConstantesMetier.MSG_PATIENT_NUM_SECU_LENGTH_EXCEPTION);
        }
        this.numSecu = numSecu;
    }
    public void setNom(final String nom) throws PatientConstructionException {
        if (Objects.isNull(nom)) {
            throw new PatientConstructionException(ConstantesMetier.MSG_PATIENT_NOM_EXCEPTION);
        }
        if (nom.length() < ConstantesMetier.PATIENT_NOM_TAILLE_MIN | nom.length() > ConstantesMetier.PATIENT_NOM_TAILLE_MAX) {
            throw new PatientConstructionException(ConstantesMetier.MSG_PATIENT_NOM_LENGTH_EXCEPTION);
        }
        // Je s'appelle Groott
        this.nom = nom;
    }
    public void setPrenom(final String prenom) throws PatientConstructionException {
        if (Objects.isNull(prenom)) {
            throw new PatientConstructionException(ConstantesMetier.MSG_PATIENT_PRENOM_EXCEPTION);
        }
        if ( prenom.length() < ConstantesMetier.PATIENT_PRENOM_TAILLE_MIN | prenom.length() > ConstantesMetier.PATIENT_PRENOM_TAILLE_MAX) {
            throw new PatientConstructionException(ConstantesMetier.MSG_PATIENT_PRENOM_LENGTH_EXCEPTION);
        }
        this.prenom = prenom;
    }


    public void ajouterRegimeAlimentaire(final RegimeAlimentaire ra) throws RepasException {
        if (Objects.isNull(ra)){
            throw new RepasException(ConstantesMetier.MSG_PATIENT_REGIME_ALIMENTAIRE_EXCEPTION);
        }
        if(this.lstRegimeAlimentaire.contains(ra)){
            throw new RepasException(ConstantesMetier.MSG_PATIENT_REGIME_ALIMENTAIRE_EXIST_EXCEPTION);
        }
        lstRegimeAlimentaire.add(ra);
    }

    private void controleRegimeAlimentaire(final Repas repas) throws PatientException {
        if(!this.lstRegimeAlimentaire.contains(repas)){
            throw new PatientException(ConstantesMetier.PATIENT_REPAS_REGIME_ALIMENTAIRE_NON_COMPATIBLE_EXCEPTION);
        }
    }

    public void ajouterRepas(final Repas repas) throws PatientConstructionException, PatientException {
        if (Objects.isNull(repas)) {
            throw new PatientConstructionException(ConstantesMetier.PATIENT_REPAS_REGIME_ALIMENTAIRE_EXCEPTION);
        }
        controleRegimeAlimentaire(repas);
        lstRepas.add(repas);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return Objects.equals(dateEntree, patient.dateEntree) && Objects.equals(lstRepas, patient.lstRepas) && Objects.equals(lstRegimeAlimentaire, patient.lstRegimeAlimentaire) && Objects.equals(id, patient.id) && Objects.equals(numSecu, patient.numSecu) && Objects.equals(nom, patient.nom) && Objects.equals(prenom, patient.prenom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateEntree, lstRepas, lstRegimeAlimentaire, id, numSecu, nom, prenom);
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
}
