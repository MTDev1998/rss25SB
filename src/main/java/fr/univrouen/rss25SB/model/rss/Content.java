package fr.univrouen.rss25SB.model.rss;

import jakarta.xml.bind.annotation.*;

@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
public class Content {

    @XmlAttribute
    private String type;

    @XmlAttribute
    private String src;

    @XmlValue
    private String value;

    public Content() {}

    public Content(String type, String src) {
        this.type = type;
        this.src = src;
        this.value = "";
    }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getSrc() { return src; }
    public void setSrc(String src) { this.src = src; }

    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }
}