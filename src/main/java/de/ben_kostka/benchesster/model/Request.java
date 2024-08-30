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
public class Request {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int request_ID;
	@ManyToOne
	@PrimaryKeyJoinColumn
	private User sender;
	@ManyToOne
	@PrimaryKeyJoinColumn
	private User recipient;
	@OneToOne
	@PrimaryKeyJoinColumn
	private Game game;
	private String status;

}
