package Servlets;

import static Servlets.LoginServlet.session;
import static Management.RequestManagement.getSenderRequestsForJSP;
import static Management.RequestManagement.getRecipientRequestsForJSP;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setAttribute("senderRequests", getSenderRequestsForJSP((int) session.getAttribute("userId")));
		req.setAttribute("recipientRequests", getRecipientRequestsForJSP((int) session.getAttribute("userId")));
		req.getRequestDispatcher("Requests.jsp").forward(req, res);
	}

}