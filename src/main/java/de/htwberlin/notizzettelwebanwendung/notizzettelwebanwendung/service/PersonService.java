package de.htwberlin.notizzettelwebanwendung.notizzettelwebanwendung.service;

import de.htwberlin.notizzettelwebanwendung.notizzettelwebanwendung.persistence.PersonEntity;
import de.htwberlin.notizzettelwebanwendung.notizzettelwebanwendung.persistence.PersonRepository;
import de.htwberlin.notizzettelwebanwendung.notizzettelwebanwendung.web.api.Person;
import de.htwberlin.notizzettelwebanwendung.notizzettelwebanwendung.web.api.PersonManipulationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    private final PersonTransformer personTransformer;

    public PersonService(PersonRepository personRepository, PersonTransformer personTransformer) {
        this.personRepository = personRepository;
        this.personTransformer = personTransformer;
    }

    public List<Person> findAll() {
        List<PersonEntity> persons = personRepository.findAll();
        return persons.stream()
                .map(personTransformer::transformEntity)
                .collect(Collectors.toList());
    }

    public Person findById(Long id) {
        var personEntity = personRepository.findById(id);
        return personEntity.map(personTransformer::transformEntity).orElse(null);
    }

    public List<PersonEntity> findByFirstName(String firstName) {
      // var personEntities = personRepository.findAllByFirstName(firstName);
       // List<PersonEntity> a = personEntities.stream().map(personTransformer::transformEntity).collect(Collectors.toList());
        return personRepository.findAllByFirstName(firstName) ;
    }

    public Person create(PersonManipulationRequest request) {
        var personEntity = new PersonEntity(request.getFirstName(), request.getLastName());
       personEntity = personRepository.save(personEntity);
        return personTransformer.transformEntity(personEntity);
    }

    public Person update(Long id, PersonManipulationRequest request) {
        var personEntityOptional = personRepository.findById(id);
        if (personEntityOptional.isEmpty()) {
            return null;
        }

        var personEntity = personEntityOptional.get();
       personEntity.setFirstName(request.getFirstName());
       personEntity.setLastName(request.getLastName());
        personEntity = personRepository.save(personEntity);

        return personTransformer.transformEntity(personEntity);
    }

    public boolean deleteById(Long id) {
        if (!personRepository.existsById(id)) {
            return false;
        }

        personRepository.deleteById(id);
        return true;
    }
}
