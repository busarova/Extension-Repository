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
    public ModelAndView showSearch(@RequestParam String params, @RequestParam String orderParams){

        ModelAndView modelAndView = new ModelAndView("searchResults");

        modelAndView.addObject("allApproved", searchService.getAllByParam(params, orderParams));

        modelAndView.addObject("searchQuery", params);

        return modelAndView;

    }

    @GetMapping("/searchAndOrder/{param}")
    public ModelAndView showSearchResultsSorted(@PathVariable ("param") String param){

        ModelAndView modelAndView = new ModelAndView("searchResults");

        String[] splitParam = param.split(" ");

        modelAndView.addObject("allApproved", searchService.getAllByParam(splitParam[0], splitParam[1]));

        modelAndView.addObject("searchQuery", splitParam[0]);

        return modelAndView;
    }

}
