package Servlets;

import static Servlets.LoginServlet.session;
import static Management.GameManagement.gameDownload;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BusinessObjects.Game;

public class GameDownloadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		gameDownload((Game) session.getAttribute("game"), req.getParameter("eMoves"));
	}
}