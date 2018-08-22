package com.telerik.extensionrepository.service.base;

import com.telerik.extensionrepository.model.UploadFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface FileService {

    void storeFile(UploadFile uploadFile);

    UploadFile getFile(int id);
}
