package com.telerik.extensionrepository.data.base;

import com.telerik.extensionrepository.model.Extension;
import com.telerik.extensionrepository.model.GitExtensionInfo;

import java.util.List;

public interface ExtensionRepository {

    List<Extension> getAllExtensions();

    List<Extension> getAllApproved();

    int createGithub_info(GitExtensionInfo gitExtensionInfo);

    void createExtension(Extension extension);

    List<Extension> getFeaturedExtensions();

    List<Extension> getPopularExtensions();

    List<Extension> getNewExtensions();

    List<Extension> getByUserName(String userName);

    Extension getExtByName(String name);

    Extension getExtById(int id);

    void changeFileId(Extension extension, int newId);

    void registerDownload(Extension extension);
}
