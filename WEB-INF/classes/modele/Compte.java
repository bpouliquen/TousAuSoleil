package modele;

import baseDeDonnees.AccesBD;

public class Compte {
	private String login;
	private String mdp;
	private String nom;
	private String prenom;
	private String role;

	public Compte(String log, String mdp, String nom, String prenom, String role) {
		this.login = log;
		this.mdp = mdp;
		this.nom = nom;
		this.prenom = prenom;
		this.role = role;
	}

	public String getLogin() {
		return login;
	}

	public String getMdp() {
		return mdp;
	}

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public String getRole() {
		return role;
	}

	public static int ajouterCompte(Compte c) {
		return AccesBD.ajouterCompte(c);
	}

	public static Compte connecter(String login, String mdp) {
		return AccesBD.connecter(login, mdp);
	}

	public static Compte getCompte(String login) {
		return AccesBD.getCompte(login);
	}
}
