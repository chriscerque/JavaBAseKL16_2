package net.ent.etrs.repaspatient.model.daos;
import net.ent.etrs.repaspatient.model.daos.exceptions.DaoException;
import net.ent.etrs.repaspatient.model.entities.Patient;
import net.ent.etrs.repaspatient.model.entities.references.ConstantesMetier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class PatientMemDao implements IPatientMemDao {

    List<Patient> persistence = new ArrayList<>();

    @Override
    public List<Patient> readAll() {
        return Collections.unmodifiableList(persistence);

    }

    @Override
    public Patient update(Patient patient) throws DaoException {
        if (Objects.isNull(patient)) {
            throw new DaoException(ConstantesMetier.MSG_DAO_MISE_A_JOUR_PATIENT_NULL);
        }
        if (!persistence.contains(patient)) {
            throw new DaoException(ConstantesMetier.MSG_DAO_PERSITANCE_PATIENT_EXISTANT);
        }

        Patient Patient = persistence.set(persistence.indexOf(patient), patient);

        return Patient;
    }

    @Override
    public void delete(Patient patient) throws DaoException {

        if (Objects.isNull(patient)) {
            throw new DaoException(ConstantesMetier.MSG_DAO_PERSITANCE_PATIENT_NULL);
        }

        List<Patient> listePatientTemporaire = new ArrayList<>(persistence);

        for (Patient r : listePatientTemporaire) {
            if (patient.getId().equals(patient)) {
                persistence.remove(r);
            }
        }
    }

    @Override
    public void deleteByKey(String id) throws DaoException{

    }

    @Override
    public Patient create(Patient patient) throws DaoException {
        if (Objects.isNull(patient)) {
            throw new DaoException(ConstantesMetier.MSG_DAO_PERSITANCE_PATIENT_NULL);
        }
        if (persistence.contains(patient)) {
            throw new DaoException(ConstantesMetier.MSG_DAO_PERSITANCE_PATIENT_EXISTANT);
        }
        persistence.add(patient);
        return patient;
    }

    @Override
    public boolean exist(final Patient patient) throws DaoException {
        try {
            return this.persistence.contains(patient);
        } catch (Exception e) {
            throw new DaoException(ConstantesMetier.MSG_DAO_PERSITANCE_PATIENT_EXISTANT, e);
        }
    }

    @Override
    public Patient read(String id) throws DaoException {
        if (Objects.isNull(id)) {
            throw new DaoException(ConstantesMetier.MSG_DAO_PERSITANCE_PATIENT_NULL);
        }
        List<Patient> listePatientTemporaire = new ArrayList<>(persistence);

        for (Patient patient : listePatientTemporaire) {
            if (id.equals(patient.getId())) {
                return patient;
            }
        }
        throw new DaoException(ConstantesMetier.MSG_DAO_PERSITANCE_PATIENT_EXISTANT);
    }

//    @Override
//    public void init() throws BusinessException {
//
//        JavaNetUriAccess repasDao;
//        try {
//            Repas r1 = EntitiesFactory.fabriquerRepas(LocalDate.now().plusDays(4), TypeRepas.PETIT_DEJEUNER);
//            r1.ajouterRegimeAlimentaire(RegimeAlimentaire.MIXE);
//            r1.ajouterRegimeAlimentaire(RegimeAlimentaire.SANS_SEL);
//            Repas r2 = EntitiesFactory.fabriquerRepas(LocalDate.now().plusDays(4), TypeRepas.DEJEUNER);
//            r2.ajouterRegimeAlimentaire(RegimeAlimentaire.VEGAN);
//            r2.ajouterRegimeAlimentaire(RegimeAlimentaire.SANS_SEL);
//            Repas r3 = EntitiesFactory.fabriquerRepas(LocalDate.now().plusDays(4), TypeRepas.DINER);
//            r3.ajouterRegimeAlimentaire(RegimeAlimentaire.VEGETARIEN);
//            Repas r4 = EntitiesFactory.fabriquerRepas(LocalDate.now().plusDays(5), TypeRepas.PETIT_DEJEUNER);
//            r4.ajouterRegimeAlimentaire(RegimeAlimentaire.SANS_SEL);
//            Repas r5 = EntitiesFactory.fabriquerRepas(LocalDate.now().plusDays(5), TypeRepas.DEJEUNER);
//            r5.ajouterRegimeAlimentaire(RegimeAlimentaire.MIXE);
//            r5.ajouterRegimeAlimentaire(RegimeAlimentaire.VEGETARIEN);
//            Repas r6 = EntitiesFactory.fabriquerRepas(LocalDate.now().plusDays(5), TypeRepas.DINER);
//            r6.ajouterRegimeAlimentaire(RegimeAlimentaire.DIABETIQUE);
//            r6.ajouterRegimeAlimentaire(RegimeAlimentaire.SANS_SEL);
//            Repas r7 = EntitiesFactory.fabriquerRepas(LocalDate.now().plusDays(6), TypeRepas.PETIT_DEJEUNER);
//            r7.ajouterRegimeAlimentaire(RegimeAlimentaire.DIABETIQUE);
//            Repas r8 = EntitiesFactory.fabriquerRepas(LocalDate.now().plusDays(6), TypeRepas.DEJEUNER);
//            r8.ajouterRegimeAlimentaire(RegimeAlimentaire.SANS_SEL);
//
//            this.repasDao.create(r1);
//            this.repasDao.create(r2);
//            this.repasDao.create(r3);
//            this.repasDao.create(r4);
//            this.repasDao.create(r5);
//            this.repasDao.create(r6);
//            this.repasDao.create(r7);
//            this.repasDao.create(r8);
//
//        } catch (RepasConstructionException | DaoException e) {
//            throw new BusinessException(e.getMessage(), e);
//        }
//
//        try {
//            Patient miney = EntitiesFactory.fabriquerPatient("12345", "Miney", "Bernard", LocalDate.of(1970, Month.DECEMBER, 12));
//            Patient haddock = EntitiesFactory.fabriquerPatient("12789", "HADDOCK", "Archibald", LocalDate.of(1970, Month.APRIL, 1));
//            Patient tintin = EntitiesFactory.fabriquerPatient("12348", "TINTIN", "Mar", LocalDate.of(1970, Month.FEBRUARY, 10));
//            Patient tournesol = EntitiesFactory.fabriquerPatient("27789", "TOURNESOL", "Tryphon", LocalDate.of(1970, Month.MAY, 20));
//            Patient tombR = EntitiesFactory.fabriquerPatient("11111", "TOMB", "Raider", LocalDate.of(1970, Month.MAY, 20));
//            Patient tombP = EntitiesFactory.fabriquerPatient("11112", "TOMB", "Parterre", LocalDate.of(1980, Month.JUNE, 14));
//
//            Repas r1 = EntitiesFactory.fabriquerRepas(LocalDate.now().plusDays(2), TypeRepas.PETIT_DEJEUNER);
//            r1.ajouterRegimeAlimentaire(RegimeAlimentaire.MIXE);
//            Repas r2 = EntitiesFactory.fabriquerRepas(LocalDate.now().plusDays(2), TypeRepas.DEJEUNER);
//            r2.ajouterRegimeAlimentaire(RegimeAlimentaire.MIXE);
//            r2.ajouterRegimeAlimentaire(RegimeAlimentaire.SANS_SEL);
//            Repas r3 = EntitiesFactory.fabriquerRepas(LocalDate.now().plusDays(2), TypeRepas.DINER);
//            r3.ajouterRegimeAlimentaire(RegimeAlimentaire.MIXE);
//            r3.ajouterRegimeAlimentaire(RegimeAlimentaire.VEGETARIEN);
//            Repas r4 = EntitiesFactory.fabriquerRepas(LocalDate.now().plusDays(1), TypeRepas.PETIT_DEJEUNER);
//            r4.ajouterRegimeAlimentaire(RegimeAlimentaire.MIXE);
//            r4.ajouterRegimeAlimentaire(RegimeAlimentaire.VEGAN);
//            Repas r5 = EntitiesFactory.fabriquerRepas(LocalDate.now().plusDays(1), TypeRepas.DEJEUNER);
//            r5.ajouterRegimeAlimentaire(RegimeAlimentaire.MIXE);
//            r5.ajouterRegimeAlimentaire(RegimeAlimentaire.DIABETIQUE);
//            Repas r6 = EntitiesFactory.fabriquerRepas(LocalDate.now().plusDays(1), TypeRepas.DINER);
//            r6.ajouterRegimeAlimentaire(RegimeAlimentaire.SANS_SEL);
//            r6.ajouterRegimeAlimentaire(RegimeAlimentaire.MIXE);
//            Repas r7 = EntitiesFactory.fabriquerRepas(LocalDate.now().plusDays(3), TypeRepas.PETIT_DEJEUNER);
//            r7.ajouterRegimeAlimentaire(RegimeAlimentaire.MIXE);
//            r7.ajouterRegimeAlimentaire(RegimeAlimentaire.SANS_SEL);
//            r7.ajouterRegimeAlimentaire(RegimeAlimentaire.VEGAN);
//            Repas r8 = EntitiesFactory.fabriquerRepas(LocalDate.now().plusDays(3), TypeRepas.DEJEUNER);
//            r8.ajouterRegimeAlimentaire(RegimeAlimentaire.MIXE);
//            r8.ajouterRegimeAlimentaire(RegimeAlimentaire.DIABETIQUE);
//            r8.ajouterRegimeAlimentaire(RegimeAlimentaire.VEGETARIEN);
//
//            repasDao.create(r1);
//            repasDao.create(r2);
//            repasDao.create(r3);
//            repasDao.create(r4);
//            repasDao.create(r5);
//            repasDao.create(r6);
//            repasDao.create(r7);
//            repasDao.create(r8);
//
//            miney.ajouterRegimeAlimentaire(RegimeAlimentaire.MIXE);
////            miney.ajouterRegimeAlimentaire(RegimeAlimentaire.SANS_SEL);
////            miney.ajouterRegimeAlimentaire(RegimeAlimentaire.DIABETIQUE);
////            miney.ajouterRegimeAlimentaire(RegimeAlimentaire.VEGETARIEN);
////            miney.ajouterRegimeAlimentaire(RegimeAlimentaire.VEGAN);
//
//            miney.ajouterRepas(r1);
//            miney.ajouterRepas(r2);
//            miney.ajouterRepas(r3);
//            miney.ajouterRepas(r4);
//            miney.ajouterRepas(r5);
//            miney.ajouterRepas(r6);
//            miney.ajouterRepas(r7);
//            miney.ajouterRepas(r8);
//
//
//            Repas c3 = EntitiesFactory.fabriquerRepas(LocalDate.now().plusDays(7), TypeRepas.DEJEUNER);
//            c3.ajouterRegimeAlimentaire(RegimeAlimentaire.MIXE);
//            c3.ajouterRegimeAlimentaire(RegimeAlimentaire.SANS_SEL);
//            Repas c4 = EntitiesFactory.fabriquerRepas(LocalDate.now().plusDays(7), TypeRepas.DINER);
//            c4.ajouterRegimeAlimentaire(RegimeAlimentaire.SANS_SEL);
//            c4.ajouterRegimeAlimentaire(RegimeAlimentaire.MIXE);
//            c4.ajouterRegimeAlimentaire(RegimeAlimentaire.VEGETARIEN);
//            Repas c5 = EntitiesFactory.fabriquerRepas(LocalDate.now().plusDays(8), TypeRepas.PETIT_DEJEUNER);
//            c5.ajouterRegimeAlimentaire(RegimeAlimentaire.VEGETARIEN);
//            Repas c6 = EntitiesFactory.fabriquerRepas(LocalDate.now().plusDays(8), TypeRepas.DINER);
//            c6.ajouterRegimeAlimentaire(RegimeAlimentaire.DIABETIQUE);
//
//            repasDao.create(c3);
//            repasDao.create(c4);
//            repasDao.create(c5);
//            repasDao.create(c6);
//
//            haddock.ajouterRegimeAlimentaire(RegimeAlimentaire.MIXE);
//            haddock.ajouterRegimeAlimentaire(RegimeAlimentaire.SANS_SEL);
//
//            haddock.ajouterRepas(c3);
//            haddock.ajouterRepas(c4);
//
//            tintin.ajouterRegimeAlimentaire(RegimeAlimentaire.VEGETARIEN);
//            tintin.ajouterRepas(c5);
//            tournesol.ajouterRegimeAlimentaire(RegimeAlimentaire.DIABETIQUE);
//            tournesol.ajouterRepas(c6);
//
//            tombR.ajouterRegimeAlimentaire(RegimeAlimentaire.SANS_SEL);
//            tombR.ajouterRepas(c4);
//            tombP.ajouterRegimeAlimentaire(RegimeAlimentaire.MIXE);
//            tombP.ajouterRepas(c3);
//
//            this.patientDao.create(miney);
//            this.patientDao.create(haddock);
//            this.patientDao.create(tintin);
//            this.patientDao.create(tournesol);
//            this.patientDao.create(tombR);
//            this.patientDao.create(tombP);
//        } catch (PatientConstructionException | PatientException | RepasConstructionException | DaoException e) {
//            throw new BusinessException(e.getMessage(), e);
//        }
//
//
//    }
}
