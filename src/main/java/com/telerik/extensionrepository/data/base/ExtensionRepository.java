package com.telerik.extensionrepository.data.base;

import com.telerik.extensionrepository.model.Extension;
import com.telerik.extensionrepository.model.GitExtensionInfo;
import com.telerik.extensionrepository.model.base.ExtensionForm;

import java.util.List;

public interface ExtensionRepository {

    List<Extension> getAllExtensions();

    int createGithub_info(GitExtensionInfo gitExtensionInfo);

    void createExtension(Extension extension);

    List<Extension> getFeaturedExtensions();

    List<Extension> getPopularExtensions();

    List<Extension> getNewExtensions();

    List<Extension> getByUserName(String userName);

    Extension getExtByName(String name);
}
