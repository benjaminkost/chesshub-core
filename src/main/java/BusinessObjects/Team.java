package BusinessObjects;

public class Team {
	
	private int team_ID;
	private String name;
	public Team(int team_ID, String name) {
		super();
		this.team_ID = team_ID;
		this.name = name;
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
	
	

}
