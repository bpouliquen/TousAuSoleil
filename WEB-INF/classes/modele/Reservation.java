package modele;

public class Reservation {
	private int noReservation;
	private String login;
	private int noVol;
	private int nbePlaces;
	private boolean confirmation;

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
}
