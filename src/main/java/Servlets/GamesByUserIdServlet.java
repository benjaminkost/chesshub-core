package Servlets;

import static Servlets.LoginServlet.session;
import static Management.GameManagement.getMyGamesJSP;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GamesByUserIdServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setAttribute("myGames", getMyGamesJSP((int) session.getAttribute("userId")));
		req.getRequestDispatcher("GameAnsicht.jsp").forward(req, res);
	}

}