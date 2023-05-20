package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import static servlets.LoginServlet.session;

public class LogoutServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		session.invalidate();
		session = req.getSession();
		req.getRequestDispatcher("index.jsp").forward(req, res);

	}

}