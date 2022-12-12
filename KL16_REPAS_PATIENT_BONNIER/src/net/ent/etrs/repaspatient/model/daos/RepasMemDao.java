package net.ent.etrs.repaspatient.model.daos;

import net.ent.etrs.repaspatient.model.daos.exceptions.DaoException;
import net.ent.etrs.repaspatient.model.entities.Repas;
import net.ent.etrs.repaspatient.model.entities.references.ConstantesMetier;
import net.ent.etrs.repaspatient.model.entities.references.TypeRepas;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class RepasMemDao implements IRepasMemDao {

    private final List<Repas> persistance = new ArrayList<>();


    @Override
    public Repas create(Repas repas) throws DaoException {
        if (!Objects.isNull(repas) || persistance.add(repas)) {
            return repas;
        } else {
            throw new DaoException(ConstantesMetier.MSG_DAO_PERSITANCE_REPAS_EXISTANT);
        }
    }

    @Override
    public List<Repas> readAll() {
        return Collections.unmodifiableList(this.persistance);
    }

    @Override
    public Repas update(Repas repas) throws DaoException {
        if (!Objects.isNull(repas) || persistance.contains(repas)) {
            persistance.remove(repas);
            persistance.add(repas);
            return repas;
        }
        throw new DaoException(ConstantesMetier.MSG_DAO_SUPPRESSION_REPAS_INEXISTANT);
    }

    @Override
    public void delete(String id) throws DaoException {
        List<Repas> repasLstTemporaire = new ArrayList<>(persistance);
        if (!Objects.isNull(id)) {
            for (Repas r : repasLstTemporaire) {
                if (r.getId().equals(id)) {
                    persistance.remove(r);
                }
            }
        }
        throw new DaoException(ConstantesMetier.MSG_DAO_SUPPRESSION_REPAS_INEXISTANT);
    }

    @Override
    public void delete(LocalDate dateRepas, TypeRepas typeRepas) throws DaoException {
        List<Repas> repasLstTemporaire = new ArrayList<>(persistance);
        if (!Objects.isNull(dateRepas) || !Objects.isNull(typeRepas)) {
            for (Repas r : repasLstTemporaire) {
                if (r.getDateRepas().equals(dateRepas) && r.getTypeRepas().equals(typeRepas)) {
                    persistance.remove(r);
                }
            }
        }
        throw new DaoException(ConstantesMetier.MSG_DAO_SUPPRESSION_REPAS_INEXISTANT);
    }


    @Override
    public Repas read(LocalDate dateRepas, TypeRepas typeRepas) {
        Repas p = null;
        for (Repas repas : this.persistance) {
            if (repas.getDateRepas().equals(dateRepas) && repas.getTypeRepas().equals(typeRepas)) {
                p = repas;
            }
        }
        return p;

    }
}

