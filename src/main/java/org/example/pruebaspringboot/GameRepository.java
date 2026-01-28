package org.example.pruebaspringboot;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface GameRepository extends JpaRepository<Game, Integer> {
    List<Game> findByPlatform(String platform);

}
