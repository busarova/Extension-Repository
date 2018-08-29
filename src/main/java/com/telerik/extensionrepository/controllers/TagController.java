package com.telerik.extensionrepository.controllers;

import com.telerik.extensionrepository.service.base.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.CriteriaBuilder;

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

    @GetMapping("/tags/searchById/{id}")
    public ModelAndView showExtensionsByTag(@PathVariable("id") String id){

        ModelAndView modelAndView = new ModelAndView("tag-palace-show");

        modelAndView.addObject("extensions", tagService.getExtensionsByTag(Integer.parseInt(id)));
        modelAndView.addObject("Tag", tagService.getTagById(Integer.parseInt(id)));

        return modelAndView;

    }

    @PostMapping("/tags/search")
    public ModelAndView showTagsByName(@RequestParam String name){

        ModelAndView modelAndView = new ModelAndView("tag-palace");

        modelAndView.addObject("allTags", tagService.getAllTagsByName(name));

        return modelAndView;

    }
}
