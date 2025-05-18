package fr.univrouen.rss25SB.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ContentEntity {

    @Column(name = "content_type")
    private String type;

    private String src;
    private String value;

    public ContentEntity() {}

    public ContentEntity(String type, String src, String value) {
        this.type = type;
        this.src = src;
        this.value = value;
    }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getSrc() { return src; }
    public void setSrc(String src) { this.src = src; }

    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }
}