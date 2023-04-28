package BusinessObjects;

import java.util.ArrayList;
import java.util.List;

public class Team {
	
	private int team_ID;
	private String name;
	private Club club;
	private User leader;
	private List<User> members = new ArrayList<>();
	
	public Team(String name, Club club, User leader) {
		super();
		//this.team_ID = team_ID;
		this.name = name;
		this.club = club;
		this.leader = leader;
		//this.members=members;
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
	public Club getClub() {
		return club;
	}
	public void setClub(Club club) {
		this.club = club;
	}
	public User getLeader() {
		return leader;
	}
	public void setLeader(User leader) {
		this.leader = leader;
	}

	/*
	@deprecated from Lukas, 28.04.2023
	Diese Methoden sollen nur über UserTeamDao genutzt werden, hier sind sie doppelt und funktionierne nicht zsm mit der DB wegen n-m-relation

	public List<User> getMembers() {
		return members;
	}

	public void setMembers(List<User> members) {
		this.members = members;
	}

	public void addMember(User newMember){
		this.members.add(newMember);
		newMember.addTeam(this);
	}

	public void removeMember(User oldMember){
		this.members.remove(oldMember);
		oldMember.removeTeam(this);
	}

	 */

}
