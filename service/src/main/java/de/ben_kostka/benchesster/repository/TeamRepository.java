package de.ben_kostka.benchesster.repository;

import de.ben_kostka.benchesster.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Integer> {
}
