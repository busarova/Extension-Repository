package com.telerik.extensionrepository.controllers;

import com.telerik.extensionrepository.exceptions.RepositoryException;
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


        try {
            modelAndView.addObject("allApproved", extensionInfoService.getAllApproved());
        }catch (RepositoryException rep){
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", rep.getMessage());
        }


        return modelAndView;
    }

    @GetMapping("/featured")
    public ModelAndView showFeatured(){

        ModelAndView modelAndView = new ModelAndView("index");

        modelAndView.addObject("allApproved", extensionInfoService.getFeatured());

        return modelAndView;
    }


}
