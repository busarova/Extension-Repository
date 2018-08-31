package com.telerik.extensionrepository.controllers;

import com.telerik.extensionrepository.model.Extension;
import com.telerik.extensionrepository.model.UploadFile;
import com.telerik.extensionrepository.model.User;
import com.telerik.extensionrepository.service.base.AdminService;
import com.telerik.extensionrepository.service.base.ExtensionInfoService;
import com.telerik.extensionrepository.service.base.ExtensionService;
import com.telerik.extensionrepository.service.base.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Base64;
import java.util.List;

@Controller
public class FileController {

    private FileService fileService;
    private ExtensionService extensionService;
    private ExtensionInfoService extensionInfoService;
    private AdminService adminService;

    @Autowired
    public FileController(FileService fileService, ExtensionService extensionService, ExtensionInfoService extensionInfoService, AdminService adminService){
        this.fileService = fileService;
        this.extensionService = extensionService;
        this.extensionInfoService = extensionInfoService;
        this.adminService = adminService;
    }

    @GetMapping("/edit-file/{name}")
    public ModelAndView editFileReroute(@PathVariable("name") String name){

        ModelAndView modelAndView = new ModelAndView("/upload-file");
        modelAndView.addObject("extensionForm", extensionInfoService.getExtByName(name));

        return modelAndView;
    }

    @GetMapping("/upload-file")
    public ModelAndView fileUploadPage(ModelAndView modelAndView){

        return modelAndView;
    }


    @PostMapping("/doUpload/{name}")
    public ModelAndView handleFileUpload(HttpServletRequest request, @PathVariable("name") String name,
                                   @RequestParam CommonsMultipartFile[] fileUpload) throws Exception {


        if (fileUpload != null && fileUpload.length > 0) {
            for (CommonsMultipartFile aFile : fileUpload){

                System.out.println("Saving file: " + aFile.getOriginalFilename());

                Extension extension = extensionInfoService.getExtByName(name);

                UploadFile uploadFile = extension.getUploadFile();
                uploadFile.setFileName(aFile.getOriginalFilename());
                uploadFile.setData(aFile.getBytes());
                fileService.storeFile(uploadFile);




                extensionService.updateExtension(extension);

                adminService.removeApproval(extension.getId());
            }

        }

        ModelAndView modelAndView = new ModelAndView("index");

        List<com.telerik.extensionrepository.model.Extension> featured = extensionInfoService.getFeatured();

        modelAndView.addObject("featured", featured);

        List<com.telerik.extensionrepository.model.Extension> popular = extensionInfoService.getPopular();

        modelAndView.addObject("popular", popular);

        List<com.telerik.extensionrepository.model.Extension> newExt = extensionInfoService.getNew();

        modelAndView.addObject("newExt", newExt);

        return modelAndView;
    }


    @RequestMapping(value = "/download/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public @ResponseBody void downloadA(HttpServletResponse response, @PathVariable("id")
            String id) throws IOException {

        Extension extension = extensionInfoService.getById(Integer.parseInt(id));

        int fileId = extension.getUploadFile().getId();

        UploadFile uploadFile = fileService.getFile(fileId);


        ByteArrayInputStream inputStream = new ByteArrayInputStream(uploadFile.getData());

            //   response.setContentType(APPLICATION_PDF);
        response.setHeader("Content-Disposition", "attachment; filename=" + uploadFile.getFileName());
            //   response.setHeader("Content-Length", String.valueOf(file.length()));
        FileCopyUtils.copy(inputStream, response.getOutputStream());

        extensionService.registerDownload(extension);


    }


    @PostMapping("/user/changePic")
    public ModelAndView uploadProfilePic(HttpServletRequest request,@RequestParam CommonsMultipartFile[] fileUpload) throws Exception {

        ModelAndView modelAndView = new ModelAndView("profile");

        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();

        if (fileUpload != null && fileUpload.length > 0) {
            for (CommonsMultipartFile aFile : fileUpload) {

                //   Extension extension = extensionInfoService.getExtByName(name);

                User currentUser = adminService.getUser(user.getUsername());

                currentUser.setData(aFile.getBytes());

                fileService.storeProfilePic(currentUser);

            }

        }

        modelAndView.addObject("extensions", extensionInfoService.getByUserName(user.getUsername()));

        return modelAndView;

    }

    @PostMapping("/user/changeExtensionLogo/{id}")
    public String uploadLogo(HttpServletRequest request,@RequestParam CommonsMultipartFile[] fileUpload, @PathVariable("id")
            String id){

        if (fileUpload != null && fileUpload.length > 0) {
            for (CommonsMultipartFile aFile : fileUpload) {

                Extension currentExtension = extensionInfoService.getById(Integer.parseInt(id));

                UploadFile uploadFile = currentExtension.getUploadFile();

                uploadFile.setLogoData(aFile.getBytes());


                fileService.updateFile(uploadFile);

                adminService.removeApproval(Integer.parseInt(id));

            }

        }

        return "redirect:/extension-details/"+id;

    }



    /*@RequestMapping(value = "/download/{name}",
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public String download(@PathVariable("name")
                                   String name, HttpServletResponse response) {

        int fileId = extensionInfoService.getExtByName(name).getFileId();

        System.out.println("THE ID IS: " + fileId);

        UploadFile file = fileService.getFile(fileId);

        System.out.println(file.getFileName());

        ByteArrayInputStream array = new ByteArrayInputStream(file.getData());

        try {
            response.setHeader("Content-Disposition", "inline;filename=\"" + file.getFileName()+ "\"");
            OutputStream out = response.getOutputStream();
         //   response.setContentType(file.getContentType());
            IOUtils.copy(array, out);
            out.flush();
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }*/


}
