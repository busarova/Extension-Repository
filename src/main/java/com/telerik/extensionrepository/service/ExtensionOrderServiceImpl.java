package com.telerik.extensionrepository.service;

import com.telerik.extensionrepository.data.base.ExtensionRepository;
import com.telerik.extensionrepository.model.Extension;
import com.telerik.extensionrepository.service.base.ExtensionOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExtensionOrderServiceImpl implements ExtensionOrderService {

    private ExtensionRepository extensionRepository;

    @Autowired
    public ExtensionOrderServiceImpl(ExtensionRepository extensionRepository){
        this.extensionRepository = extensionRepository;
    }

    @Override
    public List<Extension> getFeatured() {
        return extensionRepository.getAllExtensions();
    }

    @Override
    public List<Extension> getPopular() {
        return extensionRepository.getAllExtensions();
    }

    @Override
    public List<Extension> getNew() {
        return extensionRepository.getAllExtensions();
    }
}
