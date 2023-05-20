package servlets;

import businessObjects.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static management.ClubManagement.*;
import static management.UserManagement.getUserById;
import static management.UserManagement.getUserByMail;
import static servlets.LoginServlet.session;

public class AdminServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        int userID = (int) session.getAttribute("userId");
        User currentUser = getUserById(userID);

        if(currentUser==null){
            req.setAttribute("message", "Error: User not found");
            req.getRequestDispatcher("Message.jsp").forward(req, res);

        } else if (currentUser.getEmail().equals("administrator@management.com")) {
            List<Club> allClubs = getAllClubs();
            req.setAttribute("clubs", allClubs);
            req.getRequestDispatcher("Admin.jsp").forward(req,res);
        } else {
            req.setAttribute("message", "Error: Access denied");
            req.getRequestDispatcher("Message.jsp").forward(req, res);
        }
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String form = req.getParameter("form");

        //Decision, which form was send
        if(form.equals("creatingClub")){
            String i_leader_mail = req.getParameter("leader_mail");
            String i_club_name = req.getParameter("club_name");

            User leader = getUserByMail(i_leader_mail);
            
            if(leader==null){
                req.setAttribute("message", "Error: Leader not found");
                req.getRequestDispatcher("Message.jsp").forward(req, res);
            } else if (getManagedClubByUserID(leader.getUser_Id())!=null) {
                req.setAttribute("message", "Error: User already leads a club!");
                req.getRequestDispatcher("Message.jsp").forward(req, res);           
            } else if (clubNameAlreadyExists(i_club_name)) {
                req.setAttribute("message", "Error: Name already used!");
                req.getRequestDispatcher("Message.jsp").forward(req, res);
            } else {
                createClub(new Club(i_club_name,leader));
                req.setAttribute("message", "Club created!");
                req.getRequestDispatcher("Message.jsp").forward(req, res);  
            }

        } else if (form.equals("changingLeader")) {

            String i_leader_mail = req.getParameter("leader_mail");
            String i_club_id = req.getParameter("club_ID");

            int clubID = -1;

            try {
                clubID = Integer.parseInt(i_club_id);
            } catch (NumberFormatException e) {
                req.setAttribute("message", "Error: Please insert a number!");
                req.getRequestDispatcher("Message.jsp").forward(req, res);
            }

            User newLeader = getUserByMail(i_leader_mail);
            Club changedClub = getClubByID(clubID);

            if(changedClub==null){
                req.setAttribute("message", "Error: Club doesn't exist!");
                req.getRequestDispatcher("Message.jsp").forward(req, res);
            } else if (newLeader != null) {
                if(getManagedClubByUserID(newLeader.getUser_Id())!=null){
                    req.setAttribute("message", "Error: User already leads a club!");
                    req.getRequestDispatcher("Message.jsp").forward(req, res);
                }
                else {
                    changeClubPresident(changedClub,newLeader);
                    req.setAttribute("message", "Leader changed!");
                    req.getRequestDispatcher("Message.jsp").forward(req, res);
                }
            } else {
                req.setAttribute("message", "Error: Leader not found");
                req.getRequestDispatcher("Message.jsp").forward(req, res);
            }

        } else if (form.equals("removingClub")) {
            String i_club_id = req.getParameter("club_ID");

            int clubID = -1;

            try {
                clubID = Integer.parseInt(i_club_id);
            } catch (NumberFormatException e) {
                req.setAttribute("message", "Error: Please insert a number!");
                req.getRequestDispatcher("Message.jsp").forward(req, res);
            }

            Club oldClub = getClubByID(clubID);

            if(oldClub==null){
                req.setAttribute("message", "Error: Club doesn't exist!");
                req.getRequestDispatcher("Message.jsp").forward(req, res);

            }  else {
                removeClub(oldClub);
                req.setAttribute("message", "Club removed");
                req.getRequestDispatcher("Message.jsp").forward(req, res);
            }

        // no formular activated
        } else {
            req.setAttribute("message", "Error: Unknown issue");
            req.getRequestDispatcher("Message.jsp").forward(req, res);
        }

    }
}