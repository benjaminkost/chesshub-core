package de.ben_kostka.chesshub_core.repository;

import de.ben_kostka.chesshub_core.model.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubRepository extends JpaRepository<Club, Long> {
}
