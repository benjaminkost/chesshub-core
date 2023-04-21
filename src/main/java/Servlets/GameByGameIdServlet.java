package Servlets;

import static Servlets.LoginServlet.session;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BusinessObjects.Game;
import dao.GameDao;

public class GameByGameIdServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		GameDao gameDao = GameDao.getInstance();
		List<Game> partien = gameDao.getGamesByUserId((int) session.getAttribute("userId"));
		Game partie = gameDao.getGameById(Integer.parseInt(req.getParameter("gameId")));
		for (Game partieVergleich : partien) {
			if (partie.getGame_ID() == partieVergleich.getGame_ID()) {
				req.setAttribute("game", partie.getGame());
				req.getRequestDispatcher("PGN_Viewer.jsp").forward(req, res);
			}
		}
	}
}