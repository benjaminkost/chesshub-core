package de.ben_kostka.benchesster.repository;

import de.ben_kostka.benchesster.model.Club;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubRepository extends JpaRepository<Club, Integer> {
}
