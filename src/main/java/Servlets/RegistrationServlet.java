package Servlets;

import BusinessObjects.Authorisation;
import BusinessObjects.Club;
import BusinessObjects.Team;
import BusinessObjects.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static login.StringEncrypter.encryptString;
import static login.UserManagement.loginUser;
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
		// Benutzerdaten aus der Anforderung auslesen
		String email = req.getParameter("email");
		String password = encryptString(req.getParameter("password"));
		int user_Id = 99;
		String Lastname = req.getParameter("lastname");
		String Firstname = req.getParameter("firstname");
		String dateString = Calendar.getInstance().getTime().toString();
		Club club = null;
		Authorisation auth = null;
		List<Team> teams = new ArrayList<>();

		saveNewUser(new User(99,Lastname,Firstname,email,password,dateString,club,auth,teams));


		// Hier könntest du den Code schreiben, um die Benutzerdaten zu verarbeiten
		// und den Benutzer in der Datenbank zu registrieren

		// Eine Antwort an den Benutzer senden
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Registrierungsbestätigung</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>Vielen Dank für die Registrierung!</h1>");
		out.println("<p>Deine E-Mail-Adresse lautet: " + email + "</p>");
		out.println("</body>");
		out.println("</html>");
	}


}
