package net.ent.etrs.repaspatient.model.facades;

import net.ent.etrs.repaspatient.model.daos.DaoFactory;
import net.ent.etrs.repaspatient.model.daos.DaoInter;
import net.ent.etrs.repaspatient.model.daos.exceptions.DaoException;
import net.ent.etrs.repaspatient.model.entities.EntitiesFactory;
import net.ent.etrs.repaspatient.model.entities.Patient;
import net.ent.etrs.repaspatient.model.entities.Repas;
import net.ent.etrs.repaspatient.model.entities.exceptions.PatientException;
import net.ent.etrs.repaspatient.model.entities.exceptions.RepasException;
import net.ent.etrs.repaspatient.model.entities.references.ConstantesMetier;
import net.ent.etrs.repaspatient.model.entities.references.RegimeAlimentaire;
import net.ent.etrs.repaspatient.model.entities.references.TypeRepas;
import net.ent.etrs.repaspatient.model.facades.exceptions.BusinessException;

import java.time.LocalDate;
import java.time.Month;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class FacadeMetierImpl implements FacadeMetier {
    DaoInter<Patient, String> patientDao;
    DaoInter<Repas, String> repasDao;

    protected FacadeMetierImpl() {
        patientDao = DaoFactory.fabriquerPatientDao();
        repasDao = DaoFactory.fabriquerRepasDao();
    }

    /**
     * Renvoi la liste de tous les patients.
     *
     * @return la liste de patients.
     */
    @Override
    public List<Patient> listerPatients() {
        return Collections.unmodifiableList(patientDao.readAll());
    }

    /**
     * Renvoi la liste de tous les repas.
     *
     * @return la liste des repas.
     */
    @Override
    public List<Repas> listerRepas() {
        return Collections.unmodifiableList(repasDao.readAll());
    }

    /**
     * Sauvegarge un patient dans l'application.
     *
     * @param patient le patient à sauvegarder.
     * @throws BusinessException si le patient existe déjà ou une erreur est levée durant la sauvegarde.
     */
    @Override
    public void sauvegarderPatient(final Patient patient) throws BusinessException {
        this.paramNullChecker(patient);
        try {
            patientDao.save(patient);
        } catch (DaoException e) {
            throw new BusinessException(ConstantesMetier.MSG_PATIENT_MISE_A_JOUR_EXCEPTION);
        }
    }

    /**
     * Supprime un patient dans l'application;
     *
     * @param patient le patient à supprimer
     * @throws BusinessException si le patient n'existe pas ou une erreur est levée durant la suppression.
     */
    @Override
    public void supprimerPatient(final Patient patient) throws BusinessException {
        this.paramNullChecker(patient);
        try {
            patientDao.delete(patient);
        } catch (DaoException e) {
            throw new BusinessException(ConstantesMetier.MSG_PATIENT_SUPPRESSION_EXCEPTION);
        }
    }

    /**
     * Met à jour un patient dans l'application.
     *
     * @param patient le patient à mettre à jour.
     * @throws BusinessException si le patient n'existe pas ou une erreur est levée durant la mise à jour.
     */
    @Override
    public void mettreAJourPatient(final Patient patient) throws BusinessException {
        this.sauvegarderPatient(patient);
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

            this.repasDao.save(r1);
            this.repasDao.save(r2);
            this.repasDao.save(r3);
            this.repasDao.save(r4);
            this.repasDao.save(r5);
            this.repasDao.save(r6);
            this.repasDao.save(r7);
            this.repasDao.save(r8);

        } catch (DaoException |
                 RepasException e) {
            throw new BusinessException(e.getMessage(), e);
        }

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

            repasDao.save(r1);
            repasDao.save(r2);
            repasDao.save(r3);
            repasDao.save(r4);
            repasDao.save(r5);
            repasDao.save(r6);
            repasDao.save(r7);
            repasDao.save(r8);

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

            repasDao.save(c3);
            repasDao.save(c4);
            repasDao.save(c5);
            repasDao.save(c6);

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

            this.patientDao.save(miney);
            this.patientDao.save(haddock);
            this.patientDao.save(tintin);
            this.patientDao.save(tournesol);
            this.patientDao.save(tombR);
            this.patientDao.save(tombP);
        } catch (
                PatientException |
                DaoException |
                RepasException e) {
            throw new BusinessException(e.getMessage(), e);
        }

    }

    @Override
    public Patient recupererPatientById(final String patientId) throws BusinessException {
        this.paramNullChecker(patientId);
        try {
            return patientDao.read(patientId);
        } catch (DaoException e) {
            throw new BusinessException(ConstantesMetier.MSG_PATIENT_RECHERCHE_EXCEPTION);
        }
    }

    private void paramNullChecker(Object o) throws BusinessException {
        if (Objects.isNull(o)) {
            throw new BusinessException(ConstantesMetier.MSG_PATIENT_VIDE_EXCEPTION);
        }
    }
}
