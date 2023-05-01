package Servlets;

import BusinessObjects.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

import static Management.TeamManagement.*;
import static Management.UserManagement.*;
import static Servlets.LoginServlet.session;

public class TeamServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        int userID = (int) session.getAttribute("userId");
        Team team = getManagedTeamByUserID(userID);
        List<User> members = getUsersFromTeam(team);

        req.setAttribute("team", team);
        req.setAttribute("members", members);

        req.getRequestDispatcher("Team.jsp").forward(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        String form = req.getParameter("form");

        //Entscheidung, welches Formular gesendet wurde
        if(form.equals("adding")){
            String i_mail = req.getParameter("mail");

            User newUser = getUserByMail(i_mail);

            if (newUser != null){
                int userID = (int) session.getAttribute("userId");
                Team team = getManagedTeamByUserID(userID);

                addMemberToTeam(team,newUser);

                req.setAttribute("message", "User added");
                req.getRequestDispatcher("Message.jsp").forward(req, res);
            }
            else {
                req.setAttribute("message", "Error: User not found");
                req.getRequestDispatcher("Message.jsp").forward(req, res);

            }

        } else if (form.equals("removing")){
            String ID_string = req.getParameter("remove_id");

            int removeID=-1;

            try {
                removeID = Integer.parseInt(ID_string);
            } catch (NumberFormatException e) {
                req.setAttribute("message", "Error: Please insert a number!");
                req.getRequestDispatcher("Message.jsp").forward(req, res);
            }

            User oldUser = getUserById(removeID);

            //Guard clause, if user is not found
            if(oldUser == null){
                req.setAttribute("message", "Error: User not found");
                req.getRequestDispatcher("Message.jsp").forward(req, res);
            }

            int managerID = (int) session.getAttribute("userId");

            Team managedTeam = getManagedTeamByUserID(managerID);

            for (User u: getUsersFromTeam(managedTeam) ){
                System.out.println(u.getUser_Id());
            }

            System.out.println("User: " + oldUser.getUser_Id());

            if (getUsersFromTeam(managedTeam).contains(oldUser)){
                removeMemberFromTeam(managedTeam,oldUser);
                req.setAttribute("message", "User removed");
                req.getRequestDispatcher("Message.jsp").forward(req, res);
            }
            else {
                req.setAttribute("message", "Error: User isn't part of your team!");
                req.getRequestDispatcher("Message.jsp").forward(req, res);
            }

        }
        else {
            req.setAttribute("message", "Error: Unkown issue");
            req.getRequestDispatcher("Message.jsp").forward(req, res);
        }


    }
}