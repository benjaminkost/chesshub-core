package Servlets;

import BusinessObjects.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;
import static Management.ClubManagement.getAllTeamsOfClub;
import static Management.ClubManagement.getManagedClubByUserID;
import static Management.TeamManagement.*;
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
            
            if(leader==null){
                req.setAttribute("message", "Error: Leader not found");
                req.getRequestDispatcher("Message.jsp").forward(req, res);
            } else if (getManagedTeamByUserID(leader.getUser_Id())!=null) {
                req.setAttribute("message", "Error: User already leads a team!");
                req.getRequestDispatcher("Message.jsp").forward(req, res);           
            } else if (teamNameAlreadyExistsInClub(managedClub,i_team_name)) {
                req.setAttribute("message", "Error: Name already used in your club!");
                req.getRequestDispatcher("Message.jsp").forward(req, res);
            } else {
                addTeam(new Team(i_team_name,managedClub,leader));
                req.setAttribute("message", "Team added to club!");
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
            } else if (newLeader != null) {
                if(getManagedTeamByUserID(newLeader.getUser_Id())!=null){
                    req.setAttribute("message", "Error: User already leads a team!");
                    req.getRequestDispatcher("Message.jsp").forward(req, res);
                }
                else {
                    changeTeamLeader(changedTeam, newLeader);
                    req.setAttribute("message", "Leader changed!");
                    req.getRequestDispatcher("Message.jsp").forward(req, res);
                }
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

            } else if (oldTeam.getClub().getClub_ID()!=managedClub.getClub_ID()) {
                req.setAttribute("message", "Error: Selected Team doesn't belong to your club!");
                req.getRequestDispatcher("Message.jsp").forward(req, res);

            } else {
                removeTeam(oldTeam);
                req.setAttribute("message", "Team removed");
                req.getRequestDispatcher("Message.jsp").forward(req, res);
            }

        // no formular activated
        } else {
            req.setAttribute("message", "Error: Unkown issue");
            req.getRequestDispatcher("Message.jsp").forward(req, res);
        }


    }
}