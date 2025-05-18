package fr.univrouen.rss25SB.Entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class AuthorEntity {

    private String name;
    private String email;
    private String uri;

    public AuthorEntity() {}

    public AuthorEntity(String name, String email, String uri) {
        this.name = name;
        this.email = email;
        this.uri = uri;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getUri() { return uri; }
    public void setUri(String uri) { this.uri = uri; }
}