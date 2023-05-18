package Management;

import java.util.ArrayList;
import java.util.List;

import BusinessObjects.Game;
import BusinessObjects.Request;
import DAO.GameDao;
import DAO.RequestDao;
import DAO.UserDao;

public class RequestManagement {

	public static boolean sendRequest(int sender_ID, int recipient_ID, int game_ID) {
		String status = "I think I was your opponent, could you please confirm.";
		return RequestDao.getInstance().insertRequest(UserDao.getInstance().getUserById(sender_ID),
				UserDao.getInstance().getUserById(recipient_ID), GameDao.getInstance().getGameById(game_ID), status);
	}

	public static boolean acceptRequest(int request_id) {
		String status = "Your request has been confirmed.";
		Game game = GameDao.getInstance()
				.getGameById(RequestDao.getInstance().getRequestById(request_id).getGame().getGame_ID());
		game.setOpponent(RequestDao.getInstance().getRequestById(request_id).getSender().getUser_Id());
		game.setComment("");
		GameDao.getInstance().updateGame(game);
		return RequestDao.getInstance().updateRequest(request_id, status);
	}

	public static boolean denyRequest(int request_id) {
		String status = "Your request has been declined.";
		return RequestDao.getInstance().updateRequest(request_id, status);
	}

	public static boolean deleteRequest(int request_id) {
		return RequestDao.getInstance().deleteRequest(request_id);
	}

	public static List<Request> getRequestsBySenderId(int userId) {
		return RequestDao.getInstance().getRequestsBySenderId(UserDao.getInstance().getUserById(userId));
	}

	public static List<Request> getRequestsByRecipientId(int userId) {
		return RequestDao.getInstance().getRequestsByRecipientId(UserDao.getInstance().getUserById(userId));
	}

	public static List<String> getSenderRequestsForJSP(int senderId) {
		List<Request> senderRequests = RequestDao.getInstance()
				.getRequestsBySenderId(UserDao.getInstance().getUserById(senderId));
		List<String> requests = new ArrayList();
		if (senderRequests.isEmpty()) {
			requests.add("You currently have no such requests! <br>");
			return requests;
		}
		for (Request req : senderRequests) {
			requests.add("<input type='radio' name='request' value='" + req.getRequest_ID() + "' required> Affected: "
					+ req.getRecipient().getFullName() + " (Game-ID " + req.getGame().getGame_ID() + ") | Status: "
					+ req.getStatus() + "<br>");
		}
		return requests;
	}

	public static List<String> getRecipientRequestsForJSP(int recipientId) {
		List<Request> recipientRequests = RequestDao.getInstance()
				.getRequestsByRecipientId(UserDao.getInstance().getUserById(recipientId));
		List<String> requests = new ArrayList();
		if (recipientRequests.isEmpty()) {
			requests.add("You currently have no such requests! <br>");
			return requests;
		}
		for (Request req : recipientRequests) {
			requests.add("<input type='radio' name='request' value='" + req.getRequest_ID() + "' required> From: "
					+ req.getSender().getFullName() + "<a href='./GameByGameIdServlet?gameId="
					+ req.getGame().getGame_ID() + "'> (Game-ID " + req.getGame().getGame_ID() + ")</a> | Status: "
					+ req.getStatus() + "<br>");
		}
		return requests;
	}
}
