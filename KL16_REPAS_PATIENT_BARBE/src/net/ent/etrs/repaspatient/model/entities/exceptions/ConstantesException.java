package net.ent.etrs.repaspatient.model.entities.exceptions;

import net.ent.etrs.repaspatient.model.entities.references.ConstantesMetier;

public final class ConstantesException {

    // ATTRIBUTES
    public static final String REPAS_DATEREPAS_NULL_EXCEPTION = "La date repas ne peut etre non renseigné";
    public static final String REPAS_TYPEREPAS_NULL_EXCEPTION = "Le type de repas ne peut etre non renseigné";
    public static final String REPAS_LSTREGIMEALIMENTAIRE_NULL_EXCEPTION = "La liste regime alimentaire ne peut etre non renseigné";
    public static final String PATIENT_DATEENTREE_NULL_EXCEPTION = "La date d'entree du patient ne peut etre non renseigné";
    public static final String PATIENT_LSTREPAS_NULL_EXCEPTION = "La liste de repas du patient ne peut etre non renseigné";
    public static final String PATIENT_LSTREGIMEALIMENTAIRE_NULL_EXCEPTION = "La liste de regime alimentaire du patient ne peut etre non renseigné";
    public static final String PATIENT_NUMSECU_NULL_EXCEPTION = "Le num de secu du patient ne peut etre non renseigné";
    public static final String PATIENT_NOM_NULL_EXCEPTION = "Le nom du patient ne peut etre non renseigné";
    public static final String PATIENT_PRENOM_NULL_EXCEPTION = "Le prenom du patient ne peut etre non renseigné";
    public static final String MSG_REPAS_REGIME_ALIMENTAIRE_NON_RENSEIGNE_EXCEPTION = "Le regime alim doit etre renseigné";
    public static final String MSG_REPAS_REGIME_ALIMENTAIRE_EXIST_EXCEPTION = "Le regime alim existe deja";
    public static final String MSG_PATIENT_REPAS_NON_RENSEIGNE_EXCEPTION = "Le repas du patient doit etre renseigné";
    public static final String MSG_PATIENT_REPAS_EXIST_EXCEPTION = "Le repas existe deja";
    public static final String PATIENT_NUMSECU_TOO_SHORT_EXCEPTION = "Le numero de sécu doit faire plus de ";
    public static final String REGIME_ALIMENTAIRE_EXCEPTION = "Le régime alimentaire n'a pas été trouvé.";
    public static final String MSG_PATIENT_NOM_LENGTH_EXCEPTION = String.format("Le nom doit contenir entre %d et %d caractères.", ConstantesMetier.PATIENT_NOM_TAILLE_MIN, ConstantesMetier.PATIENT_NOM_TAILLE_MAX);
    public static final String MSG_PATIENT_PRENOM_LENGTH_EXCEPTION = String.format("Le nom doit contenir entre %d et %d caractères.", ConstantesMetier.PATIENT_NOM_TAILLE_MIN, ConstantesMetier.PATIENT_NOM_TAILLE_MAX);
    public static final String PATIENT_DATEENTREE_FUTURE = "La date d'entee ne peut etre dans le future";

    /////////////////////////////////////////////////
    //FACADE METIER
    public static final String MSG_PATIENT_CREATION_EXCEPTION = "La sauvegarde du patient %s %s n'a pas pu être effectuée.";
    public static final String MSG_PATIENT_SUPPRESSION_EXCEPTION = "La suppression du patient %s %s n'a pas pu être effectuée.";
    public static final String MSG_PATIENT_MISE_A_JOUR_EXCEPTION = "La mise à jour du patient %s %s n'a pas pu être effectuée.";
    public static final String MSG_PATIENT_RECHERCHE_EXCEPTION = "Le patient n'a pas été trouvé.";
    public static final String DAO_REPAS_NULL = "Le repas n'existe pas";
    public static final String DAO_REPAS_IMPOSSIBLE_UPDATE = "le repas ne peux pas être mis a jour";
    public static final String DAO_REPAS_IMPOSSIBLE_CREATE = "Le repas ne peut pas être créé";
    public static final String DAO_PATIENT_IMPOSSIBLE_CREATE = "Le patient ne peut être créé";
    public static final String DAO_PATIENT_IMPOSSIBLE_UPDATE = "Le patient ne peux être mis a jour";
    public static final String DAO_PATIENT_NULL = "le patient n'existe pas";
    public static final String FACADE_METIER_PATIENT_NULL = "Le patient doit exister";


    // CONSTRUCTOR


    public ConstantesException() {
    }
}
