package de.ben_kostka.chesshub_core.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;

/**
 * User
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = {"teamMemberships", "clubMemberships"})
@Entity
@Table
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @EqualsAndHashCode.Include
  private Long id;

  @Column
  @EqualsAndHashCode.Include
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

  @Column(name = "fide_id")
  private String fideId;

  @Column(name = "lichess_username")
  private String lichessUsername;

  @Column(name = "chesscom_username")
  private String chesscomUsername;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  @Builder.Default
  private Set<TeamMembership> teamMemberships = new HashSet<>();

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  @Builder.Default
  private Set<ClubMembership> clubMemberships = new HashSet<>();

  @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
  @JoinTable(name = "users_roles",
          joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
          inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
  )
  @Builder.Default
  private Set<Role> roles = new HashSet<>();
}

