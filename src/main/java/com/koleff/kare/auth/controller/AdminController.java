package com.koleff.kare.auth.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/admin")
@CrossOrigin("*")
public class AdminController {
    
    @GetMapping("/test")
    public String testAdminAccess(){
        return "Admin level access";
    }

}
