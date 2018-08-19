package com.telerik.extensionrepository.controllers;

import com.telerik.extensionrepository.model.Extension;
import com.telerik.extensionrepository.model.base.ExtensionForm;
import com.telerik.extensionrepository.service.base.ExtensionOrderService;
import com.telerik.extensionrepository.service.base.ExtensionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserController {

    private ExtensionOrderService extensionOrderService;
    private ExtensionService extensionService;

    @Autowired
    public UserController(ExtensionOrderService extensionOrderService, ExtensionService extensionService){
        this.extensionOrderService = extensionOrderService;
        this.extensionService = extensionService;
    }

    /*@GetMapping("/profile")
    public String showUserPage(){

        return "profile";
    }*/

    @GetMapping("/accessDenied")
    public String accessDenied(){

        return "access-denied";
    }

    @GetMapping("/profile")
    public ModelAndView showUserPage(){

        User user = (User) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();

        ModelAndView modelAndView = new ModelAndView("/profile");

        modelAndView.addObject("extensions", extensionOrderService.getByUserName(user.getUsername()));

        return modelAndView;

    }

    @GetMapping("/user/create-extension")
    public ModelAndView showCreateExtentionPage(){

        ModelAndView modelAndView = new ModelAndView("create-extension");

        modelAndView.addObject("extension", new ExtensionForm());

        return modelAndView;
    }

    @PostMapping("/user/create-extension")
    public String createExtension(ExtensionForm extension){

        System.out.println(extension.getDescription());
        System.out.println(extension.getName());

        extensionService.createExtension(extension);

        return "index";

    }
}
