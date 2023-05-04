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
		String email = req.getParameter("email");
		String password = encryptString(req.getParameter("password"));
		String Lastname = req.getParameter("lastname");
		String Firstname = req.getParameter("firstname");
		String dateString = Calendar.getInstance().getTime().toString();
		List<Team> teams = new ArrayList<>();

		boolean registrationSucceed = saveNewUser(new User(Lastname,Firstname,email,password,dateString,teams));

		if(registrationSucceed){
			req.getRequestDispatcher("Login.jsp").forward(req, res);
		}
		else{
			req.setAttribute("message", "Error: Registration failed. Please contact Lukas!");
			req.getRequestDispatcher("Message.jsp").forward(req, res);
		}
	}

}
