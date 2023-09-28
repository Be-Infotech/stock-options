package fr.beit.projetcac.api.controller;

import fr.beit.projetcac.api.model.User;
import fr.beit.projetcac.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getUsers")
    public Iterable<User>getUsers(){return userService.getUsers();}
}
