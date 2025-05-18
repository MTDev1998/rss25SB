package fr.univrouen.rss25SB.model.rss;

import jakarta.xml.bind.annotation.*;

@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
public class Image {

    @XmlAttribute
    private String type;

    @XmlAttribute
    private String href;

    @XmlAttribute
    private String alt;

    @XmlAttribute
    private Integer length;

    public Image() {}

    public Image(String type, String href, String alt, Integer length) {
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