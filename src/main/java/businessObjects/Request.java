package businessObjects;

public class Request {

	private int request_ID;
	private User sender;
	private User recipient;
	private Game game;
	private String status;

	public Request() {
		super();
	}

	public Request(int request_ID, User sender, User recipient, Game game, String status) {
		super();
		this.request_ID = request_ID;
		this.sender = sender;
		this.recipient = recipient;
		this.game = game;
		this.status = status;
	}

	public int getRequest_ID() {
		return request_ID;
	}

	public void setRequest_ID(int request_ID) {
		this.request_ID = request_ID;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getRecipient() {
		return recipient;
	}

	public void setRecipient(User recipient) {
		this.recipient = recipient;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
