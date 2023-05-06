package Servlets;

import BusinessObjects.*;
import static Management.ClubManagement.getManagedClubByUserID;
import static Management.TeamManagement.getManagedTeamByUserID;
import static Management.UserManagement.*;
import static Servlets.LoginServlet.session;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	protected static HttpSession session;

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		String i_mail = req.getParameter("mail").toLowerCase();
		String i_password = req.getParameter("psw");

		int login = loginUser(i_mail,i_password);
		
		if(login==-1){
			req.setAttribute("message", "Error: User doesn't exist!");
			req.getRequestDispatcher("Message.jsp").forward(req, res);
		}
		else if (login==-2) {
			req.setAttribute("message", "Error: Wrong password!");
			req.getRequestDispatcher("Message.jsp").forward(req, res);
		}else {
			session = req.getSession();
			session.setAttribute("userId", login);
			session.setAttribute("welcome", "Log in successfully! Welcome "+ getUserById(login).getFullName());
			session.setAttribute("teams", getTeamsByUserId(login));

			//if User leads a team
			Team managedTeam = getManagedTeamByUserID(login);
			if(managedTeam!=null){
				session.setAttribute("team", managedTeam);
			}
			else {
				session.setAttribute("team", null);
			}
			Club managedClub = getManagedClubByUserID(login);
			//if User leads a club
			if(managedClub!=null){
				session.setAttribute("club", managedClub);
			}
			else {
				session.setAttribute("club", null);
			}

			req.getRequestDispatcher("Menu.jsp").forward(req, res);
		}
		
	}

}