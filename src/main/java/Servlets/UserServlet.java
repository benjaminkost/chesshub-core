package Servlets;

import BusinessObjects.Team;
import BusinessObjects.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static Management.TeamManagement.*;
import static Management.UserManagement.*;
import static Servlets.LoginServlet.session;

public class UserServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        User loggedInUser = getUserById((int) session.getAttribute("userId"));
        req.setAttribute("user", loggedInUser);
        req.getRequestDispatcher("EditProfile.jsp").forward(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int userID = (int) session.getAttribute("userId");

        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String mail = req.getParameter("mail");

        boolean updateSuccess = updateUser(userID,firstName,lastName,mail);

        if(updateSuccess){
            req.setAttribute("message", "Profile updated!");
            req.getRequestDispatcher("Message.jsp").forward(req, res);
        }
        else {
            req.setAttribute("message", "Error: Update failed");
            req.getRequestDispatcher("Message.jsp").forward(req, res);
        }
    }
}