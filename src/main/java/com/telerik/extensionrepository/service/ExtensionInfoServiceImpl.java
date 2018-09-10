package com.telerik.extensionrepository.service;

import com.telerik.extensionrepository.data.base.ExtensionRepository;
import com.telerik.extensionrepository.model.Extension;
import com.telerik.extensionrepository.service.base.ExtensionInfoService;
import com.telerik.extensionrepository.exceptions.RepositoryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExtensionInfoServiceImpl implements ExtensionInfoService {

    private ExtensionRepository extensionRepository;

    @Autowired
    public ExtensionInfoServiceImpl(ExtensionRepository extensionRepository){
        this.extensionRepository = extensionRepository;
    }

    @Override
    public List<Extension> getAll() throws RepositoryException {
        return extensionRepository.getAllExtensions();
    }

    @Override
    public List<Extension> getAllApproved() throws RepositoryException {
        return extensionRepository.getAllApproved();
    }

    @Override
    public List<Extension> getFeatured() {                                                  // gets all the extensions which are featured and approved

        return extensionRepository.getFeaturedExtensions();

    }

    @Override
    public List<Extension> getPopular() {                                               // gets all extensions and sorts them by number of downloads

        return extensionRepository.getPopularExtensions();
    }

    @Override
    public List<Extension> getNew() {

        return extensionRepository.getNewExtensions();
    }

    @Override
    public List<Extension> getByUserName(String userName) {

        return extensionRepository.getByUserName(userName);

    }

    @Override
    public Extension getById(int id) {
        return extensionRepository.getExtById(id);
    }

    @Override
    public Extension getExtByName(String name) {

        return extensionRepository.getExtByName(name);

    }

    // to fix the last commit date order when real extensions are placed

    @Override
    public List<Extension> returnAllOrderedBy(String parameter) throws RepositoryException{

        switch (parameter){

            case "name":

                return extensionRepository.getAllApproved();

            case "downloads":

                return extensionRepository.getPopularExtensions();

            case "uploadDate":

                return extensionRepository.getNewExtensions();

            case "lastCommitDate":

                return extensionRepository.getAllExtensions();

            case "featured":

                return extensionRepository.getFeaturedExtensions();
        }

        return null;

    }

}
