package de.htwberlin.notizzettelwebanwendung.notizzettelwebanwendung.service;

import de.htwberlin.notizzettelwebanwendung.notizzettelwebanwendung.persistence.NotesEntity;
import de.htwberlin.notizzettelwebanwendung.notizzettelwebanwendung.persistence.PersonEntity;
import de.htwberlin.notizzettelwebanwendung.notizzettelwebanwendung.web.api.Person;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class PersonTransformer {

    public Person transformEntity(PersonEntity personEntity) {
        var notesIds = personEntity.getNotes().stream().map(NotesEntity::getId).collect(Collectors.toList());
        return new Person(
                personEntity.getId(),
                personEntity.getFirstName(),
                personEntity.getLastName(),
                notesIds);
    }
}
