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
    public AdminServiceImpl(ExtensionRepository extensionRepository, AdminRepository adminRepository, GitExtensionInfoRepository gitExtensionInfoRepository, GitService gitService){
        this.extensionRepository = extensionRepository;
        this.adminRepository = adminRepository;
        this.gitExtensionInfoRepository = gitExtensionInfoRepository;
        this.gitService = gitService;
    }

    @Override
    public int refreshAllGitHubInfo() {

        List<GitExtensionInfo> allGitInfo =  gitExtensionInfoRepository.getAllGitInfo();

        int errors = 0;

        for (GitExtensionInfo gitInfo:
             allGitInfo) {
            String gitLink = gitInfo.getGitRepoLink();
            gitInfo =  gitService.getGitDetails(gitLink);
            gitExtensionInfoRepository.updateGitInfo(gitInfo);
            System.out.println("GIT INFO: " + gitInfo.getId() + "UPDATED");
        }

        adminRepository.updateLastSuccessfulSync(new Date());

        return errors;
    }

    @Override
    public List<Extension> getUnnaprovedExt() {
        return extensionRepository.getAllExtensions().stream()
                .filter( x-> x.getApproved() == 0)
                .collect(Collectors.toList());
    }

    @Override
    public void approveExt(int id) {

        adminRepository.approveExtension(id);

    }

    @Override
    public void removeApproval(int id) {
        adminRepository.removeApproval(id);
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
    public void disableUser(String name) {
        adminRepository.disableUser(name);
    }

    @Override
    public void enableUser(String name) {
        adminRepository.enableUser(name);
    }

    @Override
    public void featureExtension(int id) {
        adminRepository.featureExtension(id);
    }

    @Override
    public void unFeatureExtension(int id) {
        adminRepository.unFeatureExtension(id);
    }

    @Override
    public void deleteExtension(int id) {

        Extension extension = extensionRepository.getExtById(id);

      // int gitId = extension.getGitId();

       adminRepository.deleteExtension(extension);
     //  adminRepository.deleteGitExtensionInfo(gitExtensionInfoRepository.getGitInfoById(gitId));
     //  adminRepository.deleteFile(extension.getFileId());


    }

    @Override
    public Admin getAdminInfo() {
        return adminRepository.getAdminInfo();
    }
}
