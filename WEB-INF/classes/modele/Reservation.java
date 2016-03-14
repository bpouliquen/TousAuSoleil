package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import baseDeDonnees.AccesBD;

public class Reservation {
	private int noReservation;
	private String login;
	private int noVol;
	private int nbePlaces;
	private boolean confirmation;
	private static int incRes = 1;
	
	static {
		try {
		ResultSet res = AccesBD.getReservations();
			while(res.next())
				incRes++;
		} catch (SQLException e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
		}
	}
	
	private static int ajoutReservation() {
		return incRes++;
	}
	
	public Reservation(int noRes, String log, int noV, int nbePla, boolean conf) {
		this.noReservation = noRes;
		this.login = log;
		this.noVol = noV;
		this.nbePlaces = nbePla;
		this.confirmation = conf;
	}

	public int getNoReservation() {
		return noReservation;
	}

	public String getLogin() {
		return login;
	}

	public int getNoVol() {
		return noVol;
	}

	public int getNbePlaces() {
		return nbePlaces;
	}

	public boolean getConfirmation() {
		return confirmation;
	}

	public void confirmer() {
		this.confirmation = true;
	}
	
	public static int confirmerReservation(int noRes) {
		return AccesBD.confirmerReservation(noRes);
	}
	
	public static ArrayList<Reservation> getReservations(String log) {
		return AccesBD.getReservations(log);
	}
	
	public static Reservation reserver(int noVol, int nbePlaces, String login) {
		Reservation re = new Reservation(ajoutReservation(), login, noVol, nbePlaces, true);
		return AccesBD.reserver(re);
	}
	
	public static Reservation prereserver(int noVol, int nbePlaces, String login) {
		Reservation re = new Reservation(ajoutReservation(), login, noVol, nbePlaces, false);
		return AccesBD.reserver(re);
	}
}
