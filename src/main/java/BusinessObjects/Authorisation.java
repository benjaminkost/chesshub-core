package BusinessObjects;

public class Authorisation {
	
	private int auth_ID;
	private String role;
	private User president;
	

	public Authorisation(int auth_ID, String role, User president) {
		super();
		this.auth_ID = auth_ID;
		this.role = role;
		this.president = president;
	}
	
	public Authorisation() {
		super();
	}
	public int getAuth_ID() {
		return auth_ID;
	}
	public void setAuth_ID(int auth_ID) {
		this.auth_ID = auth_ID;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

	public User getPresident() {
		return president;
	}

	public void setPresident(User president) {
		this.president = president;
	}
	
	
	

}
