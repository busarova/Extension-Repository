package com.telerik.extensionrepository.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    /*@GetMapping("/")
    public String showHomePage(){

        return "index";
    }*/

    @GetMapping("/")
    public ModelAndView showFeatured(){
        ModelAndView modelAndView = new ModelAndView("index");

        List<String> featured = new ArrayList<>();

        featured.add("EXTENSIONS 1");
        featured.add("EXTENSIONS 2");
        featured.add("EXTENSIONS 3");

        modelAndView.addObject("featured", featured);

        List<String> popular = new ArrayList<>();

        popular.add("POPULAR 1");
        popular.add("POPULAR 2");
        popular.add("POPULAR 3");

        modelAndView.addObject("popular", popular);

        List<String> mostNew = new ArrayList<>();

        mostNew.add("MOST NEW 1");
        mostNew.add("MOST NEW 2");
        mostNew.add("MOST NEW 3");

        modelAndView.addObject("mostNew", mostNew);

        return modelAndView;
    }

}
