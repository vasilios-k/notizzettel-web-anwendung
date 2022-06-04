package de.htwberlin.notizzettelwebanwendung.notizzettelwebanwendung.web;

import de.htwberlin.notizzettelwebanwendung.notizzettelwebanwendung.service.NotesService;
import de.htwberlin.notizzettelwebanwendung.notizzettelwebanwendung.web.api.Notes;
import de.htwberlin.notizzettelwebanwendung.notizzettelwebanwendung.web.api.NotesManipulationRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class NotesRestController {

    private final NotesService notesService;

    public NotesRestController(NotesService notesService) {
        this.notesService = notesService;
    }

    @GetMapping(path = "/api/notes")
    public ResponseEntity<List<Notes>> fetchNotes() {
        return ResponseEntity.ok(notesService.findAll());
    }

    @GetMapping(path = "/api/notes/{id}")
    public ResponseEntity<Notes> fetchNotesById(@PathVariable Long id) {
        var notes = notesService.findById(id);
        return notes != null? ResponseEntity.ok(notes) : ResponseEntity.notFound().build();
    }

    @PostMapping(path = "/api/notes")
    public ResponseEntity<Void> createNotes(@RequestBody NotesManipulationRequest request) throws URISyntaxException {
        var notes = notesService.create(request);
        URI uri = new URI("/api/notes/" + notes.getId());
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(path = "/api/notes/{id}")
    public ResponseEntity<Notes> updateNotes(@PathVariable Long id, @RequestBody NotesManipulationRequest request) {
        var notes = notesService.update(id, request);
        return notes != null? ResponseEntity.ok(notes) : ResponseEntity.notFound().build();
    }

    @DeleteMapping(path = "/api/notes/{id}")
    public ResponseEntity<Void> deleteNotes(@PathVariable Long id) {
        boolean successful = notesService.deleteById(id);
        return successful? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
