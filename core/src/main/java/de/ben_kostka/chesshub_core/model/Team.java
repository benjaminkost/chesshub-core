package de.ben_kostka.chesshub_core.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * Team
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = {"teamMemberships", "club"})
@Entity
@Table

public class Team {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @EqualsAndHashCode.Include
  private Long id;

  @Column(unique = true)
  private String name;

  @ManyToOne
  private Club club;

  @OneToOne
  private User leader;

  @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
  @Builder.Default
  private java.util.Set<TeamMembership> teamMemberships = new java.util.HashSet<>();
}

