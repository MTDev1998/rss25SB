package fr.univrouen.rss25SB.Repository;

import fr.univrouen.rss25SB.Entity.RSSItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.ZonedDateTime;
import java.util.Optional;

public interface RSSItemRepository extends JpaRepository<RSSItem, String> {
    Optional<RSSItem> findByTitleAndPublished(String title, ZonedDateTime published);

    Optional<RSSItem> findByGuid(String guid);
}