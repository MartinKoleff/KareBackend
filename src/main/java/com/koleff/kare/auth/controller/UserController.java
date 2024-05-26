package com.koleff.kare.auth.controller;

import com.koleff.kare.auth.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/user/test")
@CrossOrigin("*")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/useraccess")
    @PreAuthorize("hasRole('USER')")
    public String testUserAccess(){
        return "User access level";
    }

    @GetMapping("/useraccess/2")
    @PreAuthorize("hasRole('USER')")
    public UserDetails testUserAccess2(){
        return userService.loadUserByUsername("Koleff");
    }
}
