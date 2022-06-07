package de.htwberlin.notizzettelwebanwendung.notizzettelwebanwendung.web;

import de.htwberlin.notizzettelwebanwendung.notizzettelwebanwendung.persistence.PersonEntity;
import de.htwberlin.notizzettelwebanwendung.notizzettelwebanwendung.service.PersonService;
import de.htwberlin.notizzettelwebanwendung.notizzettelwebanwendung.web.api.Person;
import de.htwberlin.notizzettelwebanwendung.notizzettelwebanwendung.web.api.PersonManipulationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class PersonRestController {

    private final PersonService personService;

    public PersonRestController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping(path = "/api/persons")
    public ResponseEntity<List<Person>> fetchPersons() {
        return ResponseEntity.ok(personService.findAll());
    }

    @GetMapping(path = "/api/persons/{id}")
    public ResponseEntity<Person> fetchPersonById(@PathVariable Long id) {
        var person = personService.findById(id);
        return person != null? ResponseEntity.ok(person) : ResponseEntity.notFound().build();
    }

    @GetMapping(path = "/api/persons", params = "firstName")
    public ResponseEntity<List<PersonEntity>> getPersonByFirstName(@RequestParam String firstName){
        return ResponseEntity.ok(personService.findByFirstName(firstName));
    }

    @PostMapping(path = "/api/persons")
    public ResponseEntity<Void> createPerson(@RequestBody PersonManipulationRequest request) throws URISyntaxException {
        var person = personService.create(request);
        URI uri = new URI("/api/persons/" + person.getId());
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(path = "/api/persons/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Long id, @RequestBody PersonManipulationRequest request) {
        var person = personService.update(id, request);
        return person != null? ResponseEntity.ok(person) : ResponseEntity.notFound().build();
    }

    @DeleteMapping(path = "/api/persons/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        boolean successful = personService.deleteById(id);
        return successful? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
