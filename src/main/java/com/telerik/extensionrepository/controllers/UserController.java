package com.telerik.extensionrepository.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/profile")
    public String showUserPage(){

        return "profile";
    }

    @GetMapping("/accessDenied")
    public String accessDenied(){

        return "access-denied";
    }
}
