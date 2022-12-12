package net.ent.etrs.repaspatient.model.entities.references;

public enum TypeRepas {
    PETIT_DEJEUNER,
    DEJEUNER,
    DINER;

    TypeRepas() {
    }

    @Override
    public String toString() {
        return "TypeRepas{} " + super.toString();
    }


}
