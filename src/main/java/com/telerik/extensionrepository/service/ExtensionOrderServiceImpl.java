package com.telerik.extensionrepository.service;

import com.telerik.extensionrepository.model.Extension;
import com.telerik.extensionrepository.service.base.ExtensionOrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExtensionOrderServiceImpl implements ExtensionOrderService {

    @Override
    public List<Extension> getFeatured() {
        return null;
    }

    @Override
    public List<Extension> getPopular() {
        return null;
    }

    @Override
    public List<Extension> getNew() {
        return null;
    }
}
