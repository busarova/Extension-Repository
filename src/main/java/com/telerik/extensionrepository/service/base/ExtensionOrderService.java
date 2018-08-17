package com.telerik.extensionrepository.service.base;

import com.telerik.extensionrepository.model.Extension;

import java.util.List;

public interface ExtensionOrderService {

    List<Extension> getFeatured();

    List<Extension> getPopular();

    List<Extension> getNew();

    List<Extension> getByUserName(String userName);

    Extension getExtByName(String name);

    List<Extension> returnOrderedBy(String parameter);

    List<Extension> sortListBy(List<Extension> list, String parameter);

}
