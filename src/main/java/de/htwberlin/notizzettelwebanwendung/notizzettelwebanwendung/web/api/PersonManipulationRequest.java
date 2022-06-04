package de.htwberlin.notizzettelwebanwendung.notizzettelwebanwendung.web.api;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class PersonManipulationRequest {
    @Size(min = 3, message = "Please provide a first name with 3 characters or more.")
    private String firstName;
    @NotBlank(message = "The last name must not be empty.")
    private String lastName;


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