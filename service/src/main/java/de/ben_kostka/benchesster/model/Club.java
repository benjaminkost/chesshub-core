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
public class Club {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int club_ID;

	@Column
	private String name;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_ID")
	private User president;

}