package com.telerik.extensionrepository.rest_controllers;


import com.telerik.extensionrepository.service.base.ExtensionInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestHomeController {

    private ExtensionInfoService extensionInfoService;

    @Autowired
    public RestHomeController(ExtensionInfoService extensionInfoService){
        this.extensionInfoService = extensionInfoService;
    }

    @GetMapping("/api/index")
    public String showHomePage(){

        return "restIndex";
    }
}
