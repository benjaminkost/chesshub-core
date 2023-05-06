package Servlets;

import static Management.GameManagement.gameByGameId;
import static Management.GameManagement.gameByTeamId;
import static Servlets.LoginServlet.session;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BusinessObjects.Team;

public class GameByGameIdServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		
		String game = gameByGameId((int) session.getAttribute("userId"), req.getParameter("gameId"));
		if (game.isEmpty() != true) {
			req.setAttribute("game", game);
			req.getRequestDispatcher("PGN_Viewer.jsp").forward(req, res);
		} 
		
		game = gameByTeamId((List<Team>) session.getAttribute("teams"), req.getParameter("gameId"));
		if (game.isEmpty() != true) {
			req.setAttribute("game", game);
			req.getRequestDispatcher("PGN_Viewer.jsp").forward(req, res);
		}
	}
}