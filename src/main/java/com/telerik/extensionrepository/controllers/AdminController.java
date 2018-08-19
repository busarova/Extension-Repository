package com.telerik.extensionrepository.controllers;

import com.telerik.extensionrepository.service.base.ExtensionOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    private ExtensionOrderService extensionOrderService;

    @Autowired
    public AdminController(ExtensionOrderService extensionOrderService){
        this.extensionOrderService = extensionOrderService;
    }

    @GetMapping("/admin")
    public String showAdminPage(){

        return "admin";
    }

}
