package com.telerik.extensionrepository.service;

import com.telerik.extensionrepository.data.base.ExtensionRepository;
import com.telerik.extensionrepository.model.Extension;
import com.telerik.extensionrepository.service.base.ExtensionOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExtensionOrderServiceImpl implements ExtensionOrderService {

    private ExtensionRepository extensionRepository;

    @Autowired
    public ExtensionOrderServiceImpl(ExtensionRepository extensionRepository){
        this.extensionRepository = extensionRepository;
    }

    @Override
    public List<Extension> getFeatured() {
        return extensionRepository.getAllExtensions().stream()
                .filter( x -> x.getFeatured() == 0 )
                .collect(Collectors.toList());
    }

    @Override
    public List<Extension> getPopular() {

        List<Extension> list = extensionRepository.getAllExtensions();

        Collections.sort(list, new Comparator<Extension>(){
            public int compare(Extension o1, Extension o2){
                if(o1.getNumberOfDownloads() == o2.getNumberOfDownloads())
                    return 0;
                return o1.getNumberOfDownloads() < o2.getNumberOfDownloads() ? 1 : -1;
            }
        });


        return list;

    }

    @Override
    public List<Extension> getNew() {
        return extensionRepository.getAllExtensions();
    }
}
