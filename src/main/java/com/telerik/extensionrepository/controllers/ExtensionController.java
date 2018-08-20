package com.telerik.extensionrepository.controllers;

import com.telerik.extensionrepository.model.Extension;
import com.telerik.extensionrepository.service.base.ExtensionOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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

    @RequestMapping("/edit-extension/{name}")
    public ModelAndView editExtension(@PathVariable("name") String name){

        ModelAndView modelAndView = new ModelAndView("edit-extension");

        modelAndView.addObject(extensionOrderService.getExtByName(name));

        return modelAndView;
    }


    @RequestMapping("/sortby/{name}")
    public ModelAndView sortByParam(@PathVariable("name") String name){

        ModelAndView modelAndView = new ModelAndView("index");

        List<Extension> list = extensionOrderService.returnOrderedBy(name);

        String[] command = name.split(" ");

        switch (command[0]){

            case "popular":
                modelAndView.addObject("popular", list);
                modelAndView.addObject("featured", extensionOrderService.getFeatured());
                modelAndView.addObject("newExt", extensionOrderService.getNew());
                break;

            case "new":

                modelAndView.addObject("newExt", list);
                modelAndView.addObject("featured", extensionOrderService.getFeatured());
                modelAndView.addObject("popular", extensionOrderService.getPopular());
                break;

            case "featured":

                modelAndView.addObject("featured", list);
                modelAndView.addObject("popular", extensionOrderService.getPopular());
                modelAndView.addObject("newExt", extensionOrderService.getNew());
                break;

        }

        return modelAndView;
    }
}
