package net.ent.etrs.repaspatient.model.entities;

import net.ent.etrs.repaspatient.model.entities.exceptions.RegimeAlimentaireException;
import net.ent.etrs.repaspatient.model.entities.references.ConstantesMetier;
import net.ent.etrs.repaspatient.model.entities.references.RegimeAlimentaire;
import net.ent.etrs.repaspatient.model.entities.references.TypeRepas;

import java.time.LocalDate;
import java.util.*;

public class Repas implements Comparable<Repas>{


        private String id ;

        private LocalDate dateRepas;

        private TypeRepas typeRepas;

        private List<RegimeAlimentaire> lstRegimeAlimentaire = new ArrayList<>();

        protected Repas(LocalDate dateRepas, TypeRepas typeRepas) {
            this.setDateRepas(dateRepas);
            this.setTypeRepas(typeRepas);
            this.id = UUID.randomUUID().toString();
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public LocalDate getDateRepas() {
            return dateRepas;
        }

        public void setDateRepas(LocalDate dateRepas) {
            this.dateRepas = dateRepas;
        }

        public TypeRepas getTypeRepas() {
            return typeRepas;
        }

        public void setTypeRepas(TypeRepas typeRepas) {
            this.typeRepas = typeRepas;
        }

        public List<RegimeAlimentaire> getLstRegimeAlimentaire() {
            return Collections.unmodifiableList(lstRegimeAlimentaire);
        }

        public void ajouterRegimeAlimentaire(RegimeAlimentaire ra) throws RegimeAlimentaireException {
            if (ra == null){
                throw new RegimeAlimentaireException(ConstantesMetier.REGIME_ALIMENTAIRE_VIDE);
            }
            if (lstRegimeAlimentaire.contains(ra)){
                throw new RegimeAlimentaireException(ConstantesMetier.MSG_PATIENT_REGIME_ALIMENTAIRE_EXIST_EXCEPTION);
            }
            lstRegimeAlimentaire.add(ra);
        }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Repas repas = (Repas) o;
        return Objects.equals(getDateRepas(), repas.getDateRepas()) && getTypeRepas() == repas.getTypeRepas();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDateRepas(), getTypeRepas());
    }

    @Override
        public String toString() {
            return "Repas{" +
                    "id='" + id + '\'' +
                    ", dateRepas=" + dateRepas +
                    ", typeRepas=" + typeRepas +
                    '}';
        }

    @Override
    public int compareTo(Repas o) {
        return this.getDateRepas().compareTo(o.getDateRepas());
    }
}



