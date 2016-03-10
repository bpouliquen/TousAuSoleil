package modele;

import java.sql.Date;

public class Vol {
	private int noVol;
	private String destination;
	private Date dateDepart;
	private int nbePlaces;
	private float prix;

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
	
}
