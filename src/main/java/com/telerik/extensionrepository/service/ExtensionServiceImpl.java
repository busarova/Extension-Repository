package com.telerik.extensionrepository.service;

import com.telerik.extensionrepository.data.base.ExtensionRepository;
import com.telerik.extensionrepository.model.Extension;
import com.telerik.extensionrepository.dto.ExtensionForm;
import com.telerik.extensionrepository.service.base.ExtensionService;
import com.telerik.extensionrepository.service.base.GitService;
import com.telerik.extensionrepository.service.base.TagService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Extension createExtension(ExtensionForm extensionForm, User user) {

        Extension newExtension = createExtensionFromForm(extensionForm, user);

        newExtension.setGitExtensionInfo(gitService.getGitDetails(extensionForm.getGithubLink()));

        extensionRepository.createExtension(newExtension);

        tagService.loadNewTags(newExtension);

        return newExtension;
    }

    public Extension updateExtension(Extension extension) {

        return extensionRepository.updateExtension(extension);
    }

    @Override
    public Extension changeExtensionName(Extension extension, String newName) {

        extension.setName(newName);

        extension.setApproved(0);

        return extensionRepository.updateExtension(extension);

    }

    @Override
    public Extension registerDownload(Extension extension) {

        long extensionDownloads = extension.getNumberOfDownloads();
        extensionDownloads++;
        extension.setNumberOfDownloads(extensionDownloads);

        return extensionRepository.updateExtension(extension);
    }

    @Override
    public Extension changeExtensionDescription(Extension extension, String newDescription) {

        extension.setDescription(newDescription);

        extension.setApproved(0);

        return extensionRepository.updateExtension(extension);
    }

    @Override
    public void addExtensionTag(Extension extension, String newTags) {

        newTags = tagManipulations.checkForHashTag(newTags);

        extension.setTags(extension.getTags() + " " + newTags);

        extension.setApproved(0);

        extensionRepository.updateExtension(extension);

        tagService.loadNewTags(extension);
    }

    @Override
    public Extension createExtensionFromForm(ExtensionForm extensionForm, User user) {

        // Checks if there is no file or if it is an empty string
        //If there is a file will set its properties
        //Otherwise will leave it null so that thymeleaf will show "No file" in page

        Extension newExtension = new Extension(
                extensionForm.getName(),
                extensionForm.getDescription(),
                extensionForm.getVersion(),
                user.getUsername(),
                tagManipulations.checkForHashTag(extensionForm.getTags()));

        newExtension.getGitExtensionInfo().setGitRepoLink(extensionForm.getGithubLink());

        if (extensionForm.getCommonsMultipartFile() != null && extensionForm.getCommonsMultipartFile().getSize() > 0) {

            newExtension.getUploadFile().setFileName(extensionForm.getCommonsMultipartFile().getOriginalFilename());
            newExtension.getUploadFile().setData(extensionForm.getCommonsMultipartFile().getBytes());

        }


        return newExtension;
    }


}
