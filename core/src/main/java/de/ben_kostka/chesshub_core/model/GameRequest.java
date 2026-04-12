package de.ben_kostka.chesshub_core.model;

import de.ben_kostka.chesshub_core.enums.GameRequestStatus;
import jakarta.persistence.*;
import lombok.*;

/**
 * Request
 */

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = {"sender", "recipient", "game"})
@Entity
@Table(name="game_request")
public class GameRequest {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @EqualsAndHashCode.Include
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

