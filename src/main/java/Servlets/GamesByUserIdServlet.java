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

public class GamesByUserIdServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		PrintWriter out = res.getWriter();
		res.setContentType("text/html");
		out.println("<html><body>");
		GameDao gameDao = GameDao.getInstance();
		List<Game> partien = gameDao.getAllGames();
		out.println("<table border=1 width=100% height=50%>");
		out.println("<col style=width:5%>");
		out.println("<col style=width:10%>");
		out.println("<col style=width:85%>");
		out.println("<tr><th>Game ID</th><th>Date</th><th>Moves</th><tr>");
		for (Game partie : partien) {
			out.println("<tr><td>" + partie.getGame_ID() + "</td><td>" + partie.getDate() + "</td><td>"
					+ partie.getMoves() + "</td></tr>");
		}
		out.println("</table>");
		out.println("<br><form action=GameByGameIdServlet>");
		out.println("GameID: <input type=text name=gameId>");
		out.println("<input type=submit>");
		out.println("</form></html></body>");
	}
}