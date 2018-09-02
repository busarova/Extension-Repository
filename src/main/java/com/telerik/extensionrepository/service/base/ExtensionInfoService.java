package com.telerik.extensionrepository.service.base;

import com.telerik.extensionrepository.model.Extension;

import java.util.List;

public interface ExtensionInfoService {

    List<Extension> getAll();

    List<Extension> getAllApproved();

    List<Extension> getFeatured();

    List<Extension> getPopular();

    List<Extension> getNew();

    List<Extension> getByUserName(String userName);

    Extension getById(int id);

    Extension getExtByName(String name);

    List<Extension> returnOrderedBy(String parameter);

    List<Extension> returnAllOrderedBy(String parameter);

    List<Extension> sortListBy(List<Extension> list, String parameter);




}
