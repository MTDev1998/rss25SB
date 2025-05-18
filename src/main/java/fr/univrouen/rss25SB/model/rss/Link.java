package fr.univrouen.rss25SB.model.rss;

import jakarta.xml.bind.annotation.*;

@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
public class Link {

    @XmlAttribute
    private String rel;

    @XmlAttribute
    private String type;

    @XmlAttribute
    private String href;

    public Link() {}

    public Link(String rel, String type, String href) {
        this.rel = rel;
        this.type = type;
        this.href = href;
    }

    public String getRel() { return rel; }
    public void setRel(String rel) { this.rel = rel; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getHref() { return href; }
    public void setHref(String href) { this.href = href; }
}