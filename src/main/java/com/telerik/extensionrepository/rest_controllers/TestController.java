package com.telerik.extensionrepository.rest_controllers;

import com.telerik.extensionrepository.model.Extension;
import com.telerik.extensionrepository.service.base.ExtensionInfoService;
import com.telerik.extensionrepository.exceptions.RepositoryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    private ExtensionInfoService extensionInfoService;

    @Autowired
    public TestController(ExtensionInfoService extensionInfoService){
        this.extensionInfoService = extensionInfoService;
    }

    @GetMapping("/test")
    public List<Extension> test() throws RepositoryException{

            return extensionInfoService.getAll();

    }
}
