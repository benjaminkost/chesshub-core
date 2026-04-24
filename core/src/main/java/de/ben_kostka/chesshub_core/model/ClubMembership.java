package de.ben_kostka.chesshub_core.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = {"user", "club"})
@Entity
@DynamicUpdate
@Table(name = "club_membership")
public class ClubMembership {

    @EmbeddedId
    @EqualsAndHashCode.Include
    private ClubMembershipId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("clubId")
    @JoinColumn(name = "club_id")
    private Club club;

    @Column
    private String status;

    @Column
    private String roles;

}
