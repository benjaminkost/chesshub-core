package BusinessObjects;

import java.util.List;

public class User{

	private int user_Id;
	private String Lastname;
	private String Firstname;
	private String Email;
	private String password;
	private String dateString;
	private List<Team> memberInTeams;
	private Club memberInClub;
	private List<Authorisation> auth;
	
		
	

	public User(int user_Id, String lastname, String firstname, String email, String password, String dateString,
			List<Team> memberInTeam, Club memberInClub, List<Authorisation> auth) {
		super();
		this.user_Id = user_Id;
		Lastname = lastname;
		Firstname = firstname;
		Email = email;
		this.password = password;
		this.dateString = dateString;
		this.memberInTeams = memberInTeam;
		this.memberInClub = memberInClub;
		this.auth = auth;
	}



	public User() {
		super();
	}



	public int getuser_Id() {
		return user_Id;
	}

	public void setuser_Id(int user_Id) {
		this.user_Id = user_Id;
	}

	public String getLastname() {
		return Lastname;
	}

	public void setLastname(String lastname) {
		Lastname = lastname;
	}

	public String getFirstname() {
		return Firstname;
	}

	public void setFirstname(String firstname) {
		Firstname = firstname;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDateString() {
		return dateString;
	}

	public void setDateString(String dateString) {
		this.dateString = dateString;
	}



	public int getUser_Id() {
		return user_Id;
	}



	public void setUser_Id(int user_Id) {
		this.user_Id = user_Id;
	}



	public List<Team> getMemberInTeams() {
		return memberInTeams;
	}



	public void setMemberInTeams(List<Team> memberInTeam) {
		this.memberInTeams = memberInTeam;
	}



	public Club getMemberInClub() {
		return memberInClub;
	}



	public void setMemberInClub(Club memberInClub) {
		this.memberInClub = memberInClub;
	}



	public List<Authorisation> getAuth() {
		return auth;
	}



	public void setAuth(List<Authorisation> auth) {
		this.auth = auth;
	}
	
	
}	

