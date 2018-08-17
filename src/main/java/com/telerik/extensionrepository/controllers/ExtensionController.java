package com.telerik.extensionrepository.controllers;

import com.telerik.extensionrepository.model.Extension;
import com.telerik.extensionrepository.service.base.ExtensionOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ExtensionController {

    private ExtensionOrderService extensionOrderService;

    @Autowired
    public ExtensionController(ExtensionOrderService extensionOrderService){
        this.extensionOrderService = extensionOrderService;
    }

    @RequestMapping("/extension-details/{name}")
    public ModelAndView getById(@PathVariable("name") String name){

        ModelAndView modelAndView = new ModelAndView("extension-details");

        Extension extension = extensionOrderService.getExtByName(name);

        System.out.println(extension.getName());

        modelAndView.addObject("extension", extension);

        return modelAndView;
    }
}
