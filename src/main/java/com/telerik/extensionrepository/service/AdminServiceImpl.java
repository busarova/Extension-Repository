package com.telerik.extensionrepository.service;

import com.telerik.extensionrepository.data.base.AdminRepository;
import com.telerik.extensionrepository.data.base.ExtensionRepository;
import com.telerik.extensionrepository.data.base.GitExtensionInfoRepository;
import com.telerik.extensionrepository.model.Extension;
import com.telerik.extensionrepository.model.User;
import com.telerik.extensionrepository.service.base.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService {

    private ExtensionRepository extensionRepository;
    private AdminRepository adminRepository;
    private GitExtensionInfoRepository gitExtensionInfoRepository;

    @Autowired
    public AdminServiceImpl(ExtensionRepository extensionRepository, AdminRepository adminRepository, GitExtensionInfoRepository gitExtensionInfoRepository){
        this.extensionRepository = extensionRepository;
        this.adminRepository = adminRepository;
        this.gitExtensionInfoRepository = gitExtensionInfoRepository;
    }

    @Override
    public List<Extension> getUnnaprovedExt() {
        return extensionRepository.getAllExtensions().stream()
                .filter( x-> x.getApproved() == 1)
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

       int gitId = extension.getGitId();

       adminRepository.deleteExtension(extension);
       adminRepository.deleteGitExtensionInfo(gitExtensionInfoRepository.getGitInfoById(gitId));
       adminRepository.deleteFile(extension.getFileId());


    }
}
