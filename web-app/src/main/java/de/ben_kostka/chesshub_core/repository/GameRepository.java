package de.ben_kostka.chesshub_core.repository;

import de.ben_kostka.chesshub_core.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
}
