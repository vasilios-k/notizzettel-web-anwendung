package de.htwberlin.notizzettelwebanwendung.notizzettelwebanwendung.persistence;

import javax.persistence.*;


@Entity(name = "notes")
public class NotesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "headline", nullable = false)
    private String headline;

    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "category")
    @Enumerated(value = EnumType.STRING)
    private Category category;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private PersonEntity owner;

    @Column(name = "datum")
    private String date;


    public NotesEntity() {
    }

    public NotesEntity(String headline,String text,Category category, PersonEntity owner, String date ) {
        this.headline = headline;
        this.text = text;
        this.category = category;
        this.owner = owner;
        this.date = date;
    }

    public Long getId() {
        return id;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public PersonEntity getOwner() {
        return owner;
    }

    public void setOwner(PersonEntity owner) {
        this.owner = owner;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
