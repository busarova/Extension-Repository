package com.telerik.extensionrepository.service.base;

import com.telerik.extensionrepository.model.Extension;
import com.telerik.extensionrepository.dto.ExtensionForm;

public interface ExtensionService {

    Extension createExtension(ExtensionForm extensionForm);

    Extension updateExtension(Extension extension);

    Extension changeExtensionName(Extension extension, String newName);

    Extension registerDownload(Extension extension);

    Extension changeExtensionDescription(Extension extension, String newDescription);

    void addExtensionTag(Extension extension, String newTags);


}
