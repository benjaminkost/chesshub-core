package Servlets;

import static Servlets.LoginServlet.session;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BusinessObjects.Game;
import dao.GameDao;
import dao.UserDao;

public class GameToPGNServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		Game game = new Game();
		
		if (req.getParameter("color").equals("White")) {
			game.setWhite(UserDao.getInstance().getUserById((int)session.getAttribute("userId")));
			game.setBlack(UserDao.getInstance().getUserById((0)));
			req.setAttribute("game", "[Result \"" + req.getParameter("result") + "\"]\n[Date \"" + req.getParameter("date") + "\"]\n[Round \"" + req.getParameter("round") + "\"]\n[Event \"" + req.getParameter("event") + "\"]\n[Black \"" + req.getParameter("opponent") + "\"]\n[White \"" + UserDao.getInstance().getUserById((int) session.getAttribute("userId")).getFullName() + "\"]\n[Site \"" + req.getParameter("site") + "\"]\n");
		}
		else {
			game.setBlack(UserDao.getInstance().getUserById((int)session.getAttribute("userId")));
			game.setWhite(UserDao.getInstance().getUserById((0)));
			req.setAttribute("game", "[Result \"" + req.getParameter("result") + "\"]\n[Date \"" + req.getParameter("date") + "\"]\n[Round \"" + req.getParameter("round") + "\"]\n[Event \"" + req.getParameter("event") + "\"]\n[Black \"" + UserDao.getInstance().getUserById((int) session.getAttribute("userId")).getFullName() + "\"]\n[White \"" + req.getParameter("opponent") + "\"]\n[Site \"" + req.getParameter("site") + "\"]\n");
		}
		
		game.setResult(req.getParameter("result"));
		try {
			game.setDate(new SimpleDateFormat("yyyy.MM.dd").parse(req.getParameter("date")));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		game.setRound(Integer.parseInt(req.getParameter("round")));
		game.setEvent(req.getParameter("event"));
		game.setSite(req.getParameter("site"));
		game.setComment(req.getParameter("opponent"));
		
		session.setAttribute("game", game);
		req.getRequestDispatcher("PGNDownload.jsp").forward(req, res);
	}

}