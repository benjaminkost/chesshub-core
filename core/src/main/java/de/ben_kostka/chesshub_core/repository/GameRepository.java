package de.ben_kostka.chesshub_core.repository;

import de.ben_kostka.chesshub_core.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    List<Game> findByTeam_Id(Long teamId);

    List<Game> findByTeam_Club_Id(Long clubId);

    @Query("SELECT g FROM Game g WHERE g.white_user.id = :userId OR g.black_user.id = :userId")
    List<Game> findByWhiteUserOrBlackUser(@Param("userId") Long userId);
}
