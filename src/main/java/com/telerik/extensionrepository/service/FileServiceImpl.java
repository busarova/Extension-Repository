package com.telerik.extensionrepository.service;

import com.telerik.extensionrepository.data.base.FileRepository;
import com.telerik.extensionrepository.model.UploadFile;
import com.telerik.extensionrepository.service.base.FileService;
import org.apache.tomcat.jni.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@Service
public class FileServiceImpl implements FileService {

    private FileRepository fileRepository;

    @Autowired
    public FileServiceImpl(FileRepository fileRepository){
        this.fileRepository = fileRepository;
    }

    @Override
    public void storeFile(UploadFile uploadFile) {

        fileRepository.saveUploadFile(uploadFile);

    }
}
