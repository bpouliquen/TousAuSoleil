package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Deconnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) {
		try {
			HttpSession session = req.getSession();
			session.removeAttribute("log");
			session.invalidate();
			RequestDispatcher d = req.getRequestDispatcher("/index.html");
			d.forward(req, res);
		} catch (IOException | ServletException e) {
			e.printStackTrace();
		}
	}
}
