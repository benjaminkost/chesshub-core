package servlets;

import static servlets.LoginServlet.session;
import static management.GameManagement.getGamesWithoutOpponentJSP;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GamesWithoutOpponentServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setAttribute("gamesWithoutOpponent", getGamesWithoutOpponentJSP((int) session.getAttribute("userId")));
		req.getRequestDispatcher("GamesWithoutOpponent.jsp").forward(req, res);
	}

}