package com.telerik.extensionrepository.controllers;

import com.telerik.extensionrepository.model.Extension;
import com.telerik.extensionrepository.service.base.AdminService;
import com.telerik.extensionrepository.service.base.ExtensionInfoService;
import com.telerik.extensionrepository.service.base.ExtensionService;
import com.telerik.extensionrepository.service.base.TagService;
import com.telerik.extensionrepository.exceptions.RepositoryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Base64;
import java.util.List;

@Controller
public class ExtensionController {

    private ExtensionInfoService extensionInfoService;
    private AdminService adminService;
    private ExtensionService extensionService;
    private TagService tagService;

    @Autowired
    public ExtensionController(ExtensionInfoService extensionInfoService, AdminService adminService, ExtensionService extensionService, TagService tagService){
        this.extensionInfoService = extensionInfoService;
        this.adminService = adminService;
        this.extensionService = extensionService;
        this.tagService = tagService;
    }

    @RequestMapping("/extension-details/{id}")
    public ModelAndView getById(@PathVariable("id") String id){

        ModelAndView modelAndView = new ModelAndView("extension-details");

        Extension extension = extensionInfoService.getById(Integer.parseInt(id));

        modelAndView.addObject("extension", extension);

        modelAndView.addObject("tags", extension.getTags());

        if(extension.getUploadFile().getLogoData() != null) {
            modelAndView.addObject("logo", Base64.getEncoder().encodeToString(extension.getUploadFile().getLogoData()));
        }

        return modelAndView;
    }

    @RequestMapping("/edit-extension/{id}")
    public ModelAndView editExtension(@PathVariable("id") String id){

        ModelAndView modelAndView = new ModelAndView("edit-extension");

        Extension extension = extensionInfoService.getById(Integer.parseInt(id));

        modelAndView.addObject("tags", extension.getTags());

        modelAndView.addObject(extension);

        if(extension.getUploadFile().getLogoData() != null) {
            modelAndView.addObject("logo", Base64.getEncoder().encodeToString(extension.getUploadFile().getLogoData()));
        }

        return modelAndView;
    }

    // Method reloads page with name parameter "on", so that html will show change name text form

    @RequestMapping("/user/edit-extension/name/{id}")
    public ModelAndView editExtensionName(@PathVariable("id") String id){

        ModelAndView modelAndView = new ModelAndView("edit-extension");

        modelAndView.addObject(extensionInfoService.getById(Integer.parseInt(id)));

        return modelAndView;
    }

    //Method changes the name of the Extension
    //Removes the approval so that admin can review the name

    @PostMapping("/user/edit-extension/name/change/{id}")
    public ModelAndView editExtensionNameChange(@RequestParam String name, @PathVariable("id") String id){

        ModelAndView modelAndView = new ModelAndView("edit-extension");

        Extension extension = extensionInfoService.getById(Integer.parseInt(id));

        extensionService.changeExtensionName(extension, name);

        modelAndView.addObject(extension);

        return modelAndView;
    }

    @PostMapping("/user/edit-extension/descr/change/{id}")
    public ModelAndView editExtensionDescription(@RequestParam String content, @PathVariable("id") String id){

        ModelAndView modelAndView = new ModelAndView("edit-extension");

        Extension extension = extensionInfoService.getById(Integer.parseInt(id));

        extensionService.changeExtensionDescription(extension, content);

        modelAndView.addObject(extension);

        return modelAndView;
    }

    @PostMapping("/user/edit-extension/tag/change/{id}")
    public ModelAndView editExtensionTag(@RequestParam String content, @PathVariable("id") String id){

        ModelAndView modelAndView = new ModelAndView("edit-extension");

        Extension extension = extensionInfoService.getById(Integer.parseInt(id));

        modelAndView.addObject(extensionService.addExtensionTag(extension, content));

        return modelAndView;
    }


    @RequestMapping("/sortby/{name}")
    public ModelAndView sortByParam(@PathVariable("name") String name){

        ModelAndView modelAndView = new ModelAndView("index");

        try {


            List<Extension> list = extensionInfoService.returnAllOrderedBy(name);

            modelAndView.addObject("allApproved", list);

        }catch(RepositoryException rep){

            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", rep);
        }


        return modelAndView;
    }

    @RequestMapping("/delete-extension/{id}")
    public String deleteExtension(@PathVariable("id") String id){

        User user = (User) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();

        Extension extension = extensionInfoService.getById(Integer.parseInt(id));

        // checks if the logged user is the owner of the extension or if he is Admin

        if(!extension.getOwner().equals(user.getUsername()) && !user.getAuthorities().toString().contains("ROLE_ADMIN")){
            return "redirect:access-denied";
        }

        adminService.deleteExtension(extension.getId());

        return "redirect:/profile";
    }

}
