package BusinessObjects;

import java.util.Date;

public class Game {

	private int game_ID;
	private String event;
	private String site;
	private Date date;
	private int round;
	private User white;
	private User black;
	private String Result; 
	private String moves;
	private String comment;
	private String commentWhite;
	private String commentBlack;
	
	public Game(int game_ID, String event, String site, Date date, int round, User whitie, User black, String result,
			String moves, String comment) {
		super();
		this.game_ID = game_ID;
		this.event = event;
		this.site = site;
		this.date = date;
		this.round = round;
		this.white = whitie;
		this.black = black;
		Result = result;
		this.moves = moves;
		this.comment = comment;
	}

	public Game() {
		super();
	}

	public int getGame_ID() {
		return game_ID;
	}

	public void setGame_ID(int game_ID) {
		this.game_ID = game_ID;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}

	public User getWhite() {
		return white;
	}

	public void setWhite(User whitie) {
		this.white = whitie;
	}

	public User getBlack() {
		return black;
	}

	public void setBlack(User black) {
		this.black = black;
	}

	public String getResult() {
		return Result;
	}

	public void setResult(String result) {
		Result = result;
	}

	public String getMoves() {
		return moves;
	}

	public void setMoves(String moves) {
		this.moves = moves;
	}
	
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public String getCommentWhite() {
		return commentWhite;
	}

	public void setCommentWhite(String commentWhite) {
		this.commentWhite = commentWhite;
	}
	
	public String getCommentBlack() {
		return commentBlack;
	}

	public void setCommentBlack(String commentBlack) {
		this.commentBlack = commentBlack;
	}

	@Override
	public String toString() {
		return "Game{" +
				"\ngame_ID=" + game_ID +
				"\n, event='" + event + '\'' +
				"\n, site='" + site + '\'' +
				"\n, date=" + date +
				"\n, round=" + round +
				"\n, white=" + white +
				"\n, black=" + black +
				"\n, Result='" + Result + '\'' +
				"\n, moves='" + moves + '\'' +
				"\n, comment='" + comment + '\'' +
				'}';
	}
	
	public String getPlayer(int userID) {
		if (userID==this.white.getUser_Id()) return this.white.getFullName() + " (WHITE)"; 
		else return this.black.getFullName() + " (BLACK)";
	}
	
	public String getOpponent(int userID) {
		if (userID!=this.white.getUser_Id()) {
			if (this.white.getUser_Id()!=0) return this.white.getFullName() + " (WHITE)"; 
			else return this.comment + " (WHITE)";
		}
		else {
			if (this.black.getUser_Id()!=0) return this.black.getFullName() + " (BLACK)";
			else return this.comment + " (BLACK)";
		}
	}
	
	public String getGame() {
		if (white.getUser_Id()==0) return "[Result \"" + Result + "\"] [Date \"" + date + "\"] [Round \"" + round + "\"] [Event \"" + event + "\"] [Black \"" + black.getFullName() + "\"] [White \"" + comment + "\"] [Site \"" + site + "\"]" + moves;
		if (black.getUser_Id()==0) return "[Result \"" + Result + "\"] [Date \"" + date + "\"] [Round \"" + round + "\"] [Event \"" + event + "\"] [Black \"" + comment + "\"] [White \"" + white.getFullName() + "\"] [Site \"" + site + "\"]" + moves;
		return "[Result \"" + Result + "\"] [Date \"" + date + "\"] [Round \"" + round + "\"] [Event \"" + event + "\"] [Black \"" + black.getFullName() + "\"] [White \"" + white.getFullName() + "\"] [Site \"" + site + "\"]" + moves;
	}

	public String readPGN() {
//		TODO BEN?
		return Result;
		
	}
	
	public void analyzePGN() {
//		TODO BEN?
	}
	
}
