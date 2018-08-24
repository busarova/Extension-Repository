package com.telerik.extensionrepository.controllers;

import com.telerik.extensionrepository.model.Extension;
import com.telerik.extensionrepository.service.base.AdminService;
import com.telerik.extensionrepository.service.base.ExtensionInfoService;
import com.telerik.extensionrepository.service.base.ExtensionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ExtensionController {

    private ExtensionInfoService extensionInfoService;
    private AdminService adminService;
    private ExtensionService extensionService;

    @Autowired
    public ExtensionController(ExtensionInfoService extensionInfoService, AdminService adminService, ExtensionService extensionService){
        this.extensionInfoService = extensionInfoService;
        this.adminService = adminService;
        this.extensionService = extensionService;
    }

    @RequestMapping("/extension-details/{name}")
    public ModelAndView getById(@PathVariable("name") String name){

        ModelAndView modelAndView = new ModelAndView("extension-details");

        Extension extension = extensionInfoService.getExtByName(name);

        System.out.println(extension.getName());

        modelAndView.addObject("extension", extension);

        return modelAndView;
    }

    @RequestMapping("/edit-extension/{name}")
    public ModelAndView editExtension(@PathVariable("name") String name){

        ModelAndView modelAndView = new ModelAndView("edit-extension");

        modelAndView.addObject(extensionInfoService.getExtByName(name));

        return modelAndView;
    }


    @RequestMapping("/sortby/{name}")
    public ModelAndView sortByParam(@PathVariable("name") String name){

        ModelAndView modelAndView = new ModelAndView("index");

        List<Extension> list = extensionInfoService.returnOrderedBy(name);

        String[] command = name.split(" ");

        switch (command[0]){

            case "popular":
                modelAndView.addObject("popular", list);
                modelAndView.addObject("featured", extensionInfoService.getFeatured());
                modelAndView.addObject("newExt", extensionInfoService.getNew());
                break;

            case "new":

                modelAndView.addObject("newExt", list);
                modelAndView.addObject("featured", extensionInfoService.getFeatured());
                modelAndView.addObject("popular", extensionInfoService.getPopular());
                break;

            case "featured":

                modelAndView.addObject("featured", list);
                modelAndView.addObject("popular", extensionInfoService.getPopular());
                modelAndView.addObject("newExt", extensionInfoService.getNew());
                break;

        }

        return modelAndView;
    }

    @RequestMapping("/delete-extension/{id}")
    public String deleteExtension(@PathVariable("id") String id){

        User user = (User) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();

        Extension extension = extensionInfoService.getById(Integer.parseInt(id));

        if(!extension.getOwner().equals(user.getUsername())){                         // checks if the logged user is the owner of the extension
            return "redirect:access-denied";
        }

        adminService.deleteExtension(extension.getId());

        return "redirect:/profile";
    }
}
