package org.example.pruebaspringboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RestController
@RequestMapping("/holamundo")
class MainController {

    @GetMapping("/llamada")
    public List<String> index() {
        return List.of("Hello cruel world.","Hola mundo cruel.");
    }
}
