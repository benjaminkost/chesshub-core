package Servlets;

import static Servlets.LoginServlet.session;
import static Management.RequestManagement.sendRequest;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SendRequestServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		sendRequest((int) session.getAttribute("userId"), Integer.parseInt(req.getParameter("recipientId")), Integer.parseInt(req.getParameter("gameId")));
	}
}