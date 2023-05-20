package servlets;

import static servlets.LoginServlet.session;
import static management.GameManagement.gameToPGN;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GameToPGNServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		//Validate Result-Input
		String result = req.getParameter("result");

		if(!result.equals("1-0")&&!result.equals("0-1")&&!result.equals("1/2-1/2")){
			req.setAttribute("message", "Error: Please insert valid result (1-0, 0-1, 1/2-1/2)");
			req.getRequestDispatcher("Message.jsp").forward(req, res);
		}

		else {
			req.setAttribute("game",
					gameToPGN(req.getParameter("color"), (int) session.getAttribute("userId"), req.getParameter("result"),
							req.getParameter("date"), req.getParameter("round"), req.getParameter("event"),
							req.getParameter("site"), req.getParameter("opponent")).getGame());
			session.setAttribute("game",
					gameToPGN(req.getParameter("color"), (int) session.getAttribute("userId"), req.getParameter("result"),
							req.getParameter("date"), req.getParameter("round"), req.getParameter("event"),
							req.getParameter("site"), req.getParameter("opponent")));
			req.getRequestDispatcher("PGNDownload.jsp").forward(req, res);
		}
	}
}