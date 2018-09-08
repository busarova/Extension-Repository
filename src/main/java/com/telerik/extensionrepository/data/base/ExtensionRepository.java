package com.telerik.extensionrepository.data.base;

import com.telerik.extensionrepository.model.Extension;
import com.telerik.extensionrepository.model.GitExtensionInfo;
import com.telerik.extensionrepository.utils.exceptions.RepositoryException;

import java.util.List;

public interface ExtensionRepository {

    List<Extension> getAllExtensions() throws RepositoryException;

    List<Extension> getAllApproved();

    List<Extension> getAllNotApproved();

    Extension updateExtension(Extension extension);

    Extension createExtension(Extension extension);

    List<Extension> getFeaturedExtensions();

    List<Extension> getPopularExtensions();

    List<Extension> getNewExtensions();

    List<Extension> getByUserName(String userName);

    Extension getExtByName(String name);

    Extension getExtById(int id);

    Extension deleteExtension(Extension extension);

    List<Extension> getAllByParam(String param);

    public List<Extension> searchByDownloads(String param);

    public List<Extension> searchByUploadDate(String param);

    public List<Extension> searchByLastCommitDate(String param);
}
