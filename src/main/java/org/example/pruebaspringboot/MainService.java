package org.example.pruebaspringboot;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
class MainService {

    private final GameRepository gameRepository;

    public MainService (GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    // Método unificado para crear o actualizar un juego
    public void saveGame(Game gameData) {
        if (gameData.getId() != null) {
            // Es una actualización
            Optional<Game> existingGameOpt = gameRepository.findById(gameData.getId());
            if (existingGameOpt.isPresent()) {
                Game existingGame = existingGameOpt.get();
                existingGame.setTitle(gameData.getTitle());
                existingGame.setPlatform(gameData.getPlatform());
                existingGame.setYear(gameData.getYear());
                existingGame.setDescription(gameData.getDescription());
                gameRepository.save(existingGame);
            }
        } else {
            // Es un juego nuevo
            gameRepository.save(gameData);
        }
    }
}
