package ua.ithillel.my_hibernate_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ua.ithillel.my_hibernate_project.model.Person;
import ua.ithillel.my_hibernate_project.service.PersonService;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/{id}")
    public Person getPerson (@PathVariable Long id) {
        return personService.getPerson(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT));
    }

    @PutMapping
    public Person addPerson (@RequestBody Person person) {
        return personService.addPerson(person)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
    }

    @PostMapping
    public Person updatePerson (@RequestBody Person person) {
        return personService.updatePerson(person)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
    }

    @DeleteMapping("/{id}")
    public Person removePerson (@PathVariable Long id) {
        return personService.removePerson(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT));
    }
}
