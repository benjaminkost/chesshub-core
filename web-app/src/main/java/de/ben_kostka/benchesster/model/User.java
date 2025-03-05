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
  private Long id;

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
          joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), // Spalte, die auf die User-Entität verweist
          inverseJoinColumns = @JoinColumn(name = "team_id", referencedColumnName = "id") // Spalte, die auf die Teams-Entität verweist
  )
  private Set<Team> teams = new HashSet<>();

  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinTable(name = "users_roles",
          joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
          inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
  )
  private Set<Role> roles;
}

