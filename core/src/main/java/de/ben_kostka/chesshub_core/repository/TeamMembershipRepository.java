package de.ben_kostka.chesshub_core.repository;

import de.ben_kostka.chesshub_core.model.TeamMembership;
import de.ben_kostka.chesshub_core.model.TeamMembershipId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamMembershipRepository extends JpaRepository<TeamMembership, TeamMembershipId> {
    List<TeamMembership> findByTeamId(Long teamId);
    List<TeamMembership> findByUserId(Long userId);
}
