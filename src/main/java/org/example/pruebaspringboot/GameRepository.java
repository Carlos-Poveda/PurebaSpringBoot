package org.example.pruebaspringboot;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

interface GameRepository extends JpaRepository<Game, Integer> {
    List<Game> findByPlatform(String platform);

    @Query("SELECT DISTINCT g.platform FROM Game g ORDER BY g.platform")
    List<String> findDistinctPlatforms();
}
