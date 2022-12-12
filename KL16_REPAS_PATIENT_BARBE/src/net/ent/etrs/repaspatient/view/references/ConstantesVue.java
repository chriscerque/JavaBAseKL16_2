package net.ent.etrs.repaspatient.view.references;

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

    public static final String PATTERN_DATE = "dd/MM/yyyy";
    public static final String SAISIR_DATE_ENTREE = "Veuillez saisir la date d'entrée : (" + ConstantesVue.PATTERN_DATE + ") ";
    public static final String MSG_AUCUN_PATIENT = "Aucun patient disponible.";
    public static final String MSG_AUCUN_REPAS = "Aucun repas.";
    public static final String MENU_NOM = "Repas patient";
    public static final String MSG_REPAS_AJOUTE = "Repas ajouté au patient : ";
    public static final String SAISIR_CLIENT = "Saisir le num de secu : ";
    public static final String SAISIR_REPAS = "Saisir l'id du repas : ";
    public static final String SAISIR_NOM = "Veuillez saisir le nom du client";
    public static final String SAISIR_PRENOM = "Veuillez saisir le prenom";

    private ConstantesVue() {
    }
}
