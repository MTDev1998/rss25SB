package fr.univrouen.rss25SB.model.rss;

import jakarta.xml.bind.annotation.*;

@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
public class Category {

    @XmlAttribute
    private String term;

    // Constructors
    public Category() {}

    public Category(String term) {
        this.term = term;
    }

    // Getters and Setters
    public String getTerm() { return term; }
    public void setTerm(String term) { this.term = term; }
}