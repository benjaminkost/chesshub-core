package BusinessObjects;

public class Club {

	private int club_ID;
	private String name;
	
	public Club(int club_ID, String name) {
		super();
		this.club_ID = club_ID;
		this.name = name;
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
	
	
}
