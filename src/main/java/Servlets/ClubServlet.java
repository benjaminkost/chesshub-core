package Servlets;

import BusinessObjects.Club;
import BusinessObjects.Team;
import BusinessObjects.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static Management.ClubManagement.getAllTeamsOfClub;
import static Management.ClubManagement.getManagedClubByUserID;
import static Management.TeamManagement.*;
import static Management.TeamManagement.removeMemberFromTeam;
import static Management.UserManagement.getUserById;
import static Management.UserManagement.getUserByMail;
import static Servlets.LoginServlet.session;

public class ClubServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        int userID = (int) session.getAttribute("userId");

        Club managedClub = getManagedClubByUserID(userID);
        List<Team> teamsOfClub = getAllTeamsOfClub(managedClub);

        req.setAttribute("club", managedClub);
        req.setAttribute("teams", teamsOfClub);

        req.getRequestDispatcher("Club.jsp").forward(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        String form = req.getParameter("form");

        int userID = (int) session.getAttribute("userId");

        Club managedClub = getManagedClubByUserID(userID);

        //Entscheidung, welches Formular gesendet wurde
        if(form.equals("creatingTeam")){
            String i_leader_mail = req.getParameter("leader_mail");
            String i_team_name = req.getParameter("team_name");

            User leader = getUserByMail(i_leader_mail);

            if(leader!=null){
                addTeam(new Team(i_team_name,managedClub,leader));
                req.setAttribute("message", "Team added to club!");
                req.getRequestDispatcher("Message.jsp").forward(req, res);
            }
            else {
                req.setAttribute("message", "Error: Leader not found");
                req.getRequestDispatcher("Message.jsp").forward(req, res);
            }

        } else if (form.equals("changingLeader")) {
            String i_leader_mail = req.getParameter("leader_mail");
            String i_team_id = req.getParameter("team_ID");

            int teamID = -1;

            try {
                teamID = Integer.parseInt(i_team_id);
            } catch (NumberFormatException e) {
                req.setAttribute("message", "Error: Please insert a number!");
                req.getRequestDispatcher("Message.jsp").forward(req, res);
            }

            User newLeader = getUserByMail(i_leader_mail);
            Team changedTeam = getTeamByID(teamID);



            if(changedTeam==null){
                req.setAttribute("message", "Error: Team doesn't exist!");
                req.getRequestDispatcher("Message.jsp").forward(req, res);
            }

            System.out.println(changedTeam.getTeam_ID());
            //System.out.println(newLeader.getUser_Id());

                if (newLeader != null) {
                    changeTeamLeader(changedTeam, newLeader);
                    req.setAttribute("message", "Leader changed!");
                    req.getRequestDispatcher("Message.jsp").forward(req, res);
                } else {
                    req.setAttribute("message", "Error: Leader not found");
                    req.getRequestDispatcher("Message.jsp").forward(req, res);
                }


        } else if (form.equals("removingTeam")) {
            String i_team_id = req.getParameter("team_ID");

            int teamID = -2;

            try {
                teamID = Integer.parseInt(i_team_id);
            } catch (NumberFormatException e) {
                req.setAttribute("message", "Error: Please insert a number!");
                req.getRequestDispatcher("Message.jsp").forward(req, res);
            }

            Team oldTeam = getTeamByID(teamID);

            if(oldTeam==null){
                req.setAttribute("message", "Error: Team doesn't exist!");
                req.getRequestDispatcher("Message.jsp").forward(req, res);
            }

            else {
                removeTeam(oldTeam);

                req.setAttribute("message", "Team removed");
                req.getRequestDispatcher("Message.jsp").forward(req, res);
            }


        } else {
            req.setAttribute("message", "Error: Unkown issue");
            req.getRequestDispatcher("Message.jsp").forward(req, res);
        }


    }
}