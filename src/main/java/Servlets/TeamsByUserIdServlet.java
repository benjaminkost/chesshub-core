package Servlets;

import static Servlets.LoginServlet.session;
import static Management.UserManagement.getTeamsByUserId;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TeamsByUserIdServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setAttribute("teams", getTeamsByUserId((int) session.getAttribute("userId")));
		req.getRequestDispatcher("SelectTeam.jsp").forward(req, res);
	}

}