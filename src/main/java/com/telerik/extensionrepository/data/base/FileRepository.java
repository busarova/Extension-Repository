package com.telerik.extensionrepository.data.base;

import com.telerik.extensionrepository.model.UploadFile;

public interface FileRepository {

    void saveUploadFile(UploadFile uploadFile);

    UploadFile getFile(int id);

}
