package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modele.Compte;

public class CreerCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		// R�cup�ration des param�tres
		Compte c = new Compte(req.getParameter("login"), req.getParameter("mdp"), req.getParameter("nom"),
				req.getParameter("prenom"), "Client");

		// Requ�te SQL
		int statut = 0;
		statut = Compte.ajouterCompte(c);

		// Envoi des informations de traitement � la vue
		req.setAttribute("compte", c);
		req.setAttribute("statut", statut);
		RequestDispatcher d = req.getRequestDispatcher("/vue/compte-ajoute.jsp");
		d.forward(req, res);
	}
}
