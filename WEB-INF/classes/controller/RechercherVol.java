package controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modele.Vol;

public class RechercherVol extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) {
		// Récupération des paramètres
		String dest = req.getParameter("dest");
		String temp = req.getParameter("date");
		int nbpers = Integer.parseInt(req.getParameter("nbpers"));
		Date date;
		try {
			date = Date.valueOf(temp);

			// Requête SQL
			ArrayList<Vol> vols = Vol.rechercherVols(dest, date, nbpers);

			// Envoi des informations de traitement à la vue
			req.setAttribute("dest", dest);
			req.setAttribute("date", date.toString());
			req.setAttribute("nbpers", nbpers);
			req.setAttribute("vols", vols);
			RequestDispatcher d = req.getRequestDispatcher("/vue/reserver.jsp");
			d.forward(req, res);
		} catch (ServletException | IOException e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
		}
	}
}
