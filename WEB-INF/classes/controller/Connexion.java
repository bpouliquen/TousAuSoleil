package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modele.Compte;

public class Connexion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) {
		// Récupération des paramètres
		String login = req.getParameter("login");
		String mdp = req.getParameter("mdp");
		try {
			// Requête SQL
			Compte c = Compte.connecter(login, mdp);

			// Envoi des informations de traitement à la vue
			RequestDispatcher d;
			if(c != null) {
				HttpSession session = req.getSession();
				session.setAttribute("log", c.getLogin());
				
				if(c.getLogin().equals("admin"))
					d = req.getRequestDispatcher("/vue/accueil-admin.jsp");
				else {
					req.setAttribute("compte", c);
					d = req.getRequestDispatcher("/vue/connexion.jsp");
				}
			}
			else
				d = req.getRequestDispatcher("/vue/connexion.jsp");
			
			d.forward(req, res);
		} catch (IOException | ServletException e) {
			e.printStackTrace();
		}
	}
}
