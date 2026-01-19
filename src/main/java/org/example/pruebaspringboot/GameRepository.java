package org.example.pruebaspringboot;

import org.springframework.data.jpa.repository.JpaRepository;

interface GameRepository extends JpaRepository<Game, Integer> {

}
