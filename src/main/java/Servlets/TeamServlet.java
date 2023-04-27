package Servlets;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

import static Management.UserManagement.*;

public class TeamServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    protected static HttpSession session;

    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        req.getRequestDispatcher("Team.jsp").forward(req, res);
    }
}