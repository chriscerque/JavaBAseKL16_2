package net.ent.etrs.repaspatient.model.facades;

import net.ent.etrs.repaspatient.model.daos.DaoFactory;
import net.ent.etrs.repaspatient.model.daos.IPatientMemDao;
import net.ent.etrs.repaspatient.model.daos.IRepasMemDao;
import net.ent.etrs.repaspatient.model.daos.exceptions.DaoException;
import net.ent.etrs.repaspatient.model.entities.EntitiesFactory;
import net.ent.etrs.repaspatient.model.entities.Patient;
import net.ent.etrs.repaspatient.model.entities.Repas;
import net.ent.etrs.repaspatient.model.entities.exceptions.PatientConstructionException;
import net.ent.etrs.repaspatient.model.entities.exceptions.PatientException;
import net.ent.etrs.repaspatient.model.entities.exceptions.RepasConstructionException;
import net.ent.etrs.repaspatient.model.entities.exceptions.RepasException;
import net.ent.etrs.repaspatient.model.entities.references.ConstantesMetier;
import net.ent.etrs.repaspatient.model.entities.references.RegimeAlimentaire;
import net.ent.etrs.repaspatient.model.entities.references.TypeRepas;
import net.ent.etrs.repaspatient.model.facades.exceptions.BusinessException;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;

public class FacadeMetierImpl implements FacadeMetier{

    private IRepasMemDao repasDao = DaoFactory.fabriquerRepasDao();
    private IPatientMemDao patientDao = DaoFactory.fabriquerPatientDao();

    @Override
    public List<Patient> listerPatients() {
        return this.patientDao.readAll();
    }

    @Override
    public List<Repas> listerRepas() {
        return this.repasDao.readAll();
    }

    @Override
    public void sauvegarderPatient(Patient patient) throws BusinessException, PatientException {
        if(Objects.isNull(patient)){
            throw new BusinessException(ConstantesMetier.MSG_PATIENT_CREATION_EXCEPTION);
        }
        Patient p = EntitiesFactory.fabriquerPatient(patient.getDateEntree(), patient.getNumSecu(), patient.getNom(), patient.getPrenom());
    }

    @Override
    public void supprimerPatient(Patient patient) throws BusinessException {
        if(Objects.isNull(patient)){
            throw new BusinessException(ConstantesMetier.MSG_PATIENT_RECHERCHE_EXCEPTION);
        }
        try {
            this.patientDao.delete(patient);
        } catch (DaoException e) {
            throw new BusinessException(ConstantesMetier.MSG_PATIENT_SUPPRESSION_EXCEPTION);
        }
    }

    @Override
    public void mettreAJourPatient(Patient patient) throws BusinessException, PatientException {
        if(Objects.isNull(patient)) {
            throw new BusinessException(ConstantesMetier.MSG_PATIENT_MISE_A_JOUR_EXCEPTION);
        }
        supprimerPatient(patient);
        sauvegarderPatient(patient);
    }

    @Override
    public void init() throws BusinessException {
        try {
            Repas r1 = EntitiesFactory.fabriquerRepas(LocalDate.now().plusDays(4), TypeRepas.PETIT_DEJEUNER);
            r1.ajouterRegimeAlimentaire(RegimeAlimentaire.MIXE);
            r1.ajouterRegimeAlimentaire(RegimeAlimentaire.SANS_SEL);
            Repas r2 = EntitiesFactory.fabriquerRepas(LocalDate.now().plusDays(4), TypeRepas.DEJEUNER);
            r2.ajouterRegimeAlimentaire(RegimeAlimentaire.VEGAN);
            r2.ajouterRegimeAlimentaire(RegimeAlimentaire.SANS_SEL);
            Repas r3 = EntitiesFactory.fabriquerRepas(LocalDate.now().plusDays(4), TypeRepas.DINER);
            r3.ajouterRegimeAlimentaire(RegimeAlimentaire.VEGETARIEN);
            Repas r4 = EntitiesFactory.fabriquerRepas(LocalDate.now().plusDays(5), TypeRepas.PETIT_DEJEUNER);
            r4.ajouterRegimeAlimentaire(RegimeAlimentaire.SANS_SEL);
            Repas r5 = EntitiesFactory.fabriquerRepas(LocalDate.now().plusDays(5), TypeRepas.DEJEUNER);
            r5.ajouterRegimeAlimentaire(RegimeAlimentaire.MIXE);
            r5.ajouterRegimeAlimentaire(RegimeAlimentaire.VEGETARIEN);
            Repas r6 = EntitiesFactory.fabriquerRepas(LocalDate.now().plusDays(5), TypeRepas.DINER);
            r6.ajouterRegimeAlimentaire(RegimeAlimentaire.DIABETIQUE);
            r6.ajouterRegimeAlimentaire(RegimeAlimentaire.SANS_SEL);
            Repas r7 = EntitiesFactory.fabriquerRepas(LocalDate.now().plusDays(6), TypeRepas.PETIT_DEJEUNER);
            r7.ajouterRegimeAlimentaire(RegimeAlimentaire.DIABETIQUE);
            Repas r8 = EntitiesFactory.fabriquerRepas(LocalDate.now().plusDays(6), TypeRepas.DEJEUNER);
            r8.ajouterRegimeAlimentaire(RegimeAlimentaire.SANS_SEL);

            this.repasDao.create(r1);
            this.repasDao.create(r2);
            this.repasDao.create(r3);
            this.repasDao.create(r4);
            this.repasDao.create(r5);
            this.repasDao.create(r6);
            this.repasDao.create(r7);
            this.repasDao.create(r8);

        } catch (DaoException | RepasException e) {
            throw new BusinessException(e.getMessage(), e);
        }

        try {
            Patient miney = EntitiesFactory.fabriquerPatient(LocalDate.of(1970, Month.DECEMBER, 12),"12345", "Miney", "Bernard" );
            Patient haddock = EntitiesFactory.fabriquerPatient(LocalDate.of(1970, Month.APRIL, 1),"12789", "HADDOCK", "Archibald" );
            Patient tintin = EntitiesFactory.fabriquerPatient(LocalDate.of(1970, Month.FEBRUARY, 10),"12348", "TINTIN", "Mar");
            Patient tournesol = EntitiesFactory.fabriquerPatient(LocalDate.of(1970, Month.MAY, 20),"27789", "TOURNESOL", "Tryphon" );
            Patient tombR = EntitiesFactory.fabriquerPatient(LocalDate.of(1970, Month.MAY, 20),"11111", "TOMB", "Raider" );
            Patient tombP = EntitiesFactory.fabriquerPatient(LocalDate.of(1980, Month.JUNE, 14),"11112", "TOMB", "Parterre");

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
        } catch (PatientConstructionException | PatientException | DaoException |
                 RepasException e) {
            throw new BusinessException(e.getMessage(), e);
        }



    }

    @Override
    public Patient recupererPatientById(String patientId) throws BusinessException {
        try {
            return patientDao.read(patientId);
        } catch (DaoException e) {
            throw new BusinessException(ConstantesMetier.MSG_PATIENT_RECHERCHE_EXCEPTION);
        }
    }
}
