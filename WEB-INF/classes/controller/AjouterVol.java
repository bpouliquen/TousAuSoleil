package controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.Vol;

public class AjouterVol extends HttpServlet {
	private static final long serialVersionUID = 1L;

		protected void doGet(HttpServletRequest req, HttpServletResponse res) {
			try {
				String dest = req.getParameter("dest");
				Date date = Date.valueOf(req.getParameter("date"));
				int nbePlaces = Integer.parseInt(req.getParameter("nbeplaces"));
				float prix = Float.parseFloat(req.getParameter("prix"));
				req.setAttribute("vol", Vol.ajouterVol(dest, date, nbePlaces, prix));
				RequestDispatcher d = req.getRequestDispatcher("/vue/vol-ajoute.jsp");
				d.forward(req, res);
			} catch (IOException | ServletException e) {
				e.printStackTrace();
			}
		}
}
