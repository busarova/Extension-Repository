package com.telerik.extensionrepository.rest_controllers;


import com.telerik.extensionrepository.dto.ExtensionDTO;
import com.telerik.extensionrepository.service.base.ExtensionInfoService;
import com.telerik.extensionrepository.service.base.RestExtensionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestHomeController {

    private ExtensionInfoService extensionInfoService;
    private RestExtensionService restExtensionService;

    @Autowired
    public RestHomeController(ExtensionInfoService extensionInfoService, RestExtensionService restExtensionService){
        this.extensionInfoService = extensionInfoService;
        this.restExtensionService = restExtensionService;
    }

    @GetMapping("/api/index")
    public String showHomePage(){

        return "restIndex";
    }

    @GetMapping("/api/getAllApproved")
    public List<ExtensionDTO> getAllApproved(){

        return restExtensionService.getAllApproved();

    }
}
