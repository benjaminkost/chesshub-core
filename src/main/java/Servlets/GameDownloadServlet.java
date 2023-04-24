package Servlets;

import static Servlets.LoginServlet.session;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BusinessObjects.Game;
import dao.GameDao;


public class GameDownloadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		Game game = (Game) session.getAttribute("game");
		game.setMoves(req.getParameter("eMoves"));
		GameDao.getInstance().insertGame(game);
	}
}