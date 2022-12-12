package net.ent.etrs.repaspatient.model.entities;

import net.ent.etrs.repaspatient.model.entities.exceptions.PatientConstructionException;
import net.ent.etrs.repaspatient.model.entities.references.ConstantesMetier;
import net.ent.etrs.repaspatient.model.entities.references.RegimeAlimentaire;

import java.time.LocalDate;
import java.util.ArrayList;
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

    //Constructeur

    protected Patient(LocalDate dateEntree, String numSecu, String nom, String prenom) {
        this.setDateEntree(dateEntree);
        this.setNumSecu(numSecu);
        this.setNom(nom);
        this.setPrenom(prenom);
        this.id = UUID.randomUUID().toString();
        this.lstRegimeAlimentaire = new ArrayList<>();
        this.lstRepas = new ArrayList<>();
    }

    //Accesseurs


    public LocalDate getDateEntree() {
        return dateEntree;
    }

    public void setDateEntree(LocalDate dateEntree) {
        this.dateEntree = dateEntree;
    }

    public List<Repas> getLstRepas() {
        return lstRepas;
    }

    public List<RegimeAlimentaire> getLstRegimeAlimentaire() {
        return lstRegimeAlimentaire;
    }

    public String getId() {
        return id;
    }


    public String getNumSecu() {
        return numSecu;
    }

    public void setNumSecu(String numSecu) {
        this.numSecu = numSecu;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    //equals/hashcode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Patient)) return false;
        Patient patient = (Patient) o;
        return Objects.equals(getDateEntree(), patient.getDateEntree()) && Objects.equals(getLstRepas(), patient.getLstRepas()) && Objects.equals(getLstRegimeAlimentaire(), patient.getLstRegimeAlimentaire()) && Objects.equals(getId(), patient.getId()) && Objects.equals(getNumSecu(), patient.getNumSecu()) && Objects.equals(getNom(), patient.getNom()) && Objects.equals(getPrenom(), patient.getPrenom());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDateEntree(), getLstRepas(), getLstRegimeAlimentaire(), getId(), getNumSecu(), getNom(), getPrenom());
    }

    //autres méthodes

    public void ajouterRepas(Repas repas) throws PatientConstructionException {
        try {
            controllerRegimeAlimentaire(repas);
            this.lstRepas.add(repas);
        } catch (PatientConstructionException e) {
            throw new PatientConstructionException(e.getMessage(), e.getCause());
        }
    }

    public void ajouterRegimeAlimentaire(RegimeAlimentaire regAl) throws PatientConstructionException {
        if (lstRegimeAlimentaire.contains(regAl)){
            throw new PatientConstructionException(ConstantesMetier.MSG_PATIENT_REGIME_ALIMENTAIRE_EXIST_EXCEPTION);
        }
        this.lstRegimeAlimentaire.add(regAl);
    }

    private void controllerRegimeAlimentaire(Repas repas) throws PatientConstructionException {
        int verif = 0;
        if (!getLstRegimeAlimentaire().isEmpty()) {
            for (RegimeAlimentaire regAl : this.lstRegimeAlimentaire) {
                verif = 0;
                for (RegimeAlimentaire regAlRepas : repas.getLstRegimeRepas()) {
                    if (regAl.equals(regAlRepas)) {
                        verif++;
                    }
                }
                if (verif == 0) {
                    throw new PatientConstructionException(ConstantesMetier.PATIENT_REPAS_REGIME_ALIMENTAIRE_EXCEPTION);
                }
            }
        }
    }
}
