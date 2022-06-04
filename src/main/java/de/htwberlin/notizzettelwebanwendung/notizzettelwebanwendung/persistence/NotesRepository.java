package de.htwberlin.notizzettelwebanwendung.notizzettelwebanwendung.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotesRepository extends JpaRepository<NotesEntity, Long> {
}
