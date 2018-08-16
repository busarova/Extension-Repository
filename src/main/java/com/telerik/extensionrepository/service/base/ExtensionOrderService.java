package com.telerik.extensionrepository.service.base;

import com.telerik.extensionrepository.model.Extension;

import java.util.List;

public interface ExtensionOrderService {

    List<Extension> getFeatured();

    List<Extension> getPopular();

    List<Extension> getNew();

}
