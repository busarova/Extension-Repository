package com.telerik.extensionrepository.controllers;

import com.telerik.extensionrepository.model.Extension;
import com.telerik.extensionrepository.service.base.ExtensionOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    private ExtensionOrderService extensionOrderService;

    @Autowired
    public HomeController(ExtensionOrderService extensionOrderService){
        this.extensionOrderService = extensionOrderService;
    }

    @GetMapping("/")
    public ModelAndView showFeatured(){
        ModelAndView modelAndView = new ModelAndView("index");

        List<Extension> featured = extensionOrderService.getFeatured();

        modelAndView.addObject("featured", featured);

        List<Extension> popular = extensionOrderService.getPopular();

        modelAndView.addObject("popular", popular);

        List<Extension> newExt = extensionOrderService.getNew();

        modelAndView.addObject("newExt", newExt);

        return modelAndView;
    }


}
