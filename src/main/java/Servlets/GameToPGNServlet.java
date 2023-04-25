package Servlets;

import static Servlets.LoginServlet.session;
import static Management.GameManagement.gameToPGN;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GameToPGNServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setAttribute("game",
				gameToPGN(req.getParameter("color"), (int) session.getAttribute("userId"), req.getParameter("result"),
						req.getParameter("date"), req.getParameter("round"), req.getParameter("event"),
						req.getParameter("site"), req.getParameter("opponent")).getGame());
		session.setAttribute("game",
				gameToPGN(req.getParameter("color"), (int) session.getAttribute("userId"), req.getParameter("result"),
						req.getParameter("date"), req.getParameter("round"), req.getParameter("event"),
						req.getParameter("site"), req.getParameter("opponent")));
		req.getRequestDispatcher("PGNDownload.jsp").forward(req, res);
	}

}