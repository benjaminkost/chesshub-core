package Servlets;

import static Servlets.LoginServlet.session;
import static Management.GameManagement.gameByGameId;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GameByGameIdServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		String game = gameByGameId((int) session.getAttribute("userId"), req.getParameter("gameId"));
		if (game.isEmpty() != true) {
			req.setAttribute("game", game);
			req.getRequestDispatcher("PGN_Viewer.jsp").forward(req, res);
		}
	}
}