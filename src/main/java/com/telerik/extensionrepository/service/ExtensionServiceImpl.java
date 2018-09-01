package com.telerik.extensionrepository.service;

import com.telerik.extensionrepository.data.base.ExtensionRepository;
import com.telerik.extensionrepository.data.base.GitExtensionInfoRepository;
import com.telerik.extensionrepository.model.Extension;
import com.telerik.extensionrepository.model.GitExtensionInfo;
import com.telerik.extensionrepository.dto.ExtensionForm;
import com.telerik.extensionrepository.model.UploadFile;
import com.telerik.extensionrepository.service.base.ExtensionService;
import com.telerik.extensionrepository.service.base.GitService;
import com.telerik.extensionrepository.service.base.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
public class ExtensionServiceImpl implements ExtensionService {

    private TagManipulations tagManipulations = new TagManipulations();
    private ExtensionRepository extensionRepository;
    private TagService tagService;
    private GitService gitService;

    @Autowired
    public ExtensionServiceImpl(ExtensionRepository extensionRepository, TagService tagService, GitService gitService) {
        this.extensionRepository = extensionRepository;
        this.tagService = tagService;
        this.gitService = gitService;
    }

    // Creates extension from the dto information given at input

    @Override
    public Extension createExtension(ExtensionForm extensionForm) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();

        Extension newExtension = new Extension(
                extensionForm.getName(),
                extensionForm.getDescription(),
                extensionForm.getVersion(),
                user.getUsername(),
                tagManipulations.checkForHashTag(extensionForm.getTags()));

        newExtension.getGitExtensionInfo().setGitRepoLink(extensionForm.getGithubLink());

        // Checks if there is no file or if it is an empty string
        //If there is a file will set its properties
        //Otherwise will leave it null so that thymeleaf will show "No file" in page

        if (extensionForm.getCommonsMultipartFile() != null && extensionForm.getCommonsMultipartFile().getSize() > 0) {

            newExtension.getUploadFile().setFileName(extensionForm.getCommonsMultipartFile().getOriginalFilename());
            newExtension.getUploadFile().setData(extensionForm.getCommonsMultipartFile().getBytes());

        }

        newExtension.setGitExtensionInfo(gitService.getGitDetails(extensionForm.getGithubLink()));
        extensionRepository.createExtension(newExtension);
        tagService.loadNewTags(newExtension);

        return newExtension;
    }

    public Extension updateExtension(Extension extension){

        return extensionRepository.updateExtension(extension);
    }

    @Override
    public Extension changeExtensionName(Extension extension, String newName) {

        extension.setName(newName);

        return extensionRepository.updateExtension(extension);

    }
    @Override
    public Extension registerDownload(Extension extension) {

       return extensionRepository.registerDownload(extension);
    }

    @Override
    public Extension changeExtensionDescription(Extension extension, String newDescription) {

        extension.setDescription(newDescription);

        return extensionRepository.updateExtension(extension);
    }

    @Override
    public void addExtensionTag(Extension extension, String newTags) {

        newTags = tagManipulations.checkForHashTag(newTags);

        extension.setTags(extension.getTags() + " " + newTags);

        extensionRepository.updateExtension(extension);

        tagService.loadNewTags(extension);
    }


}
