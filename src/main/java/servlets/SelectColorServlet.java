package servlets;

import static servlets.LoginServlet.session;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class SelectColorServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		session.setAttribute("color", req.getParameter("color"));
		req.getRequestDispatcher("PGNUpload.jsp").forward(req, res);
	}
}