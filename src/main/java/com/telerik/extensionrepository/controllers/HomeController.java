package com.telerik.extensionrepository.controllers;

import com.telerik.extensionrepository.model.Extension;
import com.telerik.extensionrepository.service.base.AdminService;
import com.telerik.extensionrepository.service.base.ExtensionInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Base64;
import java.util.List;

@Controller
public class HomeController {

    private ExtensionInfoService extensionInfoService;
    private AdminService adminService;

    @Autowired
    public HomeController(ExtensionInfoService extensionInfoService, AdminService adminService){
        this.extensionInfoService = extensionInfoService;
        this.adminService = adminService;
    }

    @GetMapping("/")
    public ModelAndView showAll(){
        ModelAndView modelAndView = new ModelAndView("index");

        System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());


        if(!SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {

            User user = (User) SecurityContextHolder.getContext().getAuthentication()
                    .getPrincipal();

            com.telerik.extensionrepository.model.User userModel = adminService.getUser(user.getUsername());

            if (userModel.getData() == null) {
                modelAndView.addObject("image", null);
                return modelAndView;
            }

            modelAndView.addObject("image", Base64.getEncoder().encodeToString(userModel.getData()));

        }


        modelAndView.addObject("allApproved", extensionInfoService.getAllApproved());


        return modelAndView;
    }

    @GetMapping("/featured")
    public ModelAndView showFeatured(){

        ModelAndView modelAndView = new ModelAndView("index");

        modelAndView.addObject("allApproved", extensionInfoService.getFeatured());

        return modelAndView;
    }


}
