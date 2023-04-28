package BusinessObjects;

import java.util.List;

public class Club {

	private int club_ID;
	private String name;
	private User president;
	//private List<Team> teams;


	public Club(String name, User president) {
		super();
		//this.club_ID = club_ID;
		this.name = name;
		this.president = president;
		//this.teams = teams;
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

	/*
	@deprecated by Lukas
	Its trash

	public void addTeam(Team newTeam){
		this.teams.add(newTeam);
		newTeam.setClub(this);
	}

	public void removeTeam(Team oldTeam){
		this.teams.remove(oldTeam);
		oldTeam.setClub(null);
	}

	public List<Team> getTeams(){
		return this.teams;
	}

	public void setTeams(List<Team> teams){
		this.teams=teams;
	}

	 */

}
