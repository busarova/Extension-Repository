package com.telerik.extensionrepository.controllers;

import com.telerik.extensionrepository.model.Extension;
import com.telerik.extensionrepository.service.base.ExtensionInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HomeController {

    private ExtensionInfoService extensionInfoService;

    @Autowired
    public HomeController(ExtensionInfoService extensionInfoService){
        this.extensionInfoService = extensionInfoService;
    }

    @GetMapping("/")
    public ModelAndView showAll(){
        ModelAndView modelAndView = new ModelAndView("index");

        /*List<Extension> featured = extensionInfoService.getFeatured();

        modelAndView.addObject("featured", featured);

        List<Extension> popular = extensionInfoService.getPopular();

        modelAndView.addObject("popular", popular);

        List<Extension> newExt = extensionInfoService.getNew();

        modelAndView.addObject("newExt", newExt);*/

        modelAndView.addObject("allApproved", extensionInfoService.getAllApproved());

        System.out.println(extensionInfoService.getAllApproved().size());

        return modelAndView;
    }

    @GetMapping("/featured")
    public ModelAndView showFeatured(){

        ModelAndView modelAndView = new ModelAndView("index");

        modelAndView.addObject("allApproved", extensionInfoService.getFeatured());

        return modelAndView;
    }


}
