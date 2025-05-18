package fr.univrouen.rss25SB.model.rss;

import jakarta.xml.bind.annotation.*;

@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
public class Category {

    @XmlElement
    private String term;

    public Category() {}

    public Category(String term) {
        this.term = term;
    }

    public String getTerm() { return term; }
    public void setTerm(String term) { this.term = term; }
}