package net.ent.etrs.kl16repaspatientgouin.model.daos;

import net.ent.etrs.kl16repaspatientgouin.model.daos.exceptions.DaoException;
import net.ent.etrs.kl16repaspatientgouin.model.entities.EntitiesFactory;
import net.ent.etrs.kl16repaspatientgouin.model.entities.Patient;
import net.ent.etrs.kl16repaspatientgouin.model.entities.PatientNomPrenomComparator;
import net.ent.etrs.kl16repaspatientgouin.model.entities.Repas;
import net.ent.etrs.kl16repaspatientgouin.model.entities.exceptions.*;
import net.ent.etrs.kl16repaspatientgouin.model.entities.references.ConstantesMetier;
import net.ent.etrs.kl16repaspatientgouin.model.entities.references.RegimeAlimentaire;
import net.ent.etrs.kl16repaspatientgouin.model.entities.references.TypeRepas;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class PatientMemDao implements IPatientMemDao{

    private List<Patient> hopital;

    private PatientMemDao patientDao;

    private RepasMemDao repasDao;

    protected PatientMemDao() throws DaoException {
        this.hopital = new ArrayList<>();
        this.patientDao = DaoFactory.fabriquerPatientDao();
        this.repasDao = DaoFactory.fabriquerRepasDao();
        this.init();
    }

    @Override
    public Patient create(Patient patient) throws DaoException {
        if (Objects.isNull(patient)) {
            throw new DaoException(ConstantesMetier.MSG_DAO_PERSITANCE_PATIENT_NULL);
        }
        if (exist(patient)) {
            throw new DaoException(ConstantesMetier.MSG_DAO_PERSITANCE_PATIENT_EXISTANT);
        }
        this.hopital.add(patient);
        return this.read(patient.getId());
    }

    @Override
    public Patient read(String id) throws DaoException {
        if (!Objects.isNull(id)){
            for (Patient patient: this.hopital) {
                if (patient.getId().equals(id)){
                    return patient;
                }
            }
        }
        throw new DaoException(ConstantesMetier.MSG_DAO_PERSITANCE_PATIENT_NULL);
    }

    @Override
    public Patient update(Patient patient) throws DaoException {
        if(!Objects.isNull(patient) || hopital.contains(patient)){
            hopital.remove(patient);
            hopital.add(patient);
            return patient;
        }
        throw new DaoException(ConstantesMetier.MSG_DAO_MISE_A_JOUR_PATIENT_INEXISTANT);
    }

    @Override
    public void delete(Patient patient) throws DaoException {
        if (!Objects.isNull(patient) || hopital.contains(patient)){
            for (Patient p: this.hopital) {
                if (p.equals(patient)){
                    hopital.remove(p);
                }
            }
        }
        throw new DaoException(ConstantesMetier.MSG_DAO_SUPPRESSION_PATIENT_INEXISTANT);
    }

    @Override
    public void deletebyKey(String id) throws DaoException {
        if (!Objects.isNull(id)){
            for (Patient patient: this.hopital) {
                if (patient.equals(this.read(id))){
                    hopital.remove(patient);
                }
            }
        }
    }

    @Override
    public List<Patient> readAll() {
        List<Patient> tmpList = new ArrayList<>(this.hopital);

        Collections.sort(tmpList, new PatientNomPrenomComparator());

        return Collections.unmodifiableList(tmpList);
    }

    @Override
    public void init() throws DaoException {
        try {
            Patient miney = EntitiesFactory.fabriquerPatient("12345", "Miney", "Bernard", LocalDate.of(1970, Month.DECEMBER, 12));
            Patient haddock = EntitiesFactory.fabriquerPatient("12789", "HADDOCK", "Archibald", LocalDate.of(1970, Month.APRIL, 1));
            Patient tintin = EntitiesFactory.fabriquerPatient("12348", "TINTIN", "Mar", LocalDate.of(1970, Month.FEBRUARY, 10));
            Patient tournesol = EntitiesFactory.fabriquerPatient("27789", "TOURNESOL", "Tryphon", LocalDate.of(1970, Month.MAY, 20));
            Patient tombR = EntitiesFactory.fabriquerPatient("11111", "TOMB", "Raider", LocalDate.of(1970, Month.MAY, 20));
            Patient tombP = EntitiesFactory.fabriquerPatient("11112", "TOMB", "Parterre", LocalDate.of(1980, Month.JUNE, 14));

            Repas r1 = EntitiesFactory.fabriquerRepas(LocalDate.now().plusDays(2), TypeRepas.PETIT_DEJEUNER);
            r1.ajouterRegimeAlimentaire(RegimeAlimentaire.MIXE);
            Repas r2 = EntitiesFactory.fabriquerRepas(LocalDate.now().plusDays(2), TypeRepas.DEJEUNER);
            r2.ajouterRegimeAlimentaire(RegimeAlimentaire.MIXE);
            r2.ajouterRegimeAlimentaire(RegimeAlimentaire.SANS_SEL);
            Repas r3 = EntitiesFactory.fabriquerRepas(LocalDate.now().plusDays(2), TypeRepas.DINER);
            r3.ajouterRegimeAlimentaire(RegimeAlimentaire.MIXE);
            r3.ajouterRegimeAlimentaire(RegimeAlimentaire.VEGETARIEN);
            Repas r4 = EntitiesFactory.fabriquerRepas(LocalDate.now().plusDays(1), TypeRepas.PETIT_DEJEUNER);
            r4.ajouterRegimeAlimentaire(RegimeAlimentaire.MIXE);
            r4.ajouterRegimeAlimentaire(RegimeAlimentaire.VEGAN);
            Repas r5 = EntitiesFactory.fabriquerRepas(LocalDate.now().plusDays(1), TypeRepas.DEJEUNER);
            r5.ajouterRegimeAlimentaire(RegimeAlimentaire.MIXE);
            r5.ajouterRegimeAlimentaire(RegimeAlimentaire.DIABETIQUE);
            Repas r6 = EntitiesFactory.fabriquerRepas(LocalDate.now().plusDays(1), TypeRepas.DINER);
            r6.ajouterRegimeAlimentaire(RegimeAlimentaire.SANS_SEL);
            r6.ajouterRegimeAlimentaire(RegimeAlimentaire.MIXE);
            Repas r7 = EntitiesFactory.fabriquerRepas(LocalDate.now().plusDays(3), TypeRepas.PETIT_DEJEUNER);
            r7.ajouterRegimeAlimentaire(RegimeAlimentaire.MIXE);
            r7.ajouterRegimeAlimentaire(RegimeAlimentaire.SANS_SEL);
            r7.ajouterRegimeAlimentaire(RegimeAlimentaire.VEGAN);
            Repas r8 = EntitiesFactory.fabriquerRepas(LocalDate.now().plusDays(3), TypeRepas.DEJEUNER);
            r8.ajouterRegimeAlimentaire(RegimeAlimentaire.MIXE);
            r8.ajouterRegimeAlimentaire(RegimeAlimentaire.DIABETIQUE);
            r8.ajouterRegimeAlimentaire(RegimeAlimentaire.VEGETARIEN);

            repasDao.create(r1);
            repasDao.create(r2);
            repasDao.create(r3);
            repasDao.create(r4);
            repasDao.create(r5);
            repasDao.create(r6);
            repasDao.create(r7);
            repasDao.create(r8);

            miney.ajouterRegimeAlimentaire(RegimeAlimentaire.MIXE);
//            miney.ajouterRegimeAlimentaire(RegimeAlimentaire.SANS_SEL);
//            miney.ajouterRegimeAlimentaire(RegimeAlimentaire.DIABETIQUE);
//            miney.ajouterRegimeAlimentaire(RegimeAlimentaire.VEGETARIEN);
//            miney.ajouterRegimeAlimentaire(RegimeAlimentaire.VEGAN);

            miney.ajouterRepas(r1);
            miney.ajouterRepas(r2);
            miney.ajouterRepas(r3);
            miney.ajouterRepas(r4);
            miney.ajouterRepas(r5);
            miney.ajouterRepas(r6);
            miney.ajouterRepas(r7);
            miney.ajouterRepas(r8);


            Repas c3 = EntitiesFactory.fabriquerRepas(LocalDate.now().plusDays(7), TypeRepas.DEJEUNER);
            c3.ajouterRegimeAlimentaire(RegimeAlimentaire.MIXE);
            c3.ajouterRegimeAlimentaire(RegimeAlimentaire.SANS_SEL);
            Repas c4 = EntitiesFactory.fabriquerRepas(LocalDate.now().plusDays(7), TypeRepas.DINER);
            c4.ajouterRegimeAlimentaire(RegimeAlimentaire.SANS_SEL);
            c4.ajouterRegimeAlimentaire(RegimeAlimentaire.MIXE);
            c4.ajouterRegimeAlimentaire(RegimeAlimentaire.VEGETARIEN);
            Repas c5 = EntitiesFactory.fabriquerRepas(LocalDate.now().plusDays(8), TypeRepas.PETIT_DEJEUNER);
            c5.ajouterRegimeAlimentaire(RegimeAlimentaire.VEGETARIEN);
            Repas c6 = EntitiesFactory.fabriquerRepas(LocalDate.now().plusDays(8), TypeRepas.DINER);
            c6.ajouterRegimeAlimentaire(RegimeAlimentaire.DIABETIQUE);

            repasDao.create(c3);
            repasDao.create(c4);
            repasDao.create(c5);
            repasDao.create(c6);

            haddock.ajouterRegimeAlimentaire(RegimeAlimentaire.MIXE);
            haddock.ajouterRegimeAlimentaire(RegimeAlimentaire.SANS_SEL);

            haddock.ajouterRepas(c3);
            haddock.ajouterRepas(c4);

            tintin.ajouterRegimeAlimentaire(RegimeAlimentaire.VEGETARIEN);
            tintin.ajouterRepas(c5);
            tournesol.ajouterRegimeAlimentaire(RegimeAlimentaire.DIABETIQUE);
            tournesol.ajouterRepas(c6);

            tombR.ajouterRegimeAlimentaire(RegimeAlimentaire.SANS_SEL);
            tombR.ajouterRepas(c4);
            tombP.ajouterRegimeAlimentaire(RegimeAlimentaire.MIXE);
            tombP.ajouterRepas(c3);

            this.patientDao.create(miney);
            this.patientDao.create(haddock);
            this.patientDao.create(tintin);
            this.patientDao.create(tournesol);
            this.patientDao.create(tombR);
            this.patientDao.create(tombP);
        } catch ( PatientException | EntitiesFactoryException | RepasException e) {
            throw new  DaoException(e.getMessage(), e);
        }

    }

    @Override
    public boolean exist(Patient patient) {
        return this.hopital.contains(patient);
    }
}
