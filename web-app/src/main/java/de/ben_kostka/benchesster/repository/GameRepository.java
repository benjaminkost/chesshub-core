package de.ben_kostka.benchesster.repository;

import de.ben_kostka.benchesster.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
}
