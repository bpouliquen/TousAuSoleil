package modele;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import baseDeDonnees.AccesBD;

public class Vol {
	private int noVol;
	private String destination;
	private Date dateDepart;
	private int nbePlaces;
	private float prix;
	private static int incVol = 1;
	
	static {
		try {
			ResultSet res = AccesBD.getVols();
			while(res.next())
				incVol++;
		} catch (SQLException e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
		}
	}
	
	public static int ajoutVol() {
		return incVol++;
	}

	public Vol(int noV, String dest, Date dateDep, int nbePla, float prix) {
		this.noVol = noV;
		this.destination = dest;
		this.dateDepart = dateDep;
		this.nbePlaces = nbePla;
		this.prix = prix;
	}

	public int getNoVol() {
		return noVol;
	}

	public String getDestination() {
		return destination;
	}

	public Date getDateDepart() {
		return dateDepart;
	}

	public int getNbePlaces() {
		return nbePlaces;
	}

	public float getPrix() {
		return prix;
	}
	
	public static ArrayList<Vol> rechercherVols(String dest, Date date, int nbpers) {
		return AccesBD.rechercherVols(dest, date, nbpers);
	}
	
	public static Vol ajouterVol(String dest, Date date, int nbePlaces, float prix) {
		return AccesBD.ajouterVol(new Vol(ajoutVol(), dest, date, nbePlaces, prix));
	}
}
