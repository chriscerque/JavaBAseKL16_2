package net.ent.etrs.repaspatient.view.references;

//@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ConstantesVue {

    /**
     * Options du menu de l'application.
     */
    public static final String[] MENU = {
            "Lister les patients",
            "Créer un nouveau patient",
            "Modifier un patient",
            "Supprimer un patient",
            "Ajouter un repas à un patient"
    };
    public static final String MENU_NOM = "Repas Patient";
    public static final String CONTINUER_SELECTION_REPAS = "Voulez-vous ajouter un repas ?";
    public static final String MSG_ACTUEL = "actuel : %s";
    public static final String MSG_MODIF_QUESTION = "Voulez-vous modifier ?";
    public static final String MSG_SELECTIONNER_REGIME_ALIMENTAIRE = "Veuillez sélectionner un régime alimentaire :";
    public static final String PATTERN_DATE = "dd/MM/yyyy";
    public static final String SAISIR_DATE_ENTREE = "Veuillez saisir la date d'entrée : ";
    public static final String SAISIR_NUM_SECU = "Veuillez saisir le num de secu : ";
    public static final String SAISIR_NOM = "Veuillez saisir le nom : ";
    public static final String SAISIR_PRENOM = "Veuillez saisir le prénom : ";
    public static final String SAISIR_TYPE_MSG = "veuillez saisir le %s";
    public static final String CONTINUER_SELECTION_REGIME_ALIMENTAIRE = "Voulez-vous ajouter un régime alimentaire ?";
    public static final String ENTETE_REGIME_ALIMENTAIRE_EXISTANT = "\tListe des régimes alimentaires :";
    public static final String AUCUN_REGIME_ALIMENTAIRE = "Aucun régime alimentaire.";


    public static final String MSG_PATIENT_CREATION = "Le patient %s %s a été créé.";
    public static final String MSG_PATIENT_SUPPRESSION = "Le patient %s %s a été supprimé.";
    public static final String MSG_PATIENT_MISE_A_JOUR = "Le patient %s %s a été mis à jour.";
    public static final String CARACTERE_SEPARATEUR = "-------------------------------------------------------------------------------------------------\n";

    public static final String MSG_AUCUN_PATIENT = "Aucun patient disponible.";
    public static final String MSG_PATIENT_INEXISTANT = "Aucun patient correspondant.";
    public static final String MSG_LST_PATIENT_NULL = "La liste de patient est null.";
    public static final String MSG_AUCUN_REPAS = "Aucun repas.";
    public static final String MSG_ERREUR_PATIENT_CREATION = "Echec de la création du patient";
    public static final String MSG_ERREUR_PATIENT_MODIF = "Echec de la modif du patient";
    public static final String MSG_ERREUR_PATIENT_REPAS = "Echec ajout repas";


    private ConstantesVue() {
    }
}
