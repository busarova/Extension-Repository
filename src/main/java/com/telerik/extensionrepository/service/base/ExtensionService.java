package com.telerik.extensionrepository.service.base;

import com.telerik.extensionrepository.model.Extension;
import com.telerik.extensionrepository.dto.ExtensionForm;
import org.springframework.security.core.userdetails.User;

public interface ExtensionService {

    Extension createExtension(ExtensionForm extensionForm, User user);

    Extension updateExtension(Extension extension);

    Extension changeExtensionName(Extension extension, String newName);

    Extension registerDownload(Extension extension);

    Extension changeExtensionDescription(Extension extension, String newDescription);

    Extension addExtensionTag(Extension extension, String newTags);

    Extension createExtensionFromForm(ExtensionForm form, User user);

}
