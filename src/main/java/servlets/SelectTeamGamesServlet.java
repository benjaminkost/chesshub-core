package servlets;

import static servlets.LoginServlet.session;
import static management.GameManagement.getTeamGamesJSP;
import static management.TeamManagement.getTeamByID;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import businessObjects.Team;

public class SelectTeamGamesServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		int tester = 0;
		for (Team team : (List<Team>) session.getAttribute("teams")) {
			if (Integer.parseInt(req.getParameter("teamId")) == team.getTeam_ID()) {
				tester++;
				req.setAttribute("teamGames", getTeamGamesJSP(team.getTeam_ID(), getTeamByID(team.getTeam_ID())));
				req.setAttribute("teamName", getTeamByID(team.getTeam_ID()).getName());
				req.getRequestDispatcher("TeamGameAnsicht.jsp").forward(req, res);
			}
		}
		if (tester == 0) {
			req.setAttribute("message", "I'm sorry, but I have to inform you that this is prohibited!");
			req.getRequestDispatcher("Message.jsp").forward(req, res);
		}
	}
}