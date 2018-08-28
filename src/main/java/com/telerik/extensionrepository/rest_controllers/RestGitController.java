package com.telerik.extensionrepository.rest_controllers;

import com.telerik.extensionrepository.service.base.GitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class RestGitController {


    private GitService gitService;

    @Autowired
    public RestGitController(GitService gitService){
        this.gitService = gitService;
    }

//    @RequestMapping("/git-details/{id}")
//    public ModelAndView getGitInfo(@PathVariable("id") String id){
//
//
//    }

}
