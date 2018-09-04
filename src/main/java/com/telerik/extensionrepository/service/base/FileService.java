package com.telerik.extensionrepository.service.base;

import com.telerik.extensionrepository.model.Extension;
import com.telerik.extensionrepository.model.UploadFile;
import com.telerik.extensionrepository.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface FileService {

    void storeFile(Extension extension, MultipartFile file) throws Exception;

    void storeProfilePic(User user);

    UploadFile getFile(int id);

    void updateFile(UploadFile uploadFile);

}
