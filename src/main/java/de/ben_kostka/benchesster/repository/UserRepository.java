package de.ben_kostka.benchesster.repository;

import de.ben_kostka.benchesster.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
