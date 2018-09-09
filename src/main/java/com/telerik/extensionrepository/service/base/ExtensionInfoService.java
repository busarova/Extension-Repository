package com.telerik.extensionrepository.service.base;

import com.telerik.extensionrepository.model.Extension;
import com.telerik.extensionrepository.exceptions.RepositoryException;

import java.util.List;

public interface ExtensionInfoService {

    List<Extension> getAll() throws RepositoryException;

    List<Extension> getAllApproved() throws RepositoryException;

    List<Extension> getFeatured();

    List<Extension> getPopular();

    List<Extension> getNew();

    List<Extension> getByUserName(String userName);

    Extension getById(int id);

    Extension getExtByName(String name);

    List<Extension> returnAllOrderedBy(String parameter) throws RepositoryException;





}
