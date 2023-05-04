package BusinessObjects;

public class Request {

	private int request_ID;
	private int sender_ID;
	private int recipient_ID;
	private int game_ID;
	private String status;

	public Request() {
		super();
	}

	public Request(int request_ID, int sender_ID, int recipient_ID, int game_ID, String status) {
		super();
		this.request_ID = request_ID;
		this.sender_ID = sender_ID;
		this.recipient_ID = recipient_ID;
		this.game_ID = game_ID;
		this.status = status;
	}

	public int getRequest_ID() {
		return request_ID;
	}

	public void setRequest_ID(int request_ID) {
		this.request_ID = request_ID;
	}

	public int getSender_ID() {
		return sender_ID;
	}

	public void setSender_ID(int sender_ID) {
		this.sender_ID = sender_ID;
	}

	public int getRecipient_ID() {
		return recipient_ID;
	}

	public void setRecipient_ID(int recipient_ID) {
		this.recipient_ID = recipient_ID;
	}

	public int getGame_ID() {
		return game_ID;
	}

	public void setGame_ID(int game_ID) {
		this.game_ID = game_ID;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
