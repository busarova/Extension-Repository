package com.telerik.extensionrepository.data.base;

import com.telerik.extensionrepository.model.UploadFile;
import com.telerik.extensionrepository.model.User;

public interface FileRepository {

    void saveUploadFile(UploadFile uploadFile);

    void updateUploadFile(UploadFile uploadFile);

    void saveProfilePic(User user);

    UploadFile getFile(int id);



}
