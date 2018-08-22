package com.telerik.extensionrepository.controllers;

import com.telerik.extensionrepository.model.Extension;
import com.telerik.extensionrepository.service.base.AdminService;
import com.telerik.extensionrepository.service.base.ExtensionOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {

    private ExtensionOrderService extensionOrderService;
    private AdminService adminService;

    @Autowired
    public AdminController(ExtensionOrderService extensionOrderService, AdminService adminService){
        this.extensionOrderService = extensionOrderService;
        this.adminService = adminService;
    }

    @GetMapping("/admin")
    public ModelAndView showAdminPage(){

        ModelAndView modelAndView = new ModelAndView("admin");

        modelAndView.addObject("extensions", adminService.getUnnaprovedExt());
        modelAndView.addObject("users", adminService.getAllUsers());

        return modelAndView;
    }

    @RequestMapping("/admin/approve/{id}")
    public ModelAndView getById(@PathVariable("id") String id){

        ModelAndView modelAndView = new ModelAndView("admin");

        adminService.approveExt(Integer.parseInt(id));

        modelAndView.addObject("extensions", adminService.getUnnaprovedExt());
        modelAndView.addObject("users", adminService.getAllUsers());


        return modelAndView;
    }

    @RequestMapping("/admin/disable-user/{name}")
    public ModelAndView disableUser(@PathVariable("name") String name){

        ModelAndView modelAndView = new ModelAndView("admin");

        adminService.disableUser(name);

        modelAndView.addObject("extensions", adminService.getUnnaprovedExt());
        modelAndView.addObject("users", adminService.getAllUsers());

        return modelAndView;
    }

    @RequestMapping("/admin/enable-user/{name}")
    public ModelAndView enableUser(@PathVariable("name") String name){

        ModelAndView modelAndView = new ModelAndView("admin");

        adminService.enableUser(name);

        modelAndView.addObject("extensions", adminService.getUnnaprovedExt());
        modelAndView.addObject("users", adminService.getAllUsers());

        return modelAndView;
    }

    @RequestMapping("/admin/feature/{Name}")
    public ModelAndView featureExtension(@PathVariable("Name") String name){

        adminService.featureExtension(name);

        ModelAndView modelAndView = new ModelAndView("redirect:/extension-details/" + name);



        return modelAndView;
    }

    @RequestMapping("/admin/un-feature/{Name}")
    public ModelAndView unFeatureExtension(@PathVariable("Name") String name){

        adminService.unFeatureExtension(name);

        ModelAndView modelAndView = new ModelAndView("redirect:/extension-details/" + name);

        return modelAndView;
    }

    @RequestMapping("/admin/delete-extension/{Name}")
    public String deleteExtension(@PathVariable("Name") String name){


        adminService.deleteExtension(name);

        return "redirect:/admin";
    }

}
