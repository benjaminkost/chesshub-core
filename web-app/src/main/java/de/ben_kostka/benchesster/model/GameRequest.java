package de.ben_kostka.benchesster.model;

import de.ben_kostka.benchesster.enums.GameRequestStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Request
 */

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="game_request")
public class GameRequest {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
  @JoinColumn(name = "sender_id")
  private User sender;

  @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
  @JoinColumn(name = "recipient_id")
  private User recipient;

  @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
  @JoinColumn(name = "game_id", nullable = false)
  private Game game;

  @Column(columnDefinition = "varchar(255) DEFAULT NULL")
  private GameRequestStatus gameRequestStatus;
}

