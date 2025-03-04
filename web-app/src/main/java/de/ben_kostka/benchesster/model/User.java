package de.ben_kostka.benchesster.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


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

  @ManyToMany
  @JoinTable(
          name = "user_has_teams", // Name der Zwischentabelle
          joinColumns = @JoinColumn(name = "user_ID"), // Spalte, die auf die User-Entität verweist
          inverseJoinColumns = @JoinColumn(name = "team_ID") // Spalte, die auf die Teams-Entität verweist
  )
  private Set<Team> teams = new HashSet<>();
}

