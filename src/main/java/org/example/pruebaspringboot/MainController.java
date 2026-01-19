package org.example.pruebaspringboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/holamundo")
class MainController {
    @Autowired
    private GameRepository gameRepository;

    // API end point
    @GetMapping("/llamada")
    public List<String> index() {
        return List.of("Hello cruel world.","Hola mundo cruel.");
    }

    @GetMapping("/buenaps")
    public String buenaps() {
        return "buenaps";
    }

    @GetMapping("/parametro/{nombre}/{apellido}")
    public String parametro(@PathVariable String nombre, @PathVariable String apellido) {
        return "Hola "+nombre + " "+ apellido;
    }

    @GetMapping("/saludarJson")
    public String saludarJson(@RequestBody Persona data) {
        System.out.println(data);
        return "que paza pisha";
    }

    @GetMapping("/saludo2")
    public String saludo2(@RequestParam String nombre) {
        return "Hola "+nombre;
    }

    @GetMapping("/games")
    public List<Game> findAll() {
        return gameRepository.findAll();
    }

    @GetMapping("games/{id}")
    public Game findById(@PathVariable int id) {
        return gameRepository.findById(id).get();
    }

    @PostMapping("games")
    public Game save(@RequestBody Game game) {
        return gameRepository.save(game);
    }
}
