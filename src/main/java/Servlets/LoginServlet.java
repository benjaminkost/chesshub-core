package Servlets;

import BusinessObjects.*;
import dao.*;
import login.UserManagement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

import static login.UserManagement.loggedInUser;
import static login.UserManagement.loginUser;

public class LoginServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		PrintWriter out = res.getWriter();
		res.setContentType("text/html");
		out.println("<html><body><form action=\"LoginServlet\" method=\"post\">");

		out.println("<div class=\"container\">\n" +
				"        <label for=\"mail\"><b>Mail</b></label>\n" +
				"        <input type=\"text\" placeholder=\"Enter your mail address\" name=\"mail\" required>\n" +
				"        <br>\n" +
				"        <label for=\"psw\"><b>Password</b></label>\n" +
				"        <input type=\"password\" placeholder=\"Enter Password\" name=\"psw\" required>\n" +
				"        <br>\n" +
				"        <button type=\"submit\">Login</button>\n" +
				"    </div>" +
				"<div class=\"container\" style=\"background-color:#f1f1f1\">\n" +
				"        <span class=\"psw\"> <a href=\"#\">Forgot password?</a></span>\n" +
				"    </div>");

		out.println("</form></html></body>");

	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		// Hier wird der Java-Code ausgeführt, nachdem der Button gedrückt wurde

		String i_mail = req.getParameter("mail");
		String i_password = req.getParameter("psw");

		boolean login = loginUser(i_mail,i_password);

		PrintWriter out = res.getWriter();
		res.setContentType("text/html");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Login</title>");
		out.println("</head>");
		out.println("<body>");
		if(login){
			String Username = loggedInUser.getFirstname();
			out.println("<h1>Log in successfully! Welcome "+Username+"</h1>");
		}
		else {
			out.println("<h1>Access denied, ask Lukas for help</h1>");
		}

		out.println("</body>");
		out.println("</html>");
	}


}
