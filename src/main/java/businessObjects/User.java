package businessObjects;

import java.util.*;

public class User{
	private int user_Id;
	private String lastname;
	private String firstname;
	private String email;
	private String password;
	private List<Team> teams = new ArrayList<>();

	public User( int user_Id, String lastname, String firstname, String email, String password, List<Team> teams) {
		super();
		this.user_Id = user_Id;
		this.lastname = lastname;
		this.firstname = firstname;
		this.email = email;
		this.password = password;
		this.teams = teams;
	}

	public User(String lastname, String firstname, String email, String password, List<Team> teams) {
		super();
		this.lastname = lastname;
		this.firstname = firstname;
		this.email = email;
		this.password = password;
		this.teams = teams;
	}

	public User() {
		super();
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public String getFullName() {
		return this.firstname + " " + this.lastname;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getUser_Id() {
		return this.user_Id;
	}

	public void setUser_Id(int user_Id) {
		this.user_Id = user_Id;
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

	/***
	 * Needed for checking if user is part of list<user>
	 *
	 * @param obj - object, which should be compared
	 * @return boolean - true, if objects are the same, identified by ID
	 *
	 * @author Lukas Zander
	 */
	@Override
	public boolean equals(Object obj) {
		//If the object is an instance of the User class, we can check for equality
		if(obj.getClass() == this.getClass()) {
			User u = (User) obj;
			return this.user_Id == u.user_Id;
		}
		//otherwise, this is definitely not the same object, so return false
		return false;
	}
}	

