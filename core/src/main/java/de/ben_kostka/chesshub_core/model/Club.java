package de.ben_kostka.chesshub_core.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = {"clubMemberships", "president"})
@Entity
@Table
public class Club {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;

	@Column
	private String name;

	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name = "user_id")
	private User president;

	@Column
	private String address;

  @OneToMany(mappedBy = "club", cascade = CascadeType.ALL, orphanRemoval = true)
  @Builder.Default
  private java.util.Set<ClubMembership> clubMemberships = new java.util.HashSet<>();

}