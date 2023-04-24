package Servlets;

import BusinessObjects.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

import static login.StringEncrypter.encryptString;
import static login.UserManagement.saveNewUser;

public class RegistrationServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		PrintWriter out = res.getWriter();
		res.setContentType("text/html");
		out.println("<!DOCTYPE html>\n" +
				"<html>\n" +
				"<head>\n" +
				"\t<title>Registration</title>\n" +
				"</head>\n" +
				"<body>\n" +
				"\t<h1>Registration</h1>\n" +
				"\t<form action=\"RegistrationServlet\" method=\"post\">\n" +

				"\t\t<label for=\"firstname\">Firstname:</label>\n" +
				"\t\t<input type=\"firstname\" id=\"firstname\" name=\"firstname\" required><br>\n" +
				"\t\t\n" +

				"\t\t<label for=\"lastname\">Lastname:</label>\n" +
				"\t\t<input type=\"lastname\" id=\"lastname\" name=\"lastname\" required><br>\n" +
				"\t\t\n" +

				"\t\t<label for=\"email\">E-Mail-Address:</label>\n" +
				"\t\t<input type=\"email\" id=\"email\" name=\"email\" required><br>\n" +
				"\t\t\n" +

				"\t\t<label for=\"password\">Password:</label>\n" +
				"\t\t<input type=\"password\" id=\"password\" name=\"password\" required><br>\n" +
				"\t\t\n" +

				"\t\t<input type=\"submit\" value=\"Register\">\n" +
				"\t</form>\n" +
				"</body>\n" +
				"</html>\n");
		
	}

	public void doPost(HttpServletRequest req, HttpServletResponse response)
			throws ServletException, IOException {
		// Reading Users Input
		String email = req.getParameter("email");
		String password = encryptString(req.getParameter("password"));
		String Lastname = req.getParameter("lastname");
		String Firstname = req.getParameter("firstname");
		String dateString = Calendar.getInstance().getTime().toString();
		Authorisation auth = null;
		List<Team> teams = new ArrayList<>();

		boolean registrationSucceed = saveNewUser(new User(Lastname,Firstname,email,password,dateString, auth,teams));

		// Response for User
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Registration confirmation</title>");
		out.println("</head>");
		out.println("<body>");
		if(registrationSucceed){
			out.println("<h1>Registration finished!</h1>");
			out.println("<p>Your email address: " + email + "</p>");
		}
		else{
			out.println("<h1>Registration failed. Please contact Lukas!</h1>");
		}
		out.println("</body>");
		out.println("</html>");
	}

}
