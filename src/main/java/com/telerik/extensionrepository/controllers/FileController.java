package com.telerik.extensionrepository.controllers;



import com.telerik.extensionrepository.model.UploadFile;
import com.telerik.extensionrepository.model.base.ExtensionForm;
import com.telerik.extensionrepository.service.base.ExtensionInfoService;
import com.telerik.extensionrepository.service.base.ExtensionService;
import com.telerik.extensionrepository.service.base.FileService;
import org.apache.commons.fileupload.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.security.cert.Extension;

@Controller
public class FileController {

    private FileService fileService;
    private ExtensionService extensionService;
    private ExtensionInfoService extensionInfoService;

    @Autowired
    public FileController(FileService fileService, ExtensionService extensionService, ExtensionInfoService extensionInfoService){
        this.fileService = fileService;
        this.extensionService = extensionService;
        this.extensionInfoService = extensionInfoService;
    }

    @GetMapping("/upload-file")
    public ModelAndView fileUploadPage(ModelAndView modelAndView){

        return modelAndView;
    }


    @PostMapping("/doUpload/{name}")
    public String handleFileUpload(HttpServletRequest request, @PathVariable("name") String name,
                                   @RequestParam CommonsMultipartFile[] fileUpload) throws Exception {


        if (fileUpload != null && fileUpload.length > 0) {
            for (CommonsMultipartFile aFile : fileUpload){

                System.out.println("Saving file: " + aFile.getOriginalFilename());

                UploadFile uploadFile = new UploadFile();
                uploadFile.setFileName(aFile.getOriginalFilename());
                uploadFile.setData(aFile.getBytes());
                fileService.storeFile(uploadFile);

                extensionService.changeExtensionFileId(extensionInfoService.getExtByName(name), uploadFile.getId());
            }

        }




        return "index";
    }

}
