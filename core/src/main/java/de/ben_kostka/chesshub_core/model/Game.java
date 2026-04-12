package de.ben_kostka.chesshub_core.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = {"requests"})
@Entity
@Table
public class Game {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	@EqualsAndHashCode.Include
	private Long id;
	@Column(columnDefinition = "varchar(45) DEFAULT NULL")
	private Date date;
	@Column(columnDefinition = "varchar(45) DEFAULT NULL")
	private int round;
	@Column(columnDefinition = "varchar(45) DEFAULT NULL")
	private String event;
	@Column(columnDefinition = "varchar(45) DEFAULT NULL")
	private String site;
	@Column(columnDefinition = "mediumtext DEFAULT NULL")
	private String moves;
	@Column(columnDefinition = "varchar(45) DEFAULT NULL")
	private String result;
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="white_user_id")
	private User white_user;
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="black_user_id")
	private User black_user;
	@Column
	private String black_player_name;
	@Column
	private String white_player_name;
	@Column(columnDefinition = "varchar(45) DEFAULT NULL")
	private String comment;
	@OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
	@Builder.Default
	private List<GameRequest> requests = new java.util.ArrayList<>();

	@Column
	private String opening;

	@Column
	private String board;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "team_id")
	private Team team;
}
