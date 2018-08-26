package com.telerik.extensionrepository.controllers;

import com.telerik.extensionrepository.dto.ExtensionForm;
import com.telerik.extensionrepository.model.Extension;
import com.telerik.extensionrepository.service.base.ExtensionInfoService;
import com.telerik.extensionrepository.service.base.ExtensionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {

    private ExtensionInfoService extensionInfoService;
    private ExtensionService extensionService;

    @Autowired
    public UserController(ExtensionInfoService extensionInfoService, ExtensionService extensionService){
        this.extensionInfoService = extensionInfoService;
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

        modelAndView.addObject("extensions", extensionInfoService.getByUserName(user.getUsername()));

        return modelAndView;

    }

    @GetMapping("/user/create-extension")
    public ModelAndView showCreateExtentionPage(){

        ModelAndView modelAndView = new ModelAndView("create-extension");

        modelAndView.addObject("extensionForm", new ExtensionForm());

        return modelAndView;
    }

    @PostMapping("/user/create-extension")
    public ModelAndView createExtension(@ModelAttribute ("extensionForm") @Valid ExtensionForm extension, Errors errors){

        if(errors.hasErrors()){

            ModelAndView modelAndView = new ModelAndView("create-extension");
            modelAndView.addObject("errors", errors);

            return modelAndView;

        }


        extensionService.createExtension(extension);

        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("allApproved", extensionInfoService.getAllApproved());
        modelAndView.addObject("extensionForm", extension);

        return modelAndView;

    }

    @RequestMapping("/user/sortby/{sortType}")
    public ModelAndView sortByParam(@PathVariable("sortType") String sortType){

        User user = (User) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();

        ModelAndView modelAndView = new ModelAndView("/profile");

        List<Extension> list = extensionInfoService.getByUserName(user.getUsername());

        extensionInfoService.sortListBy(list, sortType);

        modelAndView.addObject("extensions", list);

        return modelAndView;
    }
}
