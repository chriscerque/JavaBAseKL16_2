package net.ent.etrs.test.model.entities;

import net.ent.etrs.test.model.entities.exception.PatientException;
import net.ent.etrs.test.model.entities.references.ConstantesMetier;
import net.ent.etrs.test.model.entities.references.RegimeAlimentaire;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Patient {

    private LocalDate dateEntree;

    private List<Repas> lstRepas = new ArrayList<>();

    private List<RegimeAlimentaire> lstRegimeAlimentaire = new ArrayList<>();

    private final String id = UUID.randomUUID().toString();

    private String numSecu;

    private String nom;

    private String prenom;


    /**
     * Constructeur de la classe Patient
     * @param numSecu
     * @param nom
     * @param prenom
     * @param dateEntree
     */
    protected Patient(final String numSecu, final String nom, final String prenom, final LocalDate dateEntree) throws PatientException {
        this.setDateEntree(dateEntree);
        this.setNumSecu(numSecu);
        this.setNom(nom);
        this.setPrenom(prenom);
    }

    //Getter & setter
    public List<Repas> getLstRepas() {
        return this.lstRepas;
    }

    public List<RegimeAlimentaire> getLstRegimeAlimentaire() {
        return lstRegimeAlimentaire;
    }

    public String getId() {
        return this.id;
    }

    public String getNumSecu() {
        return this.numSecu;
    }

    public void setNumSecu(final String numSecu) throws PatientException {
        if(numSecu == null){
            throw new PatientException(ConstantesMetier.MSG_PATIENT_NUM_SECU_EXCEPTION);
        }
        if(numSecu.length() != ConstantesMetier.PATIENT_NUM_SECU_TAILLE){
            throw new PatientException(ConstantesMetier.MSG_PATIENT_NUM_SECU_LENGTH_EXCEPTION);
        }
        this.numSecu = numSecu;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(final String nom) throws PatientException {
        if(nom == null){
            throw new PatientException(ConstantesMetier.MSG_PATIENT_NOM_EXCEPTION);
        }
        if(nom.length() < ConstantesMetier.PATIENT_NOM_TAILLE_MIN || nom.length() > ConstantesMetier.PATIENT_NOM_TAILLE_MAX){
            throw new PatientException(ConstantesMetier.MSG_PATIENT_NOM_LENGTH_EXCEPTION);
        }
        this.nom = nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public void setPrenom(final String prenom) throws PatientException {
        if(prenom == null){
            throw new PatientException(ConstantesMetier.MSG_PATIENT_PRENOM_EXCEPTION);
        }
        if(prenom.length() < ConstantesMetier.PATIENT_PRENOM_TAILLE_MIN || prenom.length() > ConstantesMetier.PATIENT_PRENOM_TAILLE_MAX){
            throw new PatientException(ConstantesMetier.MSG_PATIENT_PRENOM_LENGTH_EXCEPTION);
        }
        this.prenom = prenom;
    }

    public LocalDate getDateEntree() {
        return this.dateEntree;
    }

    public void setDateEntree(final LocalDate dateEntree) throws PatientException {
        if(dateEntree == null){
            throw new PatientException(ConstantesMetier.MSG_PATIENT_DATE_ENTREE_EXCEPTION);
        }
        if(dateEntree.isAfter(LocalDate.now())){
            throw new PatientException(ConstantesMetier.MSG_PATIENT_DATE_ENTREE_INVALIDE_EXCEPTION);
        }
        this.dateEntree = dateEntree;
    }

    //Equals / HashCode / ToString


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
                ", id='" + id + '\'' +
                ", numSecu='" + numSecu + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                '}';
    }

    //Autres méthodes

    /**
     * Controle si le régime que l'on veut rentrer n'est pas déjà présent chez un patient.
     * @param repas
     * @throws PatientException
     */
    private void controlerRegimeAlimentaire(final Repas repas) throws PatientException {
        if(!repas.getLstRegimeAlimentaire().containsAll(this.lstRegimeAlimentaire)){
            throw new PatientException(ConstantesMetier.PATIENT_REPAS_REGIME_ALIMENTAIRE_EXCEPTION);
        }
    }

    /**
     * Ajoute un repas à un patient.
     * @param repas
     * @throws PatientException
     */
    public void ajouterRepas(final Repas repas) throws PatientException {
        if(repas == null){
            throw new PatientException(ConstantesMetier.MSG_PATIENT_REPAS_NULL);
        }
        controlerRegimeAlimentaire(repas);
        this.lstRepas.add(repas);
    }

    /**
     * Ajoute un régime alimentaire à un patient.
     * @param regimeAlimentaire
     * @throws PatientException
     */
    public void ajouterRegimeAlimentaire(final RegimeAlimentaire regimeAlimentaire) throws PatientException {
        if(regimeAlimentaire == null){
            throw new PatientException(ConstantesMetier.MSG_PATIENT_REGIME_ALIMENTAIRE_EXCEPTION);
        }
        if(this.lstRegimeAlimentaire.contains(regimeAlimentaire)){
            throw new PatientException(ConstantesMetier.MSG_PATIENT_REGIME_ALIMENTAIRE_EXIST_EXCEPTION);
        }
        this.lstRegimeAlimentaire.add(regimeAlimentaire);
    }





}
