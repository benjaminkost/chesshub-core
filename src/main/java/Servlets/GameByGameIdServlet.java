package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BusinessObjects.Game;
import dao.GameDao;

public class GameByGameIdServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		GameDao gameDao = GameDao.getInstance();
		Game partie = gameDao.getGameById(Integer.parseInt(req.getParameter("gameId")));
		req.setAttribute("moves", partie.getGame());
		req.getRequestDispatcher("PGN_Viewer.jsp").forward(req, res);
	}
}