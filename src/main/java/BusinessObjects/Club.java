package BusinessObjects;

public class Club {

	private int club_ID;
	private String name;
	private User president;

	public Club(int club_ID, String name, User president) {
		super();
		this.club_ID = club_ID;
		this.name = name;
		this.president = president;
	}

	public Club() {
		super();
	}

	public int getClub_ID() {
		return club_ID;
	}

	public void setClub_ID(int club_ID) {
		this.club_ID = club_ID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getPresident() {
		return president;
	}

	public void setPresident(User president) {
		this.president = president;
	}

}
