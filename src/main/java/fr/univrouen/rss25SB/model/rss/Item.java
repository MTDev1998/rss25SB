package fr.univrouen.rss25SB.model.rss;

import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@XmlType(propOrder = {"guid", "title", "categories", "published", "image", "content", "authors"})
@XmlAccessorType(XmlAccessType.FIELD)
public class Item {

    @XmlElement
    private String guid;

    @XmlElement
    private String title;

    @XmlElement(name = "category")
    private List<Category> categories = new ArrayList<>();

    @XmlElement
    @XmlJavaTypeAdapter(ZonedDateTimeAdapter.class)
    private ZonedDateTime published;

    @XmlElement(required = false)
    private Image image;

    @XmlElement
    private Content content;

    @XmlElement(name = "author")
    private List<Author> authors = new ArrayList<>();

    // Constructors
    public Item() {}

    public Item(String guid, String title, List<Category> categories, ZonedDateTime published, Image image, Content content, List<Author> authors) {
        this.guid = guid;
        this.title = title;
        this.categories = categories != null ? categories : new ArrayList<>();
        this.published = published;
        this.image = image;
        this.content = content;
        this.authors = authors != null ? authors : new ArrayList<>();
    }

    // Getters and Setters
    public String getGuid() { return guid; }
    public void setGuid(String guid) { this.guid = guid; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public List<Category> getCategories() { return categories; }
    public void setCategories(List<Category> categories) { this.categories = categories; }

    public ZonedDateTime getPublished() { return published; }
    public void setPublished(ZonedDateTime published) { this.published = published; }

    public Image getImage() { return image; }
    public void setImage(Image image) { this.image = image; }

    public Content getContent() { return content; }
    public void setContent(Content content) { this.content = content; }

    public List<Author> getAuthors() { return authors; }
    public void setAuthors(List<Author> authors) { this.authors = authors; }
}