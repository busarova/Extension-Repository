package com.telerik.extensionrepository.controllers;

import com.telerik.extensionrepository.service.base.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SearchController {

    private SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService){
        this.searchService = searchService;

    }


    @PostMapping("/search")
    public ModelAndView showSearch(String params){

        ModelAndView modelAndView = new ModelAndView("search");

        modelAndView.addObject("searchResult", searchService.getAllByParam(params));

        return modelAndView;

    }
}
