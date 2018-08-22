package com.telerik.extensionrepository.service.base;

import com.telerik.extensionrepository.model.UploadFile;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    void storeFile(UploadFile uploadFile);
}
