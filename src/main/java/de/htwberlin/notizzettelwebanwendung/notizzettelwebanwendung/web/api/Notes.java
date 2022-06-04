package de.htwberlin.notizzettelwebanwendung.notizzettelwebanwendung.web.api;

public class Notes {


    private Long id;
    private String headline;
    private String text;
    private String category;
    private Person person;

    public Notes(Long id, String headline,String text, String category, Person person) {
        this.id = id;
        this.headline = headline;
        this.text = text;
        this.category = category;
        this.person = person;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
