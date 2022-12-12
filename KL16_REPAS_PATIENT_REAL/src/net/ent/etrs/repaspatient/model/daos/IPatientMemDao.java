package net.ent.etrs.repaspatient.model.daos;

import net.ent.etrs.repaspatient.model.daos.exceptions.DaoException;
import net.ent.etrs.repaspatient.model.entities.Patient;

import java.util.List;
import java.util.UUID;

public interface IPatientMemDao {

    public abstract List<Patient> readAll();

    public abstract Patient create(final Patient p) throws DaoException;

    public abstract void delete(final String numSecu);

    public abstract void deleteByKey(final UUID id);

    public abstract boolean exists(final Patient p);

    public abstract Patient read(final String numSecu) throws DaoException;

    public abstract void init();

    public abstract Patient update(final Patient p) throws DaoException;




}
