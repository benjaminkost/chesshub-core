package de.ben_kostka.benchesster.repository;

import de.ben_kostka.benchesster.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Integer> {
}
