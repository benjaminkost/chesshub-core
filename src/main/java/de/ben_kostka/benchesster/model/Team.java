package de.ben_kostka.benchesster.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table
public class Team {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int team_ID;
	@Column
	private String name;
	@ManyToOne
	@PrimaryKeyJoinColumn
	private Club club;
	@ManyToOne
	@PrimaryKeyJoinColumn
	private User leader;

}
