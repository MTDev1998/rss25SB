package fr.univrouen.rss25SB.controllers;

import fr.univrouen.rss25SB.Entity.RSSItem;
import fr.univrouen.rss25SB.Repository.RSSItemRepository;
import fr.univrouen.rss25SB.model.rss.*;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.StringReader;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Controller
@RequestMapping("/rss25SB")
public class RSS25Controller {

    private static final Logger LOGGER = Logger.getLogger(RSS25Controller.class.getName());
    private static final String XSD_PATH = "rss25SB.xsd";

    @Autowired
    private RSSItemRepository rssItemRepository;

    // Load XSD schema for validation
    private Schema getSchema() throws Exception {
        SchemaFactory sf = SchemaFactory.newInstance(javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI);
        return sf.newSchema(getClass().getClassLoader().getResource(XSD_PATH));
    }

    // Validate XML against XSD
    private boolean validateXML(String xml) throws Exception {
        JAXBContext jc = JAXBContext.newInstance(Feed.class);
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        unmarshaller.setSchema(getSchema());
        unmarshaller.unmarshal(new StringReader(xml));
        return true;
    }

    // GET /rss25SB/resume/xml - List all articles in XML
    @GetMapping(value = "/resume/xml", produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public Feed getArticleListXML() {
        List<RSSItem> items = rssItemRepository.findAll();
        List<Item> feedItems = new ArrayList<>();
        for (RSSItem rssItem : items) {
            Item item = convertToItem(rssItem);
            Item summary = new Item(item.getGuid(), item.getTitle(), null, item.getPublished(), null, null, null);
            feedItems.add(summary);
        }
        return new Feed("RSS25SB Feed", ZonedDateTime.now(), "© 2025 Tarek", "fr", new ArrayList<>(), feedItems);
    }

    // GET /rss25SB/resume/html - List all articles in HTML
    @GetMapping(value = "/resume/html")
    public String getArticleListHTML(Model model) {
        List<RSSItem> items = rssItemRepository.findAll();
        model.addAttribute("items", items);
        return "articles";
    }

    // GET /rss25SB/resume/xml/{id} - Get article details in XML
    @GetMapping(value = "/resume/xml/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public Feed getArticleDetailsXML(@PathVariable String id) {
        Optional<RSSItem> optionalItem = rssItemRepository.findById(id);
        if (optionalItem.isPresent()) {
            Item item = convertToItem(optionalItem.get());
            return new Feed("Article Details", ZonedDateTime.now(), "© 2025 Tarek", "fr", new ArrayList<>(), List.of(item));
        } else {
            return createErrorFeed(id, "ERROR", "Article with ID " + id + " not found");
        }
    }

    // GET /rss25SB/html/{id} - Get article details in HTML
    @GetMapping(value = "/html/{id}")
    public String getArticleDetailsHTML(@PathVariable("id") String guid, Model model) {
        System.out.println("Fetching article details for GUID: " + guid);
        Optional<RSSItem> itemOpt = rssItemRepository.findByGuid(guid);
        if (itemOpt.isEmpty()) {
            System.out.println("Article with GUID " + guid + " not found");
            model.addAttribute("error", "Article with GUID " + guid + " not found");
            return "error";
        }

        RSSItem item = itemOpt.get();
        System.out.println("Article found: GUID=" + item.getGuid() + ", Title=" + item.getTitle());
        model.addAttribute("item", item);
        return "article-details";
    }

    // POST /rss25SB/insert - Add new articles
    @PostMapping(value = "/insert", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public String insertArticle(@RequestBody String xmlFeed) {
        try {
            validateXML(xmlFeed);
            JAXBContext jc = JAXBContext.newInstance(Feed.class);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            Feed feed = (Feed) unmarshaller.unmarshal(new StringReader(xmlFeed));
            for (Item item : feed.getItems()) {
                LOGGER.info("Processing item: " + item.getTitle());
                Optional<RSSItem> existing = rssItemRepository.findByTitleAndPublished(item.getTitle(), item.getPublished());
                if (existing.isPresent()) {
                    return "<result><id>" + item.getGuid() + "</id><status>ERROR</status><description>Duplicate article (title and date)</description></result>";
                }
                RSSItem rssItem = convertToRSSItem(item);
                rssItemRepository.save(rssItem);
            }
            return "<result><id>" + feed.getItems().get(0).getGuid() + "</id><status>INSERTED</status></result>";
        } catch (Exception e) {
            LOGGER.severe("Validation error: " + e.getMessage());
            return "<result><id>0</id><status>ERROR</status><description>" + e.getMessage() + "</description></result>";
        }
    }

    // DELETE /rss25SB/delete/{id} - Delete an article
    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public String deleteArticle(@PathVariable String id) {
        Optional<RSSItem> optionalItem = rssItemRepository.findById(id);
        if (optionalItem.isPresent()) {
            rssItemRepository.deleteById(id);
            if (rssItemRepository.count() == 0) {
                // Cleanup empty feed (bonus)
            }
            return "<result><id>" + id + "</id><status>DELETED</status></result>";
        } else {
            return "<result><id>" + id + "</id><status>ERROR</status><description>Article with ID " + id + " not found</description></result>";
        }
    }

    private Item convertToItem(RSSItem rssItem) {
        List<Category> categories = new ArrayList<>();
        for (String term : rssItem.getCategories()) {
            categories.add(new Category(term));
        }
        List<Author> authors = new ArrayList<>();
        for (fr.univrouen.rss25SB.Entity.AuthorEntity authorEntity : rssItem.getAuthors()) {
            authors.add(new Author(authorEntity.getName(), authorEntity.getEmail(), authorEntity.getUri()));
        }
        return new Item(
                rssItem.getGuid(),
                rssItem.getTitle(),
                categories,
                rssItem.getPublished(),
                new Image(rssItem.getImage().getType(), rssItem.getImage().getHref(), rssItem.getImage().getAlt(), rssItem.getImage().getLength()),
                new Content(rssItem.getContent().getType(), rssItem.getContent().getSrc()),
                authors
        );
    }

    private RSSItem convertToRSSItem(Item item) {
        RSSItem rssItem = new RSSItem();
        rssItem.setGuid(item.getGuid());
        rssItem.setTitle(item.getTitle());
        List<String> categoryTerms = new ArrayList<>();
        if (item.getCategories() != null) {
            for (Category category : item.getCategories()) {
                categoryTerms.add(category.getTerm() != null ? category.getTerm() : "");
            }
        }
        rssItem.setCategories(categoryTerms);
        rssItem.setPublished(item.getPublished());
        rssItem.setImage(new fr.univrouen.rss25SB.Entity.ImageEntity(
                item.getImage() != null ? item.getImage().getType() : "",
                item.getImage() != null ? item.getImage().getHref() : "",
                item.getImage() != null ? item.getImage().getAlt() : "",
                item.getImage() != null ? item.getImage().getLength() : 0
        ));
        rssItem.setContent(new fr.univrouen.rss25SB.Entity.ContentEntity(
                item.getContent() != null ? item.getContent().getType() : "",
                item.getContent() != null ? item.getContent().getSrc() : "",
                ""
        ));
        List<fr.univrouen.rss25SB.Entity.AuthorEntity> authorEntities = new ArrayList<>();
        if (item.getAuthors() != null) {
            for (Author author : item.getAuthors()) {
                authorEntities.add(new fr.univrouen.rss25SB.Entity.AuthorEntity(
                        author.getName() != null ? author.getName() : "",
                        author.getEmail() != null ? author.getEmail() : "",
                        author.getUri() != null ? author.getUri() : ""
                ));
            }
        }
        rssItem.setAuthors(authorEntities);
        return rssItem;
    }

    private Feed createErrorFeed(String id, String status, String description) {
        Feed errorFeed = new Feed();
        errorFeed.setTitle("Error");
        errorFeed.setPubDate(ZonedDateTime.now());
        errorFeed.setCopyright("© 2025 Tarek");
        errorFeed.setLang("fr");
        errorFeed.setItems(new ArrayList<>());
        return errorFeed;
    }
}