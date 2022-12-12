package net.ent.etrs.KL16_REPAS_PATIENT_SANTOS.model.entities;

import net.ent.etrs.KL16_REPAS_PATIENT_SANTOS.model.entities.exceptions.PatientConstructionException;
import net.ent.etrs.KL16_REPAS_PATIENT_SANTOS.model.entities.exceptions.RepasException;
import net.ent.etrs.KL16_REPAS_PATIENT_SANTOS.model.references.ConstantesMetier;
import net.ent.etrs.KL16_REPAS_PATIENT_SANTOS.model.references.RegimeAlimentaire;

import java.time.LocalDate;
import java.util.*;

public class Patient {
    private LocalDate dateEntre ;

    private final List<RegimeAlimentaire> lstRegimeAlimentaire = new ArrayList<>();
    private final List<Repas> lstRepas = new ArrayList<>();
    private UUID id = UUID.randomUUID();
    private String numSecu;
    private String nom ;
    private String prenom ;

    public Patient(LocalDate dateEntre, String numSecu, String nom, String prenom) throws PatientConstructionException {
        setDateEntre(dateEntre);
         setNumSecu(numSecu);
         setNom(nom);
         setPrenom(prenom);
    }

    public List<RegimeAlimentaire> getLstRegimeAlimentaire() {
        return Collections.unmodifiableList(this.lstRegimeAlimentaire);
    }

    public List<Repas> getLstRepas() {
        return Collections.unmodifiableList(this.lstRepas);
    }

    public LocalDate getDateEntre() {
        return this.dateEntre;
    }

    public UUID getId() {
        return id;
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

    public void setDateEntre(LocalDate dateEntre) throws PatientConstructionException {
        if (dateEntre == null || dateEntre.isBefore(LocalDate.now().plusDays(1))) {
            throw new PatientConstructionException(ConstantesMetier.MSG_PATIENT_DATE_ENTREE_INVALIDE_EXCEPTION);
        }


        this.dateEntre = dateEntre;
    }

    public void setNumSecu(String numSecu) throws PatientConstructionException {
        if (numSecu == null || numSecu.isBlank() || numSecu.length() !=ConstantesMetier.PATIENT_NUM_SECU_TAILLE) {
            throw new PatientConstructionException(ConstantesMetier.MSG_PATIENT_NUM_SECU_EXCEPTION);
        }

        this.numSecu = numSecu;
    }

    public void setNom(String nom) throws PatientConstructionException {
        if (nom == null || nom.isBlank()|| nom.length()<ConstantesMetier.PATIENT_NOM_TAILLE_MIN || nom.length()>ConstantesMetier.PATIENT_NOM_TAILLE_MAX) {
            throw new PatientConstructionException(ConstantesMetier.MSG_PATIENT_NOM_EXCEPTION);
        }

        this.nom = nom;
    }

    public void setPrenom(String prenom) throws PatientConstructionException {
        if (prenom == null || prenom.isBlank()|| prenom.length()<ConstantesMetier.PATIENT_PRENOM_TAILLE_MIN || prenom.length()>ConstantesMetier.PATIENT_NOM_TAILLE_MAX) {
            throw new PatientConstructionException(ConstantesMetier.MSG_PATIENT_PRENOM_EXCEPTION);
        }

        this.prenom = prenom;
    }
    public void  ajouterRepas (Repas repas) throws RepasException {
        if (repas == null   ) {
            throw new RepasException(ConstantesMetier.REPAS_TYPE_REPAS_EXCEPTION);
        }
        this.lstRepas.add(repas);

    }

    private void controleRegimeAlimentaire(Repas repas) throws RepasException {
        //if (repas.getLstRegimeAlimentaire().contains(this.lstRegimeAlimentaire.get(1)) ) {


//        for (int i = 0; i>repas.getLstRegimeAlimentaire().size(){
//            int i =0 ;
//
//            if (repas.getLstRegimeAlimentaire().contains(this.lstRegimeAlimentaire.get(i));
//            i++;
//        }


    }

    public void  ajouterRegimeAlimentaire (RegimeAlimentaire regimeAlimentaire) throws RepasException {
        if (regimeAlimentaire == null || this.lstRegimeAlimentaire.contains(regimeAlimentaire)  ) {
            throw new RepasException(ConstantesMetier.PATIENT_REPAS_REGIME_ALIMENTAIRE_EXCEPTION);
        }
        this.lstRegimeAlimentaire.add(regimeAlimentaire);

    }


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
                "dateEntre=" + dateEntre +
                ", lstRegimeAlimentaire=" + lstRegimeAlimentaire +
                ", lstRepas=" + lstRepas +
                ", id=" + id +
                ", numSecu='" + numSecu + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                '}';
    }

}
