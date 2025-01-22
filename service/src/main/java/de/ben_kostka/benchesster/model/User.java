package de.ben_kostka.benchesster.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
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
 * User
 */
@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int user_ID;

  @Column
  private String username;

  @Column
  private String firstName;

  @Column
  private String lastName;

  @Column
  private String email;

  @Column
  private String password;

  @Column
  private String phone;

  @Column
  private Integer userStatus;

  @ManyToMany
  @JoinTable(
          name = "user_has_teams", // Name der Zwischentabelle
          joinColumns = @JoinColumn(name = "user_ID"), // Spalte, die auf die Student-Entität verweist
          inverseJoinColumns = @JoinColumn(name = "team_ID") // Spalte, die auf die Course-Entität verweist
  )
  private Set<Team> courses = new HashSet<>();
}

