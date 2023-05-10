package Servlets;

import static Servlets.LoginServlet.session;
import static Management.RequestManagement.getRequestsBySenderId;
import static Management.RequestManagement.getRequestsByRecipientId;
import static Management.RequestManagement.acceptRequest;
import static Management.RequestManagement.denyRequest;
import static Management.RequestManagement.deleteRequest;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import BusinessObjects.Request;

public class ManageRequestServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		int check = -1;

		for (Request request : (List<Request>) getRequestsBySenderId((int) session.getAttribute("userId"))) {
			if (request.getRequest_ID() == Integer.parseInt((String) req.getParameter("request"))) {
				check = 0;
			}
		}

		for (Request request : (List<Request>) getRequestsByRecipientId((int) session.getAttribute("userId"))) {
			if (request.getRequest_ID() == Integer.parseInt((String) req.getParameter("request"))) {
				check = 1;
			}
		}

		if (check == -1) {
			req.setAttribute("message", "I'm sorry, but I have to inform you that this is prohibited!");
			req.getRequestDispatcher("Message.jsp").forward(req, res);
		}

		else if (Integer.parseInt((String) req.getParameter("action")) == 1) {
			if (check == 1) {
				acceptRequest(Integer.parseInt((String) req.getParameter("request")));
				req.setAttribute("message", "Request was successfully accepted.");
				req.getRequestDispatcher("Message.jsp").forward(req, res);
			} else {
				req.setAttribute("message", "You can only use this action for received requests!");
				req.getRequestDispatcher("Message.jsp").forward(req, res);
			}

		}
		else if (Integer.parseInt((String) req.getParameter("action")) == 2) {
			if (check == 1) {
				denyRequest(Integer.parseInt((String) req.getParameter("request")));
				req.setAttribute("message", "Request was successfully denied.");
				req.getRequestDispatcher("Message.jsp").forward(req, res);
			} else {
				req.setAttribute("message", "You can only use this action for received requests!");
				req.getRequestDispatcher("Message.jsp").forward(req, res);
			}

		}
		else if (Integer.parseInt((String) req.getParameter("action")) == 3) {
			if (check == 0) {
				deleteRequest(Integer.parseInt((String) req.getParameter("request")));
				req.setAttribute("message", "Request was successfully deleted.");
				req.getRequestDispatcher("Message.jsp").forward(req, res);
			} else {
				req.setAttribute("message", "You can only use this action for sent requests!");
				req.getRequestDispatcher("Message.jsp").forward(req, res);
			}
		}
	}
}