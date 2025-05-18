package fr.univrouen.rss25SB.util;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.IOException;
import java.io.StringReader;

public class XMLValidator {

    public static boolean validateXML(String xmlContent, String xsdPath) {
        try {
            System.out.println("Attempting to load schema from: " + xsdPath);
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(new StreamSource(XMLValidator.class.getResourceAsStream(xsdPath)));
            Validator validator = schema.newValidator();
            System.out.println("Validating XML: " + xmlContent.substring(0, Math.min(xmlContent.length(), 100)) + "...");
            Source xmlSource = new StreamSource(new StringReader(xmlContent));
            validator.validate(xmlSource);
            System.out.println("XML validation successful.");
            return true;
        } catch (SAXException e) {
            System.err.println("XML Validation Error (SAXException): " + e.getMessage());
            return false;
        } catch (IOException e) {
            System.err.println("XML Validation Error (IOException): " + e.getMessage());
            return false;
        }
    }
}