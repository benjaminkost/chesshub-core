package Servlets;

import static Servlets.LoginServlet.session;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;

public class GameToPGNServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		if (req.getParameter("color").equals("White")) req.setAttribute("game", "[Result \"" + req.getParameter("result") + "\"] [Date \"" + req.getParameter("date") + "\"] [Round \"" + req.getParameter("round") + "\"] [Event \"" + req.getParameter("event") + "\"] [Black \"" + req.getParameter("opponent") + "\"] [White \"" + UserDao.getInstance().getUserById((int) session.getAttribute("userId")).getFullName() + "\"] [Site \"" + req.getParameter("site") + "\"]" + "1. ");
		else req.setAttribute("game", "[Result \"" + req.getParameter("result") + "\"] [Date \"" + req.getParameter("date") + "\"] [Round \"" + req.getParameter("round") + "\"] [Event \"" + req.getParameter("event") + "\"] [Black \"" + UserDao.getInstance().getUserById((int) session.getAttribute("userId")).getFullName() + "\"] [White \"" + req.getParameter("opponent") + "\"] [Site \"" + req.getParameter("site") + "\"]" + "1. ");
		req.getRequestDispatcher("PGN_Viewer.jsp").forward(req, res);
	}

}