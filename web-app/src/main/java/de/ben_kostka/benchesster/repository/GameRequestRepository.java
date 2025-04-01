package de.ben_kostka.benchesster.repository;

import de.ben_kostka.benchesster.model.GameRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRequestRepository extends JpaRepository<GameRequest, Long> {
}
