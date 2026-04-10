package de.ben_kostka.chesshub_core.repository;

import de.ben_kostka.chesshub_core.model.GameRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRequestRepository extends JpaRepository<GameRequest, Long> {
}
