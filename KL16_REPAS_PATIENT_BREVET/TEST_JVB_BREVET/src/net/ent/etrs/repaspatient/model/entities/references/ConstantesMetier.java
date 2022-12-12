/**
 *
 */
package net.ent.etrs.repaspatient.model.entities.references;

/**
 * @author christophe.cerqueira
 */
public final class ConstantesMetier {


    //ENUM
    public static final String REGIME_ALIMENTAIRE_GET_BY_LIBELLE_EXCEPTION = "Le régime alimentaire n'a pas été trouvé.";

    /////////////////////////////////////////////////

    //PATIENT
    public static final String MSG_PATIENT_NUM_SECU_EXCEPTION = "Le numéro de sécurité doit être renseigné.";
    public static final int PATIENT_NUM_SECU_TAILLE = 5;
    public static final String MSG_PATIENT_NUM_SECU_LENGTH_EXCEPTION = String.format("Le numéro de sécurité doit contenir %d caractères.", ConstantesMetier.PATIENT_NUM_SECU_TAILLE);
    public static final int PATIENT_NOM_TAILLE_MIN = 3;
    public static final int PATIENT_NOM_TAILLE_MAX = 50;
    public static final int PATIENT_PRENOM_TAILLE_MIN = 3;
    public static final int PATIENT_PRENOM_TAILLE_MAX = 50;
    public static final String MSG_PATIENT_NOM_LENGTH_EXCEPTION = String.format("Le nom doit contenir entre %d et %d caractères.", ConstantesMetier.PATIENT_NOM_TAILLE_MIN, ConstantesMetier.PATIENT_NOM_TAILLE_MAX);
    public static final String MSG_PATIENT_PRENOM_LENGTH_EXCEPTION = String.format("Le nom doit contenir entre %d et %d caractères.", ConstantesMetier.PATIENT_NOM_TAILLE_MIN, ConstantesMetier.PATIENT_NOM_TAILLE_MAX);
    public static final String PATIENT_REPAS_REGIME_ALIMENTAIRE_EXCEPTION = "Le repas du patient est invalide.";
    public static final String MSG_PATIENT_REGIME_ALIMENTAIRE_EXCEPTION = "Le régime alimentaire est invalide.";
    public static final String MSG_PATIENT_REGIME_ALIMENTAIRE_EXIST_EXCEPTION = "Le régime alimentaire existe déjà.";
    public static final String PATIENT_REPAS_REGIME_ALIMENTAIRE_NON_COMPATIBLE_EXCEPTION = "Le repas ne correspond pas au régime alimentaire du patient.";
    public static final String MSG_PATIENT_NOM_EXCEPTION = "Le nom est non renseigné.";
    public static final String MSG_PATIENT_PRENOM_EXCEPTION = "Le prenom est non renseigné.";
    public static final String MSG_PATIENT_DATE_ENTREE_EXCEPTION = "La date est non renseignée.";
    public static final String MSG_PATIENT_DATE_ENTREE_INVALIDE_EXCEPTION = "La date de naissance ne peut être postérieure à la date actuelle.";

    /////////////////////////////////////////////////
    //REPAS
    public static final String REPAS_DATE_EXCEPTION = "La date du repas est non valide.";
    public static final String REPAS_TYPE_REPAS_EXCEPTION = "Le type du repas est non valide.";

    /////////////////////////////////////////////////
    //FACADE METIER
    public static final String MSG_PATIENT_CREATION_EXCEPTION = "La sauvegarde du patient %s %s n'a pas pu être effectuée.";
    public static final String MSG_PATIENT_SUPPRESSION_EXCEPTION = "La suppression du patient %s %s n'a pas pu être effectuée.";
    public static final String MSG_PATIENT_MISE_A_JOUR_EXCEPTION = "La mise à jour du patient %s %s n'a pas pu être effectuée.";
    public static final String MSG_PATIENT_RECHERCHE_EXCEPTION = "Le patient n'a pas été trouvé.";


    /////////////////////////////////////////////////
    //DAO PATIENT
    public static final String MSG_DAO_PERSITANCE_PATIENT_NULL = "Dao : tentative de persistance d'un patient à null";
    public static final String MSG_DAO_PERSITANCE_PATIENT_EXISTANT = "Dao : tentative de création d'un patient déjà existant";
    public static final String MSG_DAO_SUPPRESSION_PATIENT_NULL = "Dao : tentative de suppression d'un patient à null";
    public static final String MSG_DAO_SUPPRESSION_PATIENT_INEXISTANT = "Dao : tentative de suppression d'un patient inexistant";
    public static final String MSG_DAO_MISE_A_JOUR_PATIENT_NULL = "Dao : tentative de modification d'un patient à null";
    public static final String MSG_DAO_MISE_A_JOUR_PATIENT_INEXISTANT = "Dao : tentative de modification d'un patient inexistant";
    public static final String MSG_DAO_PERSITANCE_PATIENT = "Dao : persistance d'un patient";
    public static final String MSG_DAO_SUPPRESSION_PATIENT = "Dao : suppression d'un patient";
    public static final String MSG_DAO_MISE_A_JOUR_PATIENT = "Dao : mise à jour d'un patient";
    public static final String MSG_PATIENT_INTROUVABLE = "Le patient est introuvable";
    //DAO REPAS
    public static final String MSG_DAO_PERSITANCE_REPAS_EXISTANT = "Dao : tentative de création d'un repas déjà existant";
    public static final String MSG_DAO_SUPPRESSION_REPAS_INEXISTANT = "Dao : tentative de suppression d'un repas inexistant";
    public static final String MSG_DAO_PERSITANCE_REPAS_NULL = "Dao : tentative de persistance d'un repas à null";
    public static final String DAO_ID_NULL = "L'identifiant doit être renseigné";
    public static final String DAO_REPAS_INTROUVABLE = "Le repas est introuvable";
    public static final String MSG_DAO_PERSITANCE_REPAS_INEXISTANT = "Dao : tentative de modification d'un repas inexistant";


    /**
     *
     */
    private ConstantesMetier() {
    }

}
