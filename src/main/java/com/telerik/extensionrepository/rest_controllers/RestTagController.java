package com.telerik.extensionrepository.rest_controllers;

import com.telerik.extensionrepository.model.Tags;
import com.telerik.extensionrepository.service.base.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestTagController {

    private TagService tagService;

    @Autowired
    public RestTagController(TagService tagService){
        this.tagService = tagService;
    }

    @GetMapping("/test/tags")
    public List<Tags> showTags(){

        return tagService.getAllTags();
    }
}
