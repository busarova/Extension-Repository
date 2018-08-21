package com.telerik.extensionrepository.rest_controllers;


import com.telerik.extensionrepository.service.base.ExtensionOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestHomeController {

    private ExtensionOrderService extensionOrderService;

    @Autowired
    public RestHomeController(ExtensionOrderService extensionOrderService){
        this.extensionOrderService = extensionOrderService;
    }

    @GetMapping("/api/index")
    public String showHomePage(){

        return "restIndex";
    }
}
