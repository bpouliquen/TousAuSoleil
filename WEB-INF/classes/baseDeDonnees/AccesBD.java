package baseDeDonnees;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modele.*;

public class AccesBD {
	private static String user = "brieuc";
	private static String password = "kaola02";
	private static String url = "jdbc:oracle:thin:@127.0.0.1:1521";
	private static Connection co;

	static {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			co = DriverManager.getConnection(url, user, password);
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
		}
	}
	
	public static ResultSet getVols() throws SQLException {
		 Statement srchVols = co.createStatement();
			return srchVols.executeQuery("SELECT numerovol FROM VOLS");
	}
	
	public static ResultSet getReservations() throws SQLException {
			Statement srchRes = co.createStatement();
			return srchRes.executeQuery("SELECT numerores FROM RESERVATIONS");
	}

	public static int ajouterCompte(Compte c) {
		// Vérifier que le pseudo n'existe pas déjà
		try {
			PreparedStatement st1 = co.prepareStatement("SELECT login FROM COMPTES WHERE login=?");
			st1.setString(1, c.getLogin());
			ResultSet res = st1.executeQuery();
			if (res.next()) {
				return -1;
			}

			// Requête SQL vers la base de données
			PreparedStatement st2 = co
					.prepareStatement("INSERT INTO COMPTES (login,mdp,nom,prenom,role) VALUES (?,?,?,?,?)");
			st2.setString(1, c.getLogin());
			st2.setString(2, c.getMdp());
			st2.setString(3, c.getNom());
			st2.setString(4, c.getPrenom());
			st2.setString(5, c.getRole());
			return st2.executeUpdate();
		} catch (SQLException e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
			return 0;
		}
	}

	public static Compte connecter(String login, String mdp) {
		try {
			PreparedStatement st = co.prepareStatement("SELECT * FROM COMPTES WHERE login=? AND mdp=?");
			st.setString(1, login);
			st.setString(2, mdp);
			ResultSet res = st.executeQuery();
			res.next();
			Compte c = new Compte(res.getString("login"), res.getString("mdp"), res.getString("nom"),
					res.getString("prenom"), res.getString("role"));
			return c;
		} catch (SQLException e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
			return null;
		}
	}

	public static Compte getCompte(String login) {
		try {
			PreparedStatement st = co.prepareStatement("SELECT * FROM COMPTES WHERE login=?");
			st.setString(1, login);
			ResultSet res = st.executeQuery();
			res.next();
			Compte c = new Compte(res.getString("login"), res.getString("mdp"), res.getString("nom"),
					res.getString("prenom"), res.getString("role"));
			return c;
		} catch (SQLException e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
			return null;
		}
	}

	public static ArrayList<Vol> rechercherVols(String dest, Date date, int nbPers) {
		try {
			ArrayList<Vol> vols = new ArrayList<Vol>();
			PreparedStatement st = co
					.prepareStatement("SELECT * FROM VOLS WHERE destination=? AND datedepart=? AND nombreplaces>=?");
			st.setString(1, dest);
			st.setDate(2, date);
			st.setInt(3, nbPers);
			ResultSet res = st.executeQuery();
			while (res.next()) {
				Vol v = new Vol(res.getInt("numerovol"), res.getString("destination"), res.getDate("datedepart"),
						res.getInt("nombreplaces"), res.getFloat("prix"));
				vols.add(v);

			}
			return vols;
		} catch (SQLException e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
			return null;
		}
	}

	public static Reservation reserver(Reservation re) {
		try {
			PreparedStatement st1 = co
					.prepareStatement("UPDATE VOLS SET nombreplaces=nombreplaces-? WHERE numerovol=?");
			st1.setInt(1, re.getNbePlaces());
			st1.setInt(2, re.getNoVol());
			int statut = st1.executeUpdate();
			if (statut != 0) {
				PreparedStatement st2 = co.prepareStatement(
						"INSERT INTO RESERVATIONS(numerores,login,numerovol,nombreplaces,confirmation) VALUES (?,?,?,?,?)");
				st2.setInt(1, re.getNoReservation());
				st2.setString(2, re.getLogin());
				st2.setInt(3, re.getNoVol());
				st2.setInt(4, re.getNbePlaces());
				if (re.getConfirmation())
					st2.setString(5, "OUI");
				else
					st2.setString(5, "NON");
				statut = st2.executeUpdate();
			}
			if(statut != 0)
				return re;
			else
				return null;
		} catch (SQLException e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
			return null;
		}
	}

	public static ArrayList<Reservation> getReservations(String log) {
		ArrayList<Reservation> lRes = new ArrayList<Reservation>();
		try {
			PreparedStatement st = co.prepareStatement("SELECT * FROM RESERVATIONS WHERE login=?");
			st.setString(1, log);
			ResultSet res = st.executeQuery();
			while (res.next()) {
				String temp = res.getString("confirmation");
				boolean conf;
				if (temp.equals("OUI"))
					conf = true;
				else
					conf = false;
				lRes.add(new Reservation(res.getInt("numerores"), res.getString("login"), res.getInt("numerovol"),
						res.getInt("nombreplaces"), conf));
			}
			return lRes;
		} catch (SQLException e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
			return null;
		}
	}

	public static int confirmerReservation(int noRes) {
		try {
			PreparedStatement st = co.prepareStatement("UPDATE RESERVATIONS SET confirmation='OUI' WHERE numerores=?");
			st.setInt(1, noRes);
			int statut = st.executeUpdate();
			return statut;
		} catch (SQLException e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
			return 0;
		}
	}
	
	public static Vol ajouterVol(Vol vol) {
		try {
			PreparedStatement st = co.prepareStatement("INSERT INTO VOLS (numerovol,destination,datedepart,nombreplaces,prix) VALUES (?,?,?,?,?)");
			st.setInt(1, vol.getNoVol());
			st.setString(2, vol.getDestination());
			st.setDate(3, vol.getDateDepart());
			st.setInt(4, vol.getNbePlaces());
			st.setFloat(5, vol.getPrix());
			int statut = st.executeUpdate();
			if(statut != 0)
				return vol;
			else
				return null;
		} catch (SQLException e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
			return null;
		}
	}
}
