package Servlets;

import static Management.GameManagement.gamesByTeamId;
import static Management.TeamManagement.getTeamByID;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class SelectTeamGamesServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		req.setAttribute("partien", gamesByTeamId(Integer.parseInt(req.getParameter("teamId"))));
		req.setAttribute("team", getTeamByID(Integer.parseInt(req.getParameter("teamId"))));
		req.setAttribute("teamName", getTeamByID(Integer.parseInt(req.getParameter("teamId"))).getName());
		req.getRequestDispatcher("TeamGameAnsicht.jsp").forward(req, res);
	}
}