package com.telerik.extensionrepository.service;

import com.telerik.extensionrepository.data.base.AdminRepository;
import com.telerik.extensionrepository.data.base.ExtensionRepository;
import com.telerik.extensionrepository.exceptions.GithubSyncException;
import com.telerik.extensionrepository.model.*;
import com.telerik.extensionrepository.service.base.AdminService;
import com.telerik.extensionrepository.service.base.GitService;
import com.telerik.extensionrepository.service.base.TagService;
import com.telerik.extensionrepository.exceptions.RepositoryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    private ExtensionRepository extensionRepository;
    private AdminRepository adminRepository;
    private GitService gitService;
    private TagService tagService;

    @Autowired
    public AdminServiceImpl(ExtensionRepository extensionRepository, AdminRepository adminRepository, GitService gitService, TagService tagService) {
        this.extensionRepository = extensionRepository;
        this.adminRepository = adminRepository;
        this.gitService = gitService;
        this.tagService = tagService;
    }

    @Override
    public int refreshAllGitHubInfo() throws RepositoryException, GithubSyncException {


        List<Extension> allExtensions = extensionRepository.getAllExtensions();

        int errors = 0;
// new log
        // set start date
        for (Extension extension :
                allExtensions) {
//try
            refreshExtensionGitInfo(extension.getId());
//catch
            // set end date, save exception
            //break
        }

        // finally always close the end date

        adminRepository.updateLastSuccessfulSync(new Date());

        return errors;
    }

    @Override
    public List<Extension> getNotApprovedExt() {
        return extensionRepository.getAllNotApproved();
    }

    @Override
    public Extension approveExt(int id) {

        Extension extension = extensionRepository.getExtById(id);
        extension.setApproved(1);
        extensionRepository.updateExtension(extension);
        return extension;

    }

    @Override
    public Extension removeApproval(int id) {
        Extension extension = extensionRepository.getExtById(id);
        extension.setApproved(0);
        extensionRepository.updateExtension(extension);
        return extension;
    }

    @Override
    public List<User> getAllUsers() {
        return adminRepository.getAllUsers();
    }

    @Override
    public User getUser(String name) {
        return adminRepository.getUserByName(name);
    }

    @Override
    public User disableUser(String name) {
        User user = adminRepository.getUserByName(name);
        user.setEnabled(0);
        adminRepository.updateUser(user);
        return user;
    }

    @Override
    public User enableUser(String name) {
        User user = adminRepository.getUserByName(name);
        user.setEnabled(1);
        adminRepository.updateUser(user);
        return user;
    }

    @Override
    public Extension featureExtension(int id) {
        Extension extension = extensionRepository.getExtById(id);
        extension.setFeatured(1);
        extensionRepository.updateExtension(extension);
        return extension;
    }

    @Override
    public Extension unFeatureExtension(int id) {
        Extension extension = extensionRepository.getExtById(id);
        extension.setFeatured(0);
        extensionRepository.updateExtension(extension);
        return extension;
    }

    @Override
    public Extension deleteExtension(int id) {

        Extension extension = extensionRepository.getExtById(id);
        return extensionRepository.deleteExtension(extension);
    }

    @Override
    public Admin getAdminInfo() {
        return adminRepository.getAdminInfo();
    }

    @Override
    public void refreshExtensionGitInfo(int id) {

        Extension extension = extensionRepository.getExtById(id);

        gitService.getGitDetails(extension.getGitExtensionInfo().getGitRepoLink());

        extension.getGitExtensionInfo().setLastSuccessfulSync(new Date());

        extensionRepository.updateExtension(extension);

    }

    @Override
    public int emptyTagCheck() {

        int count = 0;

        for (Tags tag:
           tagService.getAllTags()  ) {

            if(tag.getExtensions().size() == 0){
                tagService.deleteTag(tag);
                count++;
            }
        }

        adminRepository.updateLastSuccessfulTagClean(new Date());

        return count;
    }

    @Override
    public String getScheduledTaskTime() {
        return adminRepository.getAdminInfo().getScheduled_time();
    }
}
