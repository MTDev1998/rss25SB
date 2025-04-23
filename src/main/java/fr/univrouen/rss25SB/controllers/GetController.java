package fr.univrouen.rss25SB.controllers;

import fr.univrouen.rss25SB.model.Item;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class GetController {
    @GetMapping("/resume")
    public String getListRSSinXML() {
        return "Envoi de la liste des flux RSS";
    }


/*@GetMapping("/guid")
public String getRSSinXML() {
    return "Détail du contenu RSS demandé";
}*/
    @GetMapping("/guid")
    public String getRSSinXML(
            @RequestParam(value = "guid") String texte) {
        return ("Détail du flux RSS demandé : " + texte);
    }
    @GetMapping("/test")
    public String getRSSinXMLVersion2(
            @RequestParam(value = "nb") String nb,  @RequestParam(value = "search") String search) {
        return "Test :\nguid = " + nb + "\n titre = " + search;
    }


    @RequestMapping(value = "/xml", produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public Item getXML() {
        Item it = new Item("12345678", "Test item", "2022-05-01T11:22:33");
        return it;
    }
}
