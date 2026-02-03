package org.example.pruebaspringboot;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
class WebController {

    private final MainService mainService;
    private final GameRepository gameRepository;

    public WebController(GameRepository gameRepository, MainService mainService) {
        this.gameRepository = gameRepository;
        this.mainService = mainService;
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
            model.addAttribute("juego", gameRepository.findById(id).get());
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

    @PostMapping("/")
    public String guardar(@ModelAttribute Game game) {
        mainService.saveGame(game);
        return "redirect:/";
    }

    @GetMapping("/nuevo")
    public String nuevoJuego(Model model) {
        model.addAttribute("juego", new Game());
        model.addAttribute("plataformas", gameRepository.findDistinctPlatforms());
        return "editar";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        if(gameRepository.findById(id).isPresent()){
            model.addAttribute("juego", gameRepository.findById(id).get());
            model.addAttribute("plataformas", gameRepository.findDistinctPlatforms());
            return "editar";
        } else {
            model.addAttribute("error","No existe el juego "+id);
            return "error";
        }
    }
}
