package de.htwberlin.notizzettelwebanwendung.notizzettelwebanwendung.persistence;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity(name = "persons")
    public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
    private List<NotesEntity> notes = new ArrayList<>();

    public PersonEntity(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    protected PersonEntity() {
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<NotesEntity> getNotes() {
        return notes;
    }

    public void setNotes(List<NotesEntity> notes) {
        this.notes = notes;
    }
}

