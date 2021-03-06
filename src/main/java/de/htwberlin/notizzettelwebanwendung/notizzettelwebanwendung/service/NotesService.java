package de.htwberlin.notizzettelwebanwendung.notizzettelwebanwendung.service;

import de.htwberlin.notizzettelwebanwendung.notizzettelwebanwendung.persistence.*;
import de.htwberlin.notizzettelwebanwendung.notizzettelwebanwendung.web.api.Notes;
import de.htwberlin.notizzettelwebanwendung.notizzettelwebanwendung.web.api.NotesManipulationRequest;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotesService {
    private final NotesRepository notesRepository;

    private final PersonRepository personRepository;

    private final PersonTransformer personTransformer;

    public NotesService(NotesRepository notesRepository, PersonRepository personRepository, PersonTransformer personTransformer) {
        this.notesRepository = notesRepository;
        this.personRepository = personRepository;
        this.personTransformer = personTransformer;
    }

    public List<Notes> findAll() {
        List<NotesEntity> notes= notesRepository.findAll();
        return notes.stream()
                .map(this::transformEntity)
                .collect(Collectors.toList());
    }

    public Notes findById(Long id) {
        var notesEntity = notesRepository.findById(id);
        return notesEntity.map(this::transformEntity).orElse(null);
    }

    public Notes create(NotesManipulationRequest request) {
        var category = Category.valueOf(request.getCategory());
        var owner = personRepository.findById(request.getOwnerId()).orElseThrow();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ssZ");
        Date datenow = new Date();
        String date = formatter.format(datenow);
        var notesEntity = new NotesEntity(request.getHeadline(),request.getText(), category, owner, date);
        notesEntity = notesRepository.save(notesEntity);
        return transformEntity(notesEntity);
    }

    public Notes update(Long id, NotesManipulationRequest request) {
        var notesEntityOptional =notesRepository.findById(id);
        if (notesEntityOptional.isEmpty()) {
            return null;
        }var notesEntity = notesEntityOptional.get();
        notesEntity.setHeadline(request.getHeadline());
        notesEntity.setText(request.getText());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ssZ");
        Date datenow = new Date();
        String date = formatter.format(datenow);
        notesEntity.setDate(date);

       notesEntity = notesRepository.save(notesEntity);

        return transformEntity(notesEntity);
    }

    public boolean deleteById(Long id) {
        if (!notesRepository.existsById(id)) {
            return false;
        }

        notesRepository.deleteById(id);
        return true;
    }


    private Notes transformEntity(NotesEntity notesEntity) {
        var category = notesEntity.getCategory() != null ? notesEntity.getCategory().name() : Category.UNKOWN.name();
        return new Notes(
                notesEntity.getId(),
                notesEntity.getHeadline(),
                notesEntity.getText(),
                category,
                personTransformer.transformEntity(notesEntity.getOwner()),
                notesEntity.getDate()
        );
    }
}
