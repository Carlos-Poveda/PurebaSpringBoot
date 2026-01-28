package org.example.pruebaspringboot;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
class WebController {

    GameRepository gameRepository;
    public WebController(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("videojuegos", gameRepository.findAll());
        model.addAttribute("plataformas", gameRepository.findDistinctPlatforms());
        return "index";
    }

    @GetMapping("/juego/{id}")
    public String juego(@PathVariable Integer id, Model model) {
        if(gameRepository.findById(id).isPresent()) {
            model.addAttribute("game", gameRepository.findById(id).get());
            model.addAttribute("plataformas", gameRepository.findDistinctPlatforms());
            return "juego";
        } else {
            model.addAttribute("error","No existe el juego "+id);
            return "error";
        }
    }

    @GetMapping("/plataforma/{platform}")
    public String plataformGames(@PathVariable String platform, Model model) {
        String platformName = platform.replace("-", " ");
        List<Game> games = gameRepository.findByPlatform(platformName);
        model.addAttribute("videojuegos", games);
        model.addAttribute("plataforma", platformName);
        model.addAttribute("plataformas", gameRepository.findDistinctPlatforms());
        return "plataforma";
    }

}
