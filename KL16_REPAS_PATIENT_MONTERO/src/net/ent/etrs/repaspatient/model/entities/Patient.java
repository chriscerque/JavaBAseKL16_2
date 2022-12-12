package net.ent.etrs.repaspatient.model.entities;

import net.ent.etrs.repaspatient.model.entities.exceptions.PatientException;
import net.ent.etrs.repaspatient.model.entities.references.ConstantesMetier;
import net.ent.etrs.repaspatient.model.entities.references.RegimeAlimentaire;

import java.time.LocalDate;
import java.util.*;

public class Patient implements Comparable<Patient> {

    //attribut(s)
    private final String id;
    List<Repas> lstRepas = new ArrayList<>();
    List<RegimeAlimentaire> lstRegimeAlimentaire = new ArrayList<>();
    private String numSecu;
    private String nom;
    private String prenom;
    private LocalDate dateEntree;

    //constructeur(s)

    protected Patient(final String numSecu, final String nom, final String prenom, final LocalDate dateEntree) throws PatientException {
        id = UUID.randomUUID().toString();
        this.setNumSecu(numSecu);
        this.setNom(nom);
        this.setPrenom(prenom);
        this.setDateEntree(dateEntree);
    }


    //getter et setter

    public String getId() {
        return this.id;
    }

    public String getNumSecu() {
        return this.numSecu;
    }

    public void setNumSecu(final String numSecu) throws PatientException {
        if (Objects.isNull(numSecu) || numSecu.isBlank()) {
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

    public List<Repas> getLstRepas() {
        return Collections.unmodifiableList(this.lstRepas);
    }

    public void setLstRepas(final List<Repas> lstRepas) {
        this.lstRepas = lstRepas;
    }

    public void ajouterRepas(final Repas repas) throws PatientException {
        if (Objects.isNull(repas)) {
            throw new PatientException(ConstantesMetier.PATIENT_REPAS_REGIME_ALIMENTAIRE_EXCEPTION);
        }
        if (controlerRegimeAlimentaire(repas) == false) {
            throw new PatientException(ConstantesMetier.PATIENT_REPAS_REGIME_ALIMENTAIRE_EXCEPTION);
        }
        this.lstRepas.add(repas);
    }

    /**
     * Verifier le regime alimentaire du repas.
     * cest a dire on prend tous les elements qui composent le regime alimentaire du repas si
     * lun deux ne fait pas partis du regime alimentaire du patient cest un echec sinon cest un succes
     *
     * @param repas
     * @return
     */
    private boolean controlerRegimeAlimentaire(final Repas repas) {
       /* for (RegimeAlimentaire regimeAlimentaire : repas.getLstRegimeAlimentaires()) {
            if (!this.lstRegimeAlimentaire.contains(regimeAlimentaire)) {
                return false;
            }
        }*/
        return true;
    }

    public List<RegimeAlimentaire> getLstRegimeAlimentaire() {
        return Collections.unmodifiableList(this.lstRegimeAlimentaire);
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


    //egal et hash

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

    //to string

    @Override
    public String toString() {
        return "Patient{" +
                "id='" + id + '\'' +
                ", numSecu='" + numSecu + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", dateEntree=" + dateEntree +
                '}';
    }

    @Override
    public int compareTo(final Patient o) {
        return this.getNumSecu().compareTo(o.getNumSecu());
    }


    //autre(s) methode(s)


}
