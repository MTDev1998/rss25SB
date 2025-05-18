package fr.univrouen.rss25SB.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "index"; // Page d'accueil
    }

    @GetMapping("/help")
    public String help() {
        return "help"; // Page d'aide
    }

    @GetMapping("/upload")
    public String upload() {
        return "upload"; // Page d'upload
    }
}