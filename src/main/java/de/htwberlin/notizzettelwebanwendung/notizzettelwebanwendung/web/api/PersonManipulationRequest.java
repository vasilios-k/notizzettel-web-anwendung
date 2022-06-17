package de.htwberlin.notizzettelwebanwendung.notizzettelwebanwendung.web.api;



public class PersonManipulationRequest {

    private String firstName;
    private String lastName;

public PersonManipulationRequest(String firstName, String lastName){
    this.firstName = firstName;
    this.lastName = lastName;
    }
    public PersonManipulationRequest() {}

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

}
