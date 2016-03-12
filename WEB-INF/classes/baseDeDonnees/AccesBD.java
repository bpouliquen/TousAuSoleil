package baseDeDonnees;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

	public static int reserver(Reservation re) {
		int statut=0;
		try {
			PreparedStatement st1 = co.prepareStatement("UPDATE TABLE VOLS SET nombreplaces=nombreplaces-? WHERE novol=?");
			st1.setInt(1, re.getNbePlaces());
			st1.setInt(2, re.getNoVol());
			statut = st1.executeUpdate();
			if(statut!=0) {
				PreparedStatement st2 = co.prepareStatement("INSERT INTO RESERVATION(numerores,login,numerovol,nombreplaces,confirmation) VALUES (?,?,?,?,?)");
				st2.setInt(1, 1); //A CHANGER
				st2.setString(2, re.getLogin());
				st2.setInt(3, re.getNoVol());
				st2.setInt(4, re.getNbePlaces());
				st2.setString(5, "OUI");
				statut = st2.executeUpdate();
			}
			return statut;
		} catch (SQLException e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
			return 0;
		}
	}

	public static int prereserver(Reservation re) {
		int statut=0;
		try {
			PreparedStatement st1 = co.prepareStatement("UPDATE TABLE VOLS SET nombreplaces=nombreplaces-? WHERE novol=?");
			st1.setInt(1, re.getNbePlaces());
			st1.setInt(2, re.getNoVol());
			statut = st1.executeUpdate();
			if(statut!=0) {
				PreparedStatement st2 = co.prepareStatement("INSERT INTO RESERVATION(numerores,login,numerovol,nombreplaces,confirmation) VALUES (?,?,?,?,?)");
				st2.setInt(1, 1); //A CHANGER
				st2.setString(2, re.getLogin());
				st2.setInt(3, re.getNoVol());
				st2.setInt(4, re.getNbePlaces());
				st2.setString(5, "NON");
				statut = st2.executeUpdate();
			}
			return statut;
		} catch (SQLException e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
			return 0;
		}
	}
}
