package com.telerik.extensionrepository.service;

import com.telerik.extensionrepository.data.base.ExtensionRepository;
import com.telerik.extensionrepository.model.Extension;
import com.telerik.extensionrepository.service.base.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {

    private ExtensionRepository extensionRepository;

    @Autowired
    public SearchServiceImpl(ExtensionRepository extensionRepository) {
        this.extensionRepository = extensionRepository;
    }

    @Override
    public List<Extension> getAllByParam(String param) {

        System.out.println("the param: " + param);
        System.out.println(extensionRepository.getAllByParam(param));
        return extensionRepository.getAllByParam(param);
    }
}
