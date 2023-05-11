package Servlets;

import BusinessObjects.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

import static Management.StringEncrypter.encryptString;
import static Management.UserManagement.saveNewUser;

public class RegistrationServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		// Reading Users Input
		String email = req.getParameter("email").toLowerCase();
		String password = encryptString(req.getParameter("password"));
		String Lastname = req.getParameter("lastname");
		String Firstname = req.getParameter("firstname");
		List<Team> teams = new ArrayList<>();

		boolean registrationSucceed = saveNewUser(new User(Lastname,Firstname,email,password,teams));

		if(registrationSucceed){
			req.setAttribute("messageBeforeLogin", "Your registration was successful!");
			req.getRequestDispatcher("MessageBeforeLogin.jsp").forward(req, res);
		}
		else{
			req.setAttribute("messageBeforeLogin", "Error: Registration failed! Account with this e-mail already exists!");
			req.getRequestDispatcher("MessageBeforeLogin.jsp").forward(req, res);
		}
	}

}
