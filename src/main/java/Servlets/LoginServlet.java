package Servlets;

import static Management.UserManagement.loginUser;
import static Management.UserManagement.getUserById;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	protected static HttpSession session;

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		// Hier wird der Java-Code ausgeführt, nachdem der Button gedrückt wurde

		String i_mail = req.getParameter("mail");
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
			req.setAttribute("welcome", "Log in successfully! Welcome "+ getUserById(login).getFullName());
			req.getRequestDispatcher("Menu.jsp").forward(req, res);
		}
		
	}

}
