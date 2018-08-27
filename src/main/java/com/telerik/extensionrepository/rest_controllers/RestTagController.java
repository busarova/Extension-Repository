package com.telerik.extensionrepository.rest_controllers;

import com.telerik.extensionrepository.service.base.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestTagController {

    private TagService tagService;

    @Autowired
    public RestTagController(TagService tagService){
        this.tagService = tagService;
    }

    @GetMapping("api/tags/")
    public String showTags(){
        return null;
    }
}
