package de.htwberlin.notizzettelwebanwendung.notizzettelwebanwendung.web.api;

import java.util.List;

public class Person {

    private long id;
    private String firstName;
    private String lastName;

    private List<Long> notes;

    public Person(long id, String firstName, String lastName,List<Long> notes) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.notes = notes;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public List<Long> getNotes() {
        return notes;
    }

    public void setNotes(List<Long> notes) {
        this.notes = notes;
    }
}
