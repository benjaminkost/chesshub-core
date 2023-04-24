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

public class GamesWithoutOpponentServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		List<Game> partien = GameDao.getInstance().getGamesByUserId(0);
		req.setAttribute("gamesWithoutOpponent", partien);
		req.getRequestDispatcher("GamesWithoutOpponent.jsp").forward(req, res);
	}

}