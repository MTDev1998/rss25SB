package fr.univrouen.rss25SB.model;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TestRSS {
    public String loadFileXML() {
        try {
            // Initialize the resource to point to the XML file
            Resource resource = new DefaultResourceLoader().getResource("classpath:xml/item.xml");

            // Read the file content using a BufferedReader
            BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()));
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
            reader.close();

            // Return the file content as a string
            return content.toString();
        } catch (Exception e) {
            // Return an error message if something goes wrong
            return "Erreur lors de la lecture : " + e.getMessage();
        }
    }
}