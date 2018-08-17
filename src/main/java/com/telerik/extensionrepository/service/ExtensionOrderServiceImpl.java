package com.telerik.extensionrepository.service;

import com.telerik.extensionrepository.data.base.ExtensionRepository;
import com.telerik.extensionrepository.model.Extension;
import com.telerik.extensionrepository.service.base.ExtensionOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
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
    public List<Extension> getFeatured() {                                                  // sorts the extensions if they are featured == 0
        return extensionRepository.getAllExtensions().stream()
                .filter( x -> x.getFeatured() == 0 )
                .collect(Collectors.toList());
    }

    @Override
    public List<Extension> getPopular() {                                         // gets all extensions and sorts them by number of downloads

        List<Extension> list = extensionRepository.getAllExtensions();

        list.sort(new Comparator<Extension>() {
            public int compare(Extension o1, Extension o2) {
                if (o1.getNumberOfDownloads() == o2.getNumberOfDownloads())
                    return 0;
                return o1.getNumberOfDownloads() > o2.getNumberOfDownloads() ? -1 : 1;
            }
        });


        return list;

    }

    @Override
    public List<Extension> getNew() {
        return extensionRepository.getAllExtensions();
    }

    @Override
    public List<Extension> getByUserName(String userName) {
        return extensionRepository.getAllExtensions().stream()
                .filter( x -> x.getOwner().equals(userName))
                .collect(Collectors.toList());
    }

    @Override
    public Extension getExtByName(String name) {
        return extensionRepository.getAllExtensions().stream()
                .filter( x -> x.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Extension> returnOrderedBy(String parameter) {

        String[] commands = parameter.split(" ");

        switch (commands[0]){

            case "popular":
                return sortListBy(getPopular(), commands[1]);

            case "featured":
                return sortListBy(getFeatured(), commands[1]);

            case "new":
                return sortListBy(getNew(), commands[1]);

        }

        return null;
    }

    @Override
    public List<Extension> sortListBy(List<Extension> list, String parameter) {

        switch (parameter){

            case "name":
                list.sort(Comparator.comparing(Extension::getName));
                return list;

            case "downloads":
                list.sort(Comparator.comparing(Extension::getNumberOfDownloads));
                 return list;

            case "uploadDate":

                list.sort(Comparator.comparing(Extension::getUploadDate));
                return list;

            case "lastCommitDate":

                list.sort(Comparator.comparing(Extension::getUploadDate));
                return list;

        }

        return null;
    }
}
