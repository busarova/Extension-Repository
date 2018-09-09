package com.telerik.extensionrepository.service;

import com.telerik.extensionrepository.data.base.ExtensionRepository;
import com.telerik.extensionrepository.model.Extension;
import com.telerik.extensionrepository.dto.ExtensionForm;
import com.telerik.extensionrepository.model.Tags;
import com.telerik.extensionrepository.service.base.ExtensionService;
import com.telerik.extensionrepository.service.base.GitService;
import com.telerik.extensionrepository.service.base.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Extension createExtension(ExtensionForm extensionForm, User user) throws Exception{

        Extension newExtension = createExtensionFromForm(extensionForm, user);

        newExtension.setGitExtensionInfo(gitService.getGitDetails(extensionForm.getGithubLink()));

        newExtension.setTags(tagService.loadNewTags(extensionForm));

        for (Tags tag:
            newExtension.getTags() ) {
            System.out.println(tag.getName());
        }

        extensionRepository.createExtension(newExtension);

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
    public Extension addExtensionTag(Extension extension, String newTags) {

        newTags = tagManipulations.checkForHashTag(newTags);

        String[] splitTags = newTags.split(" ");

        List<Tags> tagList = extension.getTags();

        for (String tag:
            splitTags ) {

            tagList.add(new Tags(tag));
        }

        extension.setTags(tagList);

        extension.setApproved(0);

       return extensionRepository.updateExtension(extension);

    }

    @Override
    public Extension createExtensionFromForm(ExtensionForm extensionForm, User user) {

        Extension newExtension = new Extension(
                extensionForm.getName(),
                extensionForm.getDescription(),
                extensionForm.getVersion(),
                user.getUsername());

        newExtension.getGitExtensionInfo().setGitRepoLink(extensionForm.getGithubLink());

        newExtension.getUploadFile().setFileName(extensionForm.getCommonsMultipartFile().getOriginalFilename());
        newExtension.getUploadFile().setData(extensionForm.getCommonsMultipartFile().getBytes());

        if(extensionForm.getLogoMultipartFile() != null && extensionForm.getLogoMultipartFile().getSize() > 1) {
            newExtension.getUploadFile().setLogoData(extensionForm.getLogoMultipartFile().getBytes());
        }

        return newExtension;
    }


}
