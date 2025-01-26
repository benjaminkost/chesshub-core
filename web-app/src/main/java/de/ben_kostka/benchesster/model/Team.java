package de.ben_kostka.benchesster.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import de.ben_kostka.benchesster.model.Club;
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
 * Team
 */
@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table

public class Team {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int team_ID;

  @Column(unique = true)
  private String name;

  @ManyToOne
  private Club club;

  @OneToOne
  private User leader;
}

