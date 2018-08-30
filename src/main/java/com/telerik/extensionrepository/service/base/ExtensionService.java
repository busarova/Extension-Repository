package com.telerik.extensionrepository.service.base;

import com.telerik.extensionrepository.model.Extension;
import com.telerik.extensionrepository.dto.ExtensionForm;

public interface ExtensionService {

    void createExtension(ExtensionForm extensionForm);

    void updateExtension(Extension extension);

    void changeExtensionName(Extension extension, String newName);

    void registerDownload(Extension extension);

    void changeExtensionDescription(Extension extension, String newDescription);

    void addExtensionTag(Extension extension, String newTags);


}
