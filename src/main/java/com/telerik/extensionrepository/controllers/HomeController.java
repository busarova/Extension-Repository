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

    @GetMapping("/")
    public String showHomePage(){

        return "index";
    }

    @PostMapping
    public ModelAndView showFeatured(){
        ModelAndView modelAndView = new ModelAndView();

        List<String> list = new ArrayList<>();


        modelAndView.addObject("featured", list);

        return modelAndView;
    }

}
