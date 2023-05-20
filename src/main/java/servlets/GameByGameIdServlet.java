package servlets;

import static management.GameManagement.gameByGameId;
import static management.GameManagement.gameByTeamId;
import static servlets.LoginServlet.session;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import businessObjects.Team;

public class GameByGameIdServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		String game = gameByGameId((int) session.getAttribute("userId"), req.getParameter("gameId"));
		if (game.isEmpty() != true) {
			req.setAttribute("game", game);
			req.getRequestDispatcher("PGN_Viewer.jsp").forward(req, res);
		} else {
			game = gameByTeamId((List<Team>) session.getAttribute("teams"), req.getParameter("gameId"));
			if (game.isEmpty() != true) {
				req.setAttribute("game", game);
				req.getRequestDispatcher("PGN_Viewer.jsp").forward(req, res);
			} else {
				req.setAttribute("message", "I'm sorry, but I have to inform you that this is prohibited!");
				req.getRequestDispatcher("Message.jsp").forward(req, res);
			}
		}
	}
}