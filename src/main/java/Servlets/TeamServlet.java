package Servlets;

import BusinessObjects.Club;
import BusinessObjects.Team;
import BusinessObjects.User;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

import static Management.ClubManagement.getManagedClubByUserID;
import static Management.TeamManagement.addMemberToTeam;
import static Management.TeamManagement.getManagedTeamByUserID;
import static Management.UserManagement.*;
import static Servlets.LoginServlet.session;

public class TeamServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        int userID = (int) session.getAttribute("userID");
        Team team = getManagedTeamByUserID(userID);

        req.setAttribute("team", team);

        req.getRequestDispatcher("Team.jsp").forward(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String i_mail = req.getParameter("mail");

        User newUser = getUserByMail(i_mail);

        int userID = (int) session.getAttribute("userID");
        Team team = getManagedTeamByUserID(userID);

        addMemberToTeam(team, newUser);

        req.setAttribute("message", "User added");
        req.getRequestDispatcher("Message.jsp").forward(req, res);
    }
}