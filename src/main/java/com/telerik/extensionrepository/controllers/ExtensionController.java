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

import javax.persistence.criteria.CriteriaBuilder;
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

    @RequestMapping("/extension-details/{id}")
    public ModelAndView getById(@PathVariable("id") String id){

        ModelAndView modelAndView = new ModelAndView("extension-details");

        Extension extension = extensionInfoService.getById(Integer.parseInt(id));

        System.out.println("currently reviewing: " + extension.getName());

        modelAndView.addObject("extension", extension);

        return modelAndView;
    }

    @RequestMapping("/edit-extension/{id}")
    public ModelAndView editExtension(@PathVariable("id") String id){

        ModelAndView modelAndView = new ModelAndView("edit-extension");

        modelAndView.addObject(extensionInfoService.getById(Integer.parseInt(id)));

        return modelAndView;
    }


    @RequestMapping("/sortby/{name}")
    public ModelAndView sortByParam(@PathVariable("name") String name){

        ModelAndView modelAndView = new ModelAndView("index");

       // List<Extension> list = extensionInfoService.returnOrderedBy(name);

        List<Extension> list = extensionInfoService.returnAllOrderedBy(name);


        modelAndView.addObject("allApproved", list);

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
