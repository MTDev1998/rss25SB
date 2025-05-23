package fr.univrouen.rss25SB.controllers;

import fr.univrouen.rss25SB.Entity.AppUser;
import fr.univrouen.rss25SB.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<AppUser> getAllUsers() {
        return userRepository.findAll();
    }
}