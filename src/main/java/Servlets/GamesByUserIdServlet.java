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

public class GamesByUserIdServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		GameDao gameDao = GameDao.getInstance();
		List<Game> partien = gameDao.getGamesByUserId((int) session.getAttribute("userId"));

		if (partien.isEmpty()) {
			req.setAttribute("noGames", "You have not any Games!");
		} else {
			req.setAttribute("noGames", "");
			req.setAttribute("partien", partien);
		}

		req.getRequestDispatcher("GameAnsicht.jsp").forward(req, res);
	}

}