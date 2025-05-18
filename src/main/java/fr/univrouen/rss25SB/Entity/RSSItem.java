package fr.univrouen.rss25SB.Entity;

import jakarta.persistence.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class RSSItem {

    @Id
    private String guid;

    private String title;

    @ElementCollection
    private List<String> categories = new ArrayList<>();

    private ZonedDateTime published;

    @Embedded
    private ImageEntity image;

    @Embedded
    private ContentEntity content;

    @ElementCollection
    @CollectionTable(name = "rssitem_authors")
    private List<AuthorEntity> authors = new ArrayList<>();

    public RSSItem() {}

    public String getGuid() { return guid; }
    public void setGuid(String guid) { this.guid = guid; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public List<String> getCategories() { return categories; }
    public void setCategories(List<String> categories) { this.categories = categories; }

    public ZonedDateTime getPublished() { return published; }
    public void setPublished(ZonedDateTime published) { this.published = published; }

    public ImageEntity getImage() { return image; }
    public void setImage(ImageEntity image) { this.image = image; }

    public ContentEntity getContent() { return content; }
    public void setContent(ContentEntity content) { this.content = content; }

    public List<AuthorEntity> getAuthors() { return authors; }
    public void setAuthors(List<AuthorEntity> authors) { this.authors = authors; }
}