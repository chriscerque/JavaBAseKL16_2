package net.ent.etrs.repaspatient.model.entities;

public class ConstanteModel {
    public static final String DATE_NAISSANCE_NULL_EXCEPTION = "Date de naissance null, valeur incorrect";
    public static final String DATE_NAISSANCE_INCORRECT_EXCEPTION = "Date de naissance apres la date du jour, valeur incorrect";
    public static final String NUM_SECU_NULL_EXCEPTION = "Numéro de sécurité sociale null, valeur incorrect";
    public static final int TAILLE_NUM_SECU = 5;
    public static final String NUM_SECU_TAILLE_EXCEPTION = "Le numéro de sécurité sociale doit contenir 5 caractères";
    public static final String NOM_NULL_EXCEPTION = "Le nom entré est null, valeur incorrect";
    public static final int TAILLE_MINI_NOM_PRENOM = 3;
    public static final String NOM_PATIENT_EXCEPTION = "Le nom du patient doit contenir entre 3 et 50 caractères";
    public static final int TAILLE_MAXI_NOM_PRENOM = 50;
    public static final String PRENOM_PATIENT_EXCEPTION = "Le prénom du patient doit contenir entre 3 et 50 caractères";
    public static final String PRENOM_NULL_EXCEPTION = "Le prénom du patient est null, valeur incorrect";
    public static final String REGIME_ALIMENTAIRE_NON_RESPECTE_EXCEPTION = "Régime alimentaire non respecté";
    public static final String TYPE_REPAS_NULL_EXCEPTION = "Type de repas null, valeur incorrect";
    public static final String DATE_REPAS_NULL_EXCEPTION = "La date du repas est null, valeur incorrect";
}
