package fr.univrouen.rss25SB.model.rss;

import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "feed")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"title", "pubDate", "copyright", "links", "items"})
public class Feed {

    @XmlElement
    private String title;

    @XmlElement
    @XmlJavaTypeAdapter(ZonedDateTimeAdapter.class)
    private ZonedDateTime pubDate;

    @XmlElement
    private String copyright;

    @XmlElement(name = "link")
    private List<Link> links = new ArrayList<>();

    @XmlElement(name = "item")
    private List<Item> items = new ArrayList<>();

    @XmlAttribute
    private String lang;

    @XmlAttribute
    private String version = "25";

    public Feed() {}

    public Feed(String title, ZonedDateTime pubDate, String copyright, String lang, List<Link> links, List<Item> items) {
        this.title = title;
        this.pubDate = pubDate;
        this.copyright = copyright;
        this.lang = lang;
        this.links = links != null ? links : new ArrayList<>();
        this.items = items != null ? items : new ArrayList<>();
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public ZonedDateTime getPubDate() { return pubDate; }
    public void setPubDate(ZonedDateTime pubDate) { this.pubDate = pubDate; }

    public String getCopyright() { return copyright; }
    public void setCopyright(String copyright) { this.copyright = copyright; }

    public List<Link> getLinks() { return links; }
    public void setLinks(List<Link> links) { this.links = links; }

    public List<Item> getItems() { return items; }
    public void setItems(List<Item> items) { this.items = items; }

    public String getLang() { return lang; }
    public void setLang(String lang) { this.lang = lang; }

    public String getVersion() { return version; }
    public void setVersion(String version) { this.version = version; }
}