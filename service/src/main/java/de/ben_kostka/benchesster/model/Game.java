package de.ben_kostka.benchesster.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table
public class Game {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int game_ID;
	@Column(columnDefinition = "varchar(45) DEFAULT NULL", nullable = true)
	private Date date;
	@Column(columnDefinition = "varchar(45) DEFAULT NULL", nullable = true)
	private String round;
	@Column(columnDefinition = "varchar(45) DEFAULT NULL", nullable = true)
	private String event;
	@Column(columnDefinition = "varchar(45) DEFAULT NULL", nullable = true)
	private String site;
	@Column(columnDefinition = "mediumtext DEFAULT NULL", nullable = true)
	private String moves;
	@Column(columnDefinition = "varchar(45) DEFAULT NULL", nullable = true)
	private String result;
	@ManyToOne
	@PrimaryKeyJoinColumn
	private User white;
	@ManyToOne
	@PrimaryKeyJoinColumn
	private User black;
	@Column(columnDefinition = "varchar(45) DEFAULT NULL", nullable = true)
	private String comment;
}
