package com.telerik.extensionrepository.service;

import com.telerik.extensionrepository.data.base.AdminRepository;
import com.telerik.extensionrepository.data.base.ExtensionRepository;
import com.telerik.extensionrepository.data.base.GitExtensionInfoRepository;
import com.telerik.extensionrepository.model.Admin;
import com.telerik.extensionrepository.model.Extension;
import com.telerik.extensionrepository.model.GitExtensionInfo;
import com.telerik.extensionrepository.model.User;
import com.telerik.extensionrepository.service.base.AdminService;
import com.telerik.extensionrepository.service.base.GitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService {

    private ExtensionRepository extensionRepository;
    private AdminRepository adminRepository;
    private GitService gitService;
    private GitExtensionInfoRepository gitExtensionInfoRepository;

    @Autowired
    public AdminServiceImpl(ExtensionRepository extensionRepository, AdminRepository adminRepository, GitExtensionInfoRepository gitExtensionInfoRepository, GitService gitService) {
        this.extensionRepository = extensionRepository;
        this.adminRepository = adminRepository;
        this.gitExtensionInfoRepository = gitExtensionInfoRepository;
        this.gitService = gitService;
    }

    @Override
    public int refreshAllGitHubInfo() {

        List<GitExtensionInfo> allGitInfo = gitExtensionInfoRepository.getAllGitInfo();

        int errors = 0;

        for (GitExtensionInfo gitInfo :
                allGitInfo) {
            String gitLink = gitInfo.getGitRepoLink();
            gitInfo = gitService.getGitDetails(gitLink);
            gitExtensionInfoRepository.updateGitInfo(gitInfo);
            System.out.println("GIT INFO: " + gitInfo.getId() + "UPDATED");
        }

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
}
