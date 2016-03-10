package controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import baseDeDonnees.AccesBD;
import modele.Vol;

public class RechercherVol {
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		// R�cup�ration des param�tres
		String dest = req.getParameter("dest");
		int jour = Integer.parseInt(req.getParameter("jour"));
		int mois = Integer.parseInt(req.getParameter("mois"));
		int annee = Integer.parseInt(req.getParameter("annee"));
		Date date = new Date(annee, mois, jour);
		int nbpers = Integer.parseInt(req.getParameter("nbpers"));

		// Requ�te SQL
		ArrayList<Vol> vols = AccesBD.rechercherVols(dest, date, nbpers);

		// Envoi des informations de traitement � la vue
		req.setAttribute("vols", vols);
		RequestDispatcher d = req.getRequestDispatcher("/vue/reserver.jsp");
		d.forward(req, res);
	}
}
