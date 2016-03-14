package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modele.Reservation;

public class Reserver extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) {
		String op = req.getParameter("op");
		int novol = Integer.parseInt(req.getParameter("vol"));
		int nbe = Integer.parseInt(req.getParameter("nbe"));
		HttpSession session = req.getSession();
		try {
			Reservation re;
			if(op.equals("re"))
				re = Reservation.reserver(novol, nbe, (String) session.getAttribute("log"));
			else
				re = Reservation.prereserver(novol, nbe, (String) session.getAttribute("log"));
			req.setAttribute("re", re);
			RequestDispatcher d = req.getRequestDispatcher("/vue/reservation.jsp");
			d.forward(req, res);
		} catch (ServletException | IOException e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
		}
	}
}
