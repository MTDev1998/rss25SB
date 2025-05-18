package fr.univrouen.rss25SB.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ImageEntity {

    @Column(name = "image_type")
    private String type;

    private String href;
    private String alt;
    private Integer length;

    public ImageEntity() {}

    public ImageEntity(String type, String href, String alt, Integer length) {
        this.type = type;
        this.href = href;
        this.alt = alt;
        this.length = length;
    }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getHref() { return href; }
    public void setHref(String href) { this.href = href; }

    public String getAlt() { return alt; }
    public void setAlt(String alt) { this.alt = alt; }

    public Integer getLength() { return length; }
    public void setLength(Integer length) { this.length = length; }
}