package de.ben_kostka.benchesster.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table
public class Game {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;
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
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
	@JoinColumn(name="white_user_id")
	private User white_user;
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
	@JoinColumn(name="black_user_id")
	private User black_user;
	@Column
	private String black_player_name;
	@Column
	private String white_player_name;
	@Column(columnDefinition = "varchar(45) DEFAULT NULL", nullable = true)
	private String comment;
	@OneToMany(mappedBy = "game", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	private List<GameRequest> requests;
}
