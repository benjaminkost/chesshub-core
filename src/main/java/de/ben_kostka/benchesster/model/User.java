package de.ben_kostka.benchesster.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table
public class User{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int user_Id;
	@Column
	private String lastname;
	@Column
	private String firstname;
	@Column
	private String email;
	@Column
	private String password;
	@ManyToMany
	@Column
	private List<Team> teams = new ArrayList<>();

	public User(String lastname, String firstname, String email, String password, List<Team> teams) {
		super();
		this.lastname = lastname;
		this.firstname = firstname;
		this.email = email;
		this.password = password;
		this.teams = teams;
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

