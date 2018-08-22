package com.telerik.extensionrepository.service.base;

import com.telerik.extensionrepository.model.Extension;
import com.telerik.extensionrepository.model.base.ExtensionForm;

public interface ExtensionService {

    void createExtension(ExtensionForm extensionForm);

    int createGitExtentionInfo(ExtensionForm extensionForm);

    void changeExtensionFileId(Extension extension, int id);

}
