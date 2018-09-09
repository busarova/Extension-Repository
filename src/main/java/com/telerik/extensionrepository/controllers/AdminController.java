package com.telerik.extensionrepository.controllers;

import com.telerik.extensionrepository.exceptions.GithubSyncException;
import com.telerik.extensionrepository.service.base.AdminService;
import com.telerik.extensionrepository.exceptions.RepositoryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {

    private AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService){
        this.adminService = adminService;
    }

    @GetMapping("/admin")
    public ModelAndView showAdminPage(){

        ModelAndView modelAndView = new ModelAndView("admin");

        modelAndView.addObject("extensions", adminService.getNotApprovedExt());
        modelAndView.addObject("users", adminService.getAllUsers());
        modelAndView.addObject("adminInfo", adminService.getAdminInfo());

        return modelAndView;
    }

    @GetMapping("/admin/refreshGit")
    public ModelAndView refreshGitInfo(){

        ModelAndView modelAndView = new ModelAndView("admin");

        try {

            adminService.refreshAllGitHubInfo();

        }catch(RepositoryException rep){
            modelAndView.setViewName("error");
            modelAndView.addObject("error", rep.getMessage());

            return modelAndView;
        }catch (GithubSyncException git){
            modelAndView.setViewName("error");
            modelAndView.addObject("error", git.getMessage());
        }

        modelAndView.addObject("extensions", adminService.getNotApprovedExt());
        modelAndView.addObject("users", adminService.getAllUsers());
        modelAndView.addObject("adminInfo", adminService.getAdminInfo());

        return modelAndView;
    }

    @GetMapping("/admin/cleanEmptyTags")
    public ModelAndView emptyTagCheck(){

        ModelAndView modelAndView = new ModelAndView("admin");

        modelAndView.addObject("extensions", adminService.getNotApprovedExt());
        modelAndView.addObject("users", adminService.getAllUsers());
        modelAndView.addObject("adminInfo", adminService.getAdminInfo());
        modelAndView.addObject("tagsCleaned", adminService.emptyTagCheck());

        return modelAndView;
    }

    @RequestMapping("/admin/approve/{id}")
    public ModelAndView getById(@PathVariable("id") String id){

        ModelAndView modelAndView = new ModelAndView("admin");

        adminService.approveExt(Integer.parseInt(id));

        modelAndView.addObject("extensions", adminService.getNotApprovedExt());
        modelAndView.addObject("users", adminService.getAllUsers());
        modelAndView.addObject("adminInfo", adminService.getAdminInfo());


        return modelAndView;
    }

    @RequestMapping("/admin/disable-user/{name}")
    public ModelAndView disableUser(@PathVariable("name") String name){

        ModelAndView modelAndView = new ModelAndView("admin");

        adminService.disableUser(name);

        modelAndView.addObject("extensions", adminService.getNotApprovedExt());
        modelAndView.addObject("users", adminService.getAllUsers());
        modelAndView.addObject("adminInfo", adminService.getAdminInfo());

        return modelAndView;
    }

    @RequestMapping("/admin/enable-user/{name}")
    public ModelAndView enableUser(@PathVariable("name") String name){

        ModelAndView modelAndView = new ModelAndView("admin");

        adminService.enableUser(name);

        modelAndView.addObject("extensions", adminService.getNotApprovedExt());
        modelAndView.addObject("users", adminService.getAllUsers());
        modelAndView.addObject("adminInfo", adminService.getAdminInfo());

        return modelAndView;
    }

    @RequestMapping("/admin/feature/{id}")
    public ModelAndView featureExtension(@PathVariable("id") String id){

        adminService.featureExtension(Integer.parseInt(id));

        ModelAndView modelAndView = new ModelAndView("redirect:/extension-details/" + id);

        return modelAndView;
    }

    @RequestMapping("/admin/un-feature/{id}")
    public ModelAndView unFeatureExtension(@PathVariable("id") String id){

        adminService.unFeatureExtension(Integer.parseInt(id));

        ModelAndView modelAndView = new ModelAndView("redirect:/extension-details/" + id);

        return modelAndView;
    }

    @RequestMapping("/admin/delete-extension/{id}")
    public String deleteExtension(@PathVariable("Name") String id){


        adminService.deleteExtension(Integer.parseInt(id));

        return "redirect:/admin";
    }

    @RequestMapping("/admin/refreshGit/{id}")
    public String refreshGit(@PathVariable("id") String id){

        adminService.refreshExtensionGitInfo(Integer.parseInt(id));

        return "redirect:/extension-details/"+id;
    }

}
