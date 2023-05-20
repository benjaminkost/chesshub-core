package businessObjects;

public class Team {
	private int team_ID;
	private String name;
	private Club club;
	private User leader;
	
	public Team(String name, Club club, User leader) {
		super();
		this.name = name;
		this.club = club;
		this.leader = leader;
	}

	public Team() {
		super();
	}
	public int getTeam_ID() {
		return team_ID;
	}
	public void setTeam_ID(int team_ID) {
		this.team_ID = team_ID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Club getClub() {	return this.club;	}
	public void setClub(Club club) {
		this.club = club;
	}
	public User getLeader() {
		return leader;
	}
	public void setLeader(User leader) {
		this.leader = leader;
	}
}
