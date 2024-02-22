package com.koleff.kare.controller;

import com.koleff.kare.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/test")
    public String testUserAccess(){
        return "User access level";
    }

    @GetMapping("/test2")
    public UserDetails testUserAccess2(){
        return userService.loadUserByUsername("Koleff");
    }
}
