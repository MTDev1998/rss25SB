package fr.univrouen.rss25SB.controllers;

import fr.univrouen.rss25SB.model.rss.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.Arrays;

@RestController
@RequestMapping("/rss")
public class RSSController {

    @GetMapping(value = "/feed", produces = MediaType.APPLICATION_XML_VALUE)
    public Feed getFeed() {
        // Example RSS25 feed
        Link link = new Link("self", "application/rss+xml", "http://example.com/rss");
        Category category = new Category("Technology");
        Image image = new Image("image/jpeg", "http://example.com/image.jpg", "Sample Image", 1024);
        Content content = new Content("text/html", "http://example.com/content");
        Author author = new Author("John Doe", "john.doe@example.com", "http://example.com/johndoe");
        Item item = new Item(
                "123456-7890",
                "Sample Article",
                Arrays.asList(category),
                ZonedDateTime.now(),
                image,
                content,
                Arrays.asList(author)
        );
        return new Feed(
                "Sample RSS Feed",
                ZonedDateTime.now(),
                "© 2025 Example Corp",
                "en",
                Arrays.asList(link),
                Arrays.asList(item)
        );
    }

    @PostMapping(value = "/feed", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public Feed createFeed(@RequestBody Feed feed) {
        // Logic to process the received feed (e.g., save or validate)
        return feed; // Echo back the received feed for confirmation
    }
}