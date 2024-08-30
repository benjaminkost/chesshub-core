package de.ben_kostka.benchesster.repository;

import de.ben_kostka.benchesster.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<Request, Integer> {
}
