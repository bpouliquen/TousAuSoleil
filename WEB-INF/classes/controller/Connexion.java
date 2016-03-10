package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modele.Compte;

public class Connexion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		// Récupération des paramètres
		String login = req.getParameter("login");
		String mdp = req.getParameter("mdp");

		// Requête SQL
		Compte c = Compte.connecter(login, mdp);

		// Envoi des informations de traitement à la vue
		req.setAttribute("compte", c);
		RequestDispatcher d = req.getRequestDispatcher("/vue/connexion.jsp");
		d.forward(req, res);
	}
}
