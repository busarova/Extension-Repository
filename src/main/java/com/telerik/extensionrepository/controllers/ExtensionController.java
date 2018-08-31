package com.telerik.extensionrepository.controllers;

import com.telerik.extensionrepository.model.Extension;
import com.telerik.extensionrepository.service.base.AdminService;
import com.telerik.extensionrepository.service.base.ExtensionInfoService;
import com.telerik.extensionrepository.service.base.ExtensionService;
import com.telerik.extensionrepository.service.base.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.CriteriaBuilder;
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

        System.out.println("currently reviewing: " + extension.getName());

        modelAndView.addObject("extension", extension);

        modelAndView.addObject("tags", tagService.extractTagsFromExtension(extension));

        if(extension.getUploadFile().getLogoData() != null) {
            modelAndView.addObject("logo", Base64.getEncoder().encodeToString(extension.getUploadFile().getLogoData()));
        }

        return modelAndView;
    }

    @RequestMapping("/edit-extension/{id}")
    public ModelAndView editExtension(@PathVariable("id") String id){

        ModelAndView modelAndView = new ModelAndView("edit-extension");

        modelAndView.addObject(extensionInfoService.getById(Integer.parseInt(id)));

        modelAndView.addObject("name", 0);
        modelAndView.addObject("description", 0);
        modelAndView.addObject("tags", 0);

        return modelAndView;
    }

    // Method reloads page with name parameter "on", so that html will show change name text form

    @RequestMapping("/user/edit-extension/name/{id}")
    public ModelAndView editExtensionName(@PathVariable("id") String id){

        ModelAndView modelAndView = new ModelAndView("edit-extension");

        modelAndView.addObject(extensionInfoService.getById(Integer.parseInt(id)));

        modelAndView.addObject("name", 1);
        modelAndView.addObject("description", 0);
        modelAndView.addObject("tags", 0);

        return modelAndView;
    }

    //Method changes the name of the Extension
    //Removes the approval so that admin can review the name

    @PostMapping("/user/edit-extension/name/change/{id}")
    public ModelAndView editExtensionNameChange(@RequestParam String name, @PathVariable("id") String id){

        ModelAndView modelAndView = new ModelAndView("edit-extension");

        Extension extension = extensionInfoService.getById(Integer.parseInt(id));

        extensionService.changeExtensionName(extension, name);

        adminService.removeApproval(extension.getId());

        modelAndView.addObject(extension);

        return modelAndView;
    }

    @PostMapping("/user/edit-extension/descr/change/{id}")
    public ModelAndView editExtensionDescription(@RequestParam String content, @PathVariable("id") String id){

        ModelAndView modelAndView = new ModelAndView("edit-extension");

        Extension extension = extensionInfoService.getById(Integer.parseInt(id));

        extensionService.changeExtensionDescription(extension, content);

        adminService.removeApproval(extension.getId());

        modelAndView.addObject(extension);

        return modelAndView;
    }

    @PostMapping("/user/edit-extension/tag/change/{id}")
    public ModelAndView editExtensionTag(@RequestParam String content, @PathVariable("id") String id){

        ModelAndView modelAndView = new ModelAndView("edit-extension");

        Extension extension = extensionInfoService.getById(Integer.parseInt(id));

        extensionService.addExtensionTag(extension, content);

        adminService.removeApproval(extension.getId());

        modelAndView.addObject(extension);

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
