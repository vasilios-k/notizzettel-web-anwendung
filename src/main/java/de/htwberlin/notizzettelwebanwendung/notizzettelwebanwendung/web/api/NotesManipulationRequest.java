package de.htwberlin.notizzettelwebanwendung.notizzettelwebanwendung.web.api;

public class NotesManipulationRequest {

    private String headline;

    private String text;

    private String category;
    private Long ownerId;

    public NotesManipulationRequest(String headline, String text, String category, Long ownerId) {
        this.headline = headline;
        this.text = text;
        this.category = category;
        this.ownerId = ownerId;
    }
public NotesManipulationRequest(){}
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

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }
}
