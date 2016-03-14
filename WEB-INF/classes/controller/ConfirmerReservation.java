package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modele.Reservation;

public class ConfirmerReservation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) {
		int noRes = Integer.parseInt(req.getParameter("res"));
		int statut = Reservation.confirmerReservation(noRes);
		try {
			req.setAttribute("nores", noRes);
			req.setAttribute("statut", statut);
			RequestDispatcher d = req.getRequestDispatcher("/vue/reservation-confirmee.jsp");
			d.forward(req, res);
		} catch (ServletException | IOException e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
		}
	}
}
