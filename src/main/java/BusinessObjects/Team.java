package BusinessObjects;

import java.util.List;

public class Team {
	
	private int team_ID;
	private String name;
	private Club club;
	private User leader;
	private List<User> members;
	
	public Team(int team_ID, String name, Club club, User leader, List<User> members) {
		super();
		this.team_ID = team_ID;
		this.name = name;
		this.club = club;
		this.leader = leader;
		this.members=members;
		
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
}
