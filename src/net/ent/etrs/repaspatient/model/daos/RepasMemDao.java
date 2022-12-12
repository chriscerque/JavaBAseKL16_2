package net.ent.etrs.repaspatient.model.daos;


//@Log4j2(topic = "LoggerDAO")

import net.ent.etrs.repaspatient.model.daos.exceptions.DaoException;
import net.ent.etrs.repaspatient.model.entities.Repas;
import net.ent.etrs.repaspatient.model.entities.references.ConstantesMetier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Implementation memoire de la dao.
 *
 * @author christophe.cerqueira
 */
public class RepasMemDao implements IRepasMemDao {
    //    private static RepasMemDao INSTANCE;
    private List<Repas> persistence = new ArrayList<>();

    protected RepasMemDao() {
    }

    //    public static RepasMemDao getInstance() {
//        if (INSTANCE == null) {
//            INSTANCE = new RepasMemDao();
//        }
//        return INSTANCE;
//    }

    @Override
    public Repas create(final Repas repas) throws DaoException {
        if (this.exist(repas)) {
//			log.warn(C.MSG_DAO_PERSITANCE_REPAS_EXISTANT);
            throw new DaoException(ConstantesMetier.MSG_DAO_PERSITANCE_REPAS_EXISTANT);
        }
        this.persistence.add(repas);
        return this.read(repas.getId());
//		log.info(C.MSG_DAO_PERSITANCE_REPAS);

    }

    @Override
    public void delete(final Repas repas) throws DaoException {
        if (!this.exist(repas)) {
//			log.warn(C.MSG_DAO_SUPPRESSION_REPAS_INEXISTANT);
            throw new DaoException(ConstantesMetier.MSG_DAO_SUPPRESSION_REPAS_INEXISTANT);
        }
        this.persistence.remove(repas);
//		log.info(C.MSG_DAO_SUPPRESSION_REPAS);

    }

    @Override
    public void deleteByKey(final String id) throws DaoException {
        this.delete(this.read(id));
    }

    @Override
    public boolean exist(final Repas repas) throws DaoException {
        if (Objects.isNull(repas)) {
//			log.warn(C.MSG_DAO_PERSITANCE_REPAS_NULL);
            throw new DaoException(ConstantesMetier.MSG_DAO_PERSITANCE_REPAS_NULL);
        }
        return this.persistence.contains(repas);
    }

    @Override
    public Repas read(final String id) throws DaoException {
        for (Repas repas : persistence) {
            if (repas.getId().equals(id)) {
                return repas;
            }
        }
        throw new DaoException("Contact inexistant");
    }

    @Override
    public List<Repas> readAll() {
        return Collections.unmodifiableList(this.persistence);
    }

    @Override
    public Repas update(final Repas repas) throws DaoException {
        if (!this.exist(repas)) {
//			log.warn(C.MSG_DAO_MISE_A_JOUR_REPAS_INEXISTANT);
        }

//		log.info(C.MSG_DAO_MISE_A_JOUR_REPAS);
        this.persistence.set(this.persistence.indexOf(repas), repas);
        return this.read(repas.getId());
    }

}
