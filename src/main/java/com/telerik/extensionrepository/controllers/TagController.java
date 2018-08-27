package com.telerik.extensionrepository.controllers;

import com.telerik.extensionrepository.service.base.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TagController {

    private TagService tagService;

    @Autowired
    public TagController(TagService tagService){
        this.tagService = tagService;
    }

    @GetMapping("/tags")
    public ModelAndView showTagPalacePage(){

        ModelAndView modelAndView = new ModelAndView("tag-palace");

        modelAndView.addObject("allTags", tagService.getAllTags());

        return modelAndView;
    }
}
