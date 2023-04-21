package BusinessObjects;

import java.util.ArrayList;
import java.util.List;

public class User{

	private int user_Id;
	private String Lastname;
	private String Firstname;
	private String Email;
	private String password;
	private String dateString;
	private Club club;
	private Authorisation auth;
	private List<Team> teams = new ArrayList<>();

	public User( int user_Id, String lastname, String firstname, String email, String password,
			String dateString, Club club, Authorisation authorisations, List<Team> teams) {
		super();
		this.user_Id = user_Id;
		Lastname = lastname;
		Firstname = firstname;
		Email = email;
		this.password = password;
		this.dateString = dateString;
		this.club = club;
		this.auth = authorisations;
		this.teams = teams;
	}

	public User(String lastname, String firstname, String email, String password,
				 String dateString, Club club, Authorisation authorisations, List<Team> teams) {
		super();
		Lastname = lastname;
		Firstname = firstname;
		Email = email;
		this.password = password;
		this.dateString = dateString;
		this.club = club;
		this.auth = authorisations;
		this.teams = teams;
	}



	public User() {
		super();
	}

	public Club getClub() {
		return this.club;
	}

	public void setClub(Club club) {
		this.club = club;
	}

	public String getLastname() {
		return this.Lastname;
	}

	public void setLastname(String lastname) {
		Lastname = lastname;
	}

	public String getFirstname() {
		return this.Firstname;
	}

	public void setFirstname(String firstname) {
		Firstname = firstname;
	}

	public String getEmail() {
		return this.Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDateString() {
		return this.dateString;
	}

	public void setDateString(String dateString) {
		this.dateString = dateString;
	}
/*
	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}*/

	public int getUser_Id() {
		return this.user_Id;
	}

	public void setUser_Id(int user_Id) {
		this.user_Id = user_Id;
	}

	public Authorisation getAuthorisation() {
		return this.auth;
	}

	public void setAuthorisation(Authorisation authorisations) {
		this.auth = authorisations;
	}

	public List<Team> getTeams() {
		return this.teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}

	public void addTeam(Team newTeam){
		this.teams.add(newTeam);
	}

	public void removeTeam(Team oldTeam){
		this.teams.remove(oldTeam);
	}
}	

