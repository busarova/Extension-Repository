package com.telerik.extensionrepository.controllers;

import com.telerik.extensionrepository.service.base.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SearchController {

    private SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService){
        this.searchService = searchService;

    }


    @PostMapping("/search")
    public ModelAndView showSearch(@RequestParam String params){

        ModelAndView modelAndView = new ModelAndView("index");

        modelAndView.addObject("allApproved", searchService.getAllByParam(params));

        return modelAndView;

    }
}
