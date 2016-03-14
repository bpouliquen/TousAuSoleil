package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modele.Reservation;

public class ControlerReservations extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) {
		ArrayList<Reservation> lRes = Reservation.getReservations(((String) req.getSession().getAttribute("log")));
		try {
			req.setAttribute("res", lRes);
			RequestDispatcher d = req.getRequestDispatcher("/vue/liste-reservations.jsp");
			d.forward(req, res);
		} catch (ServletException | IOException e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
		}
	}
}
