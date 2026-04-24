package de.ben_kostka.chesshub_core.repository;

import de.ben_kostka.chesshub_core.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
}
