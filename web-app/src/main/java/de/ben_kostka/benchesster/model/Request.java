package de.ben_kostka.benchesster.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import de.ben_kostka.benchesster.model.Game;
import de.ben_kostka.benchesster.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
/**
 * Request
 */

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table
public class Request {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int request_ID;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "sender_ID")
  private User sender;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "recipient_ID")
  private User recipient;

  @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
  @JoinColumn(name = "game_ID", nullable = false)
  private Game game;

  @Column
  private String status;
}

