package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import baseDeDonnees.AccesBD;
import modele.Reservation;

public class Reserver extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) {
		String log = req.getParameter("log");
		String op = req.getParameter("op");
		int novol = Integer.parseInt(req.getParameter("vol"));
		int nbe = Integer.parseInt("nbe");
		Reservation re = new Reservation(0, log, novol, nbe, false);
		if (op.equals("re")) {
			re.confirmer();
			reserver(req, res, re);
		} else
			prereserver(req, res, re);
	}

	private void reserver(HttpServletRequest req, HttpServletResponse res, Reservation re) {
		int statut = AccesBD.reserver(re);
		req.setAttribute("statut", statut);
		req.setAttribute("log", re.getLogin());
		RequestDispatcher d = req.getRequestDispatcher("/vue/reservation.jsp");
		try {
			d.forward(req, res);
		} catch (ServletException | IOException e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
		}
	}

	private void prereserver(HttpServletRequest req, HttpServletResponse res, Reservation re) {
		int statut = AccesBD.prereserver(re);
		req.setAttribute("statut", statut);
		req.setAttribute("log", re.getLogin());
		RequestDispatcher d = req.getRequestDispatcher("/vue/prereservation.jsp");
		try {
			d.forward(req, res);
		} catch (ServletException | IOException e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
		}
	}
}
