package net.ent.etrs.repaspatient.model.entities;

import net.ent.etrs.repaspatient.model.entities.exceptions.PatientException;
import net.ent.etrs.repaspatient.model.entities.references.RegimeAlimentaire;

import java.time.LocalDate;
import java.util.*;

public class Patient {

    //ATTRIBUTS
    private LocalDate dateEntree;
    private List<Repas> lstRepas = new ArrayList<>();
    private Set<RegimeAlimentaire> lstRegimeAlimentaire = new HashSet<>();
    private String id = UUID.randomUUID().toString();
    private String numSecu;
    private String nom;
    private String prenom;

    //CONSTRUCTEUR

    protected Patient(LocalDate dateEntree, String numSecu, String nom, String prenom) throws PatientException {
        this.setNom(nom);
        this.setDateEntree(dateEntree);
        this.setNumSecu(numSecu);
        this.setPrenom(prenom);
    }

    //GETTER & SETTER

    public LocalDate getDateEntree() {
        return this.dateEntree;
    }

    public void setDateEntree(final LocalDate dateEntree) throws PatientException {
        if(Objects.isNull(this.dateEntree)){
            throw new PatientException(ConstanteModel.DATE_NAISSANCE_NULL_EXCEPTION);
        }
        if (this.dateEntree.isAfter(LocalDate.now())){
            throw new PatientException(ConstanteModel.DATE_NAISSANCE_INCORRECT_EXCEPTION);
        }
        this.dateEntree = dateEntree;
    }

    public String getNumSecu() {
        return this.numSecu;
    }

    public void setNumSecu(final String numSecu) throws PatientException {
        if (Objects.isNull(this.numSecu)){
            throw new PatientException(ConstanteModel.NUM_SECU_NULL_EXCEPTION);
        }
        if (this.numSecu.length() != ConstanteModel.TAILLE_NUM_SECU){
            throw new PatientException(ConstanteModel.NUM_SECU_TAILLE_EXCEPTION);
        }
        this.numSecu = numSecu;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(final String nom) throws PatientException {
        if (Objects.isNull(this.nom)){
            throw new PatientException(ConstanteModel.NOM_NULL_EXCEPTION);
        }
        if (this.nom.length() < ConstanteModel.TAILLE_MINI_NOM_PRENOM || this.nom.length() > ConstanteModel.TAILLE_MAXI_NOM_PRENOM){
            throw new PatientException(ConstanteModel.NOM_PATIENT_EXCEPTION);
        }
        this.nom = nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public void setPrenom(String prenom) throws PatientException {
        if (Objects.isNull(this.prenom)){
            throw new PatientException(ConstanteModel.PRENOM_NULL_EXCEPTION);
        }
        if (this.prenom.length() < ConstanteModel.TAILLE_MINI_NOM_PRENOM || this.prenom.length() > ConstanteModel.TAILLE_MAXI_NOM_PRENOM){
            throw new PatientException(ConstanteModel.PRENOM_PATIENT_EXCEPTION);
        }
        this.prenom = prenom;
    }

    public List<Repas> getLstRepas() {
        return Collections.unmodifiableList(lstRepas);
    }

    /**
     *
     * @return
     */
    public Set<RegimeAlimentaire> getLstRegimeAlimentaire() {
        return Collections.unmodifiableSet(lstRegimeAlimentaire);
    }

    public String getId() {
        return this.id;
    }

    //AUTRES METHODES

    /**
     * Controle le regime alimentaire d'un repas prit en paramètre
     * @param repas
     * @throws PatientException
     */
    private void controlerRegimeAlimentaire(Repas repas) throws PatientException {
        for(RegimeAlimentaire r : lstRegimeAlimentaire){
            if (r.getLibelle().equals(r.getLibelle())){
                throw new PatientException(ConstanteModel.REGIME_ALIMENTAIRE_NON_RESPECTE_EXCEPTION);
            }
        }
    }

    /**
     * Ajoute un repas à la liste et controle si le regime alimentaire est respecté
     * @param repas
     * @throws PatientException
     */
    public void ajouterRepas(Repas repas) throws PatientException {
        controlerRegimeAlimentaire(repas);
        lstRepas.add(repas);
    }

    /**
     * Ajoute un regime alimentaire à la liste concernée
     * @param regimeAlimentaire
     */
    public void ajouterRegimeAlimentaire(RegimeAlimentaire regimeAlimentaire){
        lstRegimeAlimentaire.add(regimeAlimentaire);
    }

    //EQUALS & HASHCODE + TOSTRING
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return Objects.equals(getDateEntree(), patient.getDateEntree()) && Objects.equals(getLstRepas(), patient.getLstRepas()) && Objects.equals(getLstRegimeAlimentaire(), patient.getLstRegimeAlimentaire()) && Objects.equals(getId(), patient.getId()) && Objects.equals(getNumSecu(), patient.getNumSecu()) && Objects.equals(getNom(), patient.getNom()) && Objects.equals(getPrenom(), patient.getPrenom());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDateEntree(), getLstRepas(), getLstRegimeAlimentaire(), getId(), getNumSecu(), getNom(), getPrenom());
    }

    @Override
    public String toString() {
        return "Patient{" +
                numSecu +
                nom +
                prenom +
                ", liste des Regimes Alimentaire :" + '\'' + lstRegimeAlimentaire +
                lstRepas +
                 + '\'' +
                "------------------------------------------------------------------------------------------------------------" +
                 '\'' +
                '}';
    }
}
