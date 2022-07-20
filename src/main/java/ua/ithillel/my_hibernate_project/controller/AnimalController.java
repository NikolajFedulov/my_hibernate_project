package ua.ithillel.my_hibernate_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ua.ithillel.my_hibernate_project.model.Animal;
import ua.ithillel.my_hibernate_project.service.AnimalService;

@RestController
@RequestMapping("/animal")
public class AnimalController {

    private final AnimalService animalService;

    @Autowired
    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping("/{id}")
    public Animal getAnimal (@PathVariable Long id) {
        return animalService.getAnimal(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT));
    }

    @PutMapping
    public Animal addAnimal (@RequestBody Animal animal) {
        return animalService.addAnimal(animal)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
    }

    @PostMapping
    public Animal updateAnimal (@RequestBody Animal animal) {
        return animalService.updateAnimal(animal)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
    }

    @DeleteMapping("/{id}")
    public Animal removeAnimal (@PathVariable Long id) {
        return animalService.removeAnimal(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT));
    }
}
