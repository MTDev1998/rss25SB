package fr.univrouen.rss25SB.model.rss;

import jakarta.xml.bind.annotation.*;

@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
public class Content {

    @XmlAttribute
    private String type;

    @XmlAttribute
    private String src;

    // Constructors
    public Content() {}

    public Content(String type, String src) {
        this.type = type;
        this.src = src;
    }

    // Getters and Setters
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getSrc() { return src; }
    public void setSrc(String src) { this.src = src; }
}