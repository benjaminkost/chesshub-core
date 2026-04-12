package de.ben_kostka.chesshub_core.repository;

import de.ben_kostka.chesshub_core.model.ClubMembership;
import de.ben_kostka.chesshub_core.model.ClubMembershipId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClubMembershipRepository extends JpaRepository<ClubMembership, ClubMembershipId> {
    List<ClubMembership> findByClubId(Long clubId);
    List<ClubMembership> findByUserId(Long userId);
}
