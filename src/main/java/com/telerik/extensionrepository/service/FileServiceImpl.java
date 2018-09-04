package com.telerik.extensionrepository.service;

import com.telerik.extensionrepository.data.base.FileRepository;
import com.telerik.extensionrepository.model.Extension;
import com.telerik.extensionrepository.model.UploadFile;
import com.telerik.extensionrepository.model.User;
import com.telerik.extensionrepository.service.base.AdminService;
import com.telerik.extensionrepository.service.base.ExtensionService;
import com.telerik.extensionrepository.service.base.FileService;
import org.apache.tomcat.jni.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


@Service
public class FileServiceImpl implements FileService {

    private FileRepository fileRepository;
    private ExtensionService extensionService;
    private AdminService adminService;

    @Autowired
    public FileServiceImpl(FileRepository fileRepository, ExtensionService extensionService, AdminService adminService){
        this.fileRepository = fileRepository;
        this.extensionService = extensionService;
        this.adminService = adminService;
    }

    @Override
    public void storeFile(Extension extension, MultipartFile aFile) throws Exception {

        extension.getUploadFile().setFileName(aFile.getOriginalFilename());
        extension.getUploadFile().setData(aFile.getBytes());

        extensionService.updateExtension(extension);

        adminService.removeApproval(extension.getId());

    }

    @Override
    public void storeProfilePic(User user) {
        fileRepository.saveProfilePic(user);
    }

    @Override
    public UploadFile getFile(int id) {

        return fileRepository.getFile(id);
    }

    @Override
    public void updateFile(UploadFile uploadFile) {
        fileRepository.updateUploadFile(uploadFile);
    }
}
