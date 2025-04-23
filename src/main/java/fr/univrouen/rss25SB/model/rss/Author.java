package fr.univrouen.rss25SB.model.rss;

import jakarta.xml.bind.annotation.*;

@XmlType(propOrder = {"name", "email", "uri"})
@XmlAccessorType(XmlAccessType.FIELD)
public class Author {

    @XmlElement
    private String name;

    @XmlElement(required = false)
    private String email;

    @XmlElement(required = false)
    private String uri;

    // Constructors
    public Author() {}

    public Author(String name, String email, String uri) {
        this.name = name;
        this.email = email;
        this.uri = uri;
    }

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getUri() { return uri; }
    public void setUri(String uri) { this.uri = uri; }
}