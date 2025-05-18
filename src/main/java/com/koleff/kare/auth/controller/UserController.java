package com.koleff.kare.auth.controller;

import com.koleff.kare.auth.models.entity.User;
import com.koleff.kare.auth.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

    @GetMapping("/getbyemail")
    public User getUserByEmail(@RequestParam String email) {
        return userService.getUserByEmail(email);
    }

    @GetMapping("/getbyusername")
    public User getUserByUsername(@RequestParam String username) {
        return userService.getUserByUsername(username);
    }

    @GetMapping("/getbyid")
    public Optional<User> getUserById(@RequestParam String userId) {
        return userService.getUserById(userId);
    }

    @PostMapping("/save")
    public User saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @PutMapping("/update")
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("/delete")
    public void deleteUser(@RequestBody User user) {
        userService.deleteUser(user);
    }
}
